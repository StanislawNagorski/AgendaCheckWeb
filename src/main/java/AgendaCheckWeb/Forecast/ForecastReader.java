package AgendaCheckWeb.Forecast;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static AgendaCheckWeb.Forecast.ForecastUtils.*;

public class ForecastReader {

    private final XSSFWorkbook forecast;
    private int numberOFDepartmentSheets = 0;
    private DepartmentTypeChecker typeChecker;

    public ForecastReader(XSSFWorkbook forecast) {
        this.forecast = forecast;
    }

    public int getNumberOFDepartmentSheets() {
        return numberOFDepartmentSheets;
    }

    public List<Double> createStoreForecastedTurnOverList(int[] range) {

        List<Double> foreList = new ArrayList<>();
        double monthlyTurnOver = 0;
        XSSFSheet forecastSheet = forecast.getSheet(DAYDAY_SHEET_NAME);

        int monthStartsAt = range[0];
        int monthEndsAt = range[1];

        for (int i = 0; i < DAYDAY_SHEET_SIZE - 5; i++) {

            XSSFCell dateCell = forecastSheet.getRow(i + DAYDAY_DATA_STARTS_ROW).getCell(DAYDAY_DATES_COLUMN);

            boolean isItNumericOrFormula = (dateCell.getCellType() == CellType.NUMERIC) ||
                    (dateCell.getCellType() == CellType.FORMULA);

            if (isItNumericOrFormula) {
                int numericValueOfDate = (int) dateCell.getNumericCellValue();

                boolean cellIsInReportedMonthRange = numericValueOfDate >= monthStartsAt && numericValueOfDate <= monthEndsAt;

                if (cellIsInReportedMonthRange) {
                    XSSFCell dailyTurnoverCell = forecastSheet.getRow(i + DAYDAY_DATA_STARTS_ROW)
                            .getCell(DAYDAY_DAILY_STORE_TURNOVER_COLUMN);
                    double dayTO;

                    CellType cachedFormulaResultType = null;
                    try {
                        cachedFormulaResultType = dailyTurnoverCell.getCachedFormulaResultType();
                    } catch (IllegalStateException e) {
                        System.out.println("Row" + dailyTurnoverCell.getRowIndex());

                        e.printStackTrace();
                    }

                    if (cachedFormulaResultType == CellType.STRING) {
                        dayTO = 0;
                    } else {
                        dayTO = dailyTurnoverCell.getNumericCellValue();
                    }

                    monthlyTurnOver += dayTO;
                    foreList.add(dayTO);
                }

                if (numericValueOfDate > monthEndsAt) {
                    break;
                }
            }
        }
        foreList.add(monthlyTurnOver);
        return foreList;
    }

    public List<Double> createDailyTurnOverShareList(List<Double> forecastTO) {
        List<Double> dailyShareToList = new ArrayList<>();

        for (int i = 0; i < forecastTO.size(); i++) {
            double shareValue = forecastTO.get(i) / forecastTO.get(forecastTO.size() - 1);
            dailyShareToList.add(shareValue);
        }
        return dailyShareToList;
    }

    private int calculateMonthColumnNr(int monthNumber) {
        return monthNumber + (monthNumber - 1);
    }

    public Map<String, Double> createDepartmentsMonthlyTurnOverMap(int monthNumber) {
        Map<String, Double> monthlyTurnOverByDepartment = new LinkedHashMap<>();

        int numberOfSheets = forecast.getNumberOfSheets();
        int monthColumnNr = calculateMonthColumnNr(monthNumber);

        for (int i = 0; i < numberOfSheets; i++) {
            XSSFSheet forecastSheet = forecast.getSheetAt(i);
            typeChecker = new DepartmentTypeChecker(forecastSheet, monthColumnNr);

            System.out.println(typeChecker.isRetailDepartment() + " : " +forecastSheet.getSheetName());

            if (typeChecker.isRetailDepartment()) {
                XSSFCell cell = forecastSheet.getRow(DEPARTMENT_TURNOVER_ROW).getCell(monthColumnNr);
                double departmentForecastedTurnOver;

                if (cell.getCellType().equals(CellType.FORMULA)) {
                    departmentForecastedTurnOver = Double.parseDouble(cell.getRawValue());
                } else {
                    departmentForecastedTurnOver = cell.getNumericCellValue();
                }

                String departmentName = forecastSheet.getSheetName();
                monthlyTurnOverByDepartment.put(departmentName, departmentForecastedTurnOver);
                numberOFDepartmentSheets++;
            }

        }
        return monthlyTurnOverByDepartment;
    }


}
