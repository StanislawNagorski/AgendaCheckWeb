package AgendaCheckWeb.Schedule;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.*;

public class ScheduleReader {
    private final XSSFSheet scheduleSheet;
    private double monthlyStoreHoursSum;

    public ScheduleReader(XSSFWorkbook schedule) {
        this.scheduleSheet = schedule.getSheetAt(0);
    }

    public List<String> copyDatesToList() {

        List<String> firstColumn = new ArrayList<>();

        for (int i = 3; i < scheduleSheet.getLastRowNum(); i++) {
            firstColumn.add(String.valueOf(scheduleSheet.getRow(i).getCell(0)));
        }

        firstColumn.add("Suma:");
        return firstColumn;
    }

    public List<Double> createStoreDailyHoursList() {
        List<Double> hoursSumByDay = new ArrayList<>();
        monthlyStoreHoursSum = 0;

        for (int i = 3; i < scheduleSheet.getLastRowNum(); i++) {
            double daySum = 0;
            for (int j = 1; j < scheduleSheet.getRow(i).getLastCellNum(); j++) {
                daySum += scheduleSheet.getRow(i).getCell(j).getNumericCellValue();
            }
            hoursSumByDay.add(daySum);
            monthlyStoreHoursSum += daySum;
        }
        hoursSumByDay.add(monthlyStoreHoursSum);

        return hoursSumByDay;
    }

    public List<Double> createStoreShareOfHoursByDayList() {
        List<Double> hoursSumByDay = createStoreDailyHoursList();
        List<Double> percentagesOfHoursByDay = new LinkedList<>();

        for (Double hoursInDay : hoursSumByDay) {
            double dailyShare = hoursInDay / monthlyStoreHoursSum;
            percentagesOfHoursByDay.add(dailyShare);
        }
        return percentagesOfHoursByDay;
    }

    private List<String> createListOfDepartmentNames() {
        List<String> listOfDepartmentNames = new ArrayList<>();
        int rowWithDepartmentName = 1;
        XSSFRow startRow = scheduleSheet.getRow(rowWithDepartmentName);

        int columnOnWhichDataStarts = 1;

        for (int i = columnOnWhichDataStarts; i < startRow.getLastCellNum(); i++) {
            if (!startRow.getCell(i).getStringCellValue().isBlank()) {
                String departmentName = startRow.getCell(i).getStringCellValue();
                listOfDepartmentNames.add(departmentName);
            }
        }
        return listOfDepartmentNames;
    }

    private List<List<Double>> createListOfDailyHoursByDepartment() {
        List<List<Double>> dailyHoursByDepartment = new ArrayList<>();

        int rowOnWhichDataStarts = 3;
        int columnOnWhichDataStarts = 1;
        int rowLength = scheduleSheet.getRow(1).getLastCellNum();

        for (int i = columnOnWhichDataStarts; i < rowLength; i++) {

        
            List<Double> departmentHoursByDay = new ArrayList<>();
            for (int j = rowOnWhichDataStarts; j < scheduleSheet.getLastRowNum()+1; j++) {
                double dailyHours = scheduleSheet.getRow(j).getCell(i).getNumericCellValue();
                departmentHoursByDay.add(dailyHours);
            }

            int rowWithContractType = 2;
            String tempContract = "tymczasowe";
            String contractType = scheduleSheet.getRow(rowWithContractType).getCell(i).getStringCellValue();
            if(contractType.contains(tempContract)){
                int lastDepartmentIndex = dailyHoursByDepartment.size() - 1;
                List<Double> lastDepartment = dailyHoursByDepartment.get(lastDepartmentIndex);
                
                for (int k = 0; k < lastDepartment.size(); k++) {
                    Double employeeHours = lastDepartment.get(k);
                    Double tempEmployeeHours = departmentHoursByDay.get(k);
                    lastDepartment.set(k,tempEmployeeHours+employeeHours);
                }

                dailyHoursByDepartment.set(lastDepartmentIndex,lastDepartment);
                
            } else {
                dailyHoursByDepartment.add(departmentHoursByDay);
            }
                       
        }

        return dailyHoursByDepartment;
    }

    public Map<String, List<Double>> createMapOfScheduleDailyHoursByDepartment(){
        Map<String, List<Double>> dailyHoursMap = new LinkedHashMap<>();

        List<String> departmentNames = createListOfDepartmentNames();
        List<List<Double>> dailyScheduledHours = createListOfDailyHoursByDepartment();

        int departmentsCount = departmentNames.size();

        for (int i = 0; i < departmentsCount; i++) {
            dailyHoursMap.put(departmentNames.get(i), dailyScheduledHours.get(i));
        }
        return dailyHoursMap;
    }

}
