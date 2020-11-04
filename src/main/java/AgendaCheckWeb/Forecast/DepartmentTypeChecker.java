package AgendaCheckWeb.Forecast;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;

public class DepartmentTypeChecker {

    private final List<String> noneRetailNames = List.of("kas","pok","wykre","admin","nieobecno", "kierowni", "kadr");
    private final List<String> cashTillsNames = List.of("kas", "pok", "punkt", "obsługi", "klienta");

    private XSSFSheet sheet;
    private int columnNrToCheckIfThereIsTurnOver;

    public DepartmentTypeChecker(XSSFSheet sheet, int columnNrToCheckIfThereIsTurnOver) {
        this.sheet = sheet;
        this.columnNrToCheckIfThereIsTurnOver = columnNrToCheckIfThereIsTurnOver;
    }

    public boolean isItCashTillDepartment(){
        return checkNamesWithSheetName(sheet.getSheetName(), cashTillsNames);
    }

    public boolean isRetailDepartment() {
        int columnToCheck = columnNrToCheckIfThereIsTurnOver;

        int numberOfRowsInSheet = sheet.getLastRowNum();
        boolean isSheetEmpty = numberOfRowsInSheet < 0;
        if (isSheetEmpty){
            return false;
        }

        if (checkNamesWithSheetName(sheet.getSheetName(), noneRetailNames)){
            return false;
        }

        short lastCellNum = sheet.getRow(2).getLastCellNum();
        boolean tableIsNotLongEnoughToCheckThisMonth = lastCellNum < columnToCheck;
        if (tableIsNotLongEnoughToCheckThisMonth) {
            return false;
        }

        XSSFCell cellShouldBeString = sheet.getRow(2).getCell(0);
        if (cellShouldBeString == null){
            return false;
        }

        boolean isItForecastCell;

        String cellShouldContain = "Obrót";
        String cellShouldAlsoContain = "2020";

        if (cellShouldBeString.getCellType().equals(CellType.STRING)) {
            String stringCellValue = cellShouldBeString.getStringCellValue();
            isItForecastCell = stringCellValue.contains(cellShouldContain) && stringCellValue.contains(cellShouldAlsoContain);
        } else {
            isItForecastCell = false;
        }

        boolean isThereTurnover;
        XSSFCell cellNumericValueShouldBeGreaterThen0 = sheet.getRow(2).getCell(columnToCheck);
        if (cellNumericValueShouldBeGreaterThen0 == null){
            return false;
        }
        if (cellNumericValueShouldBeGreaterThen0.getCellType().equals(CellType.NUMERIC)) {
            isThereTurnover = cellNumericValueShouldBeGreaterThen0.getNumericCellValue() > 0;
        } else if (cellNumericValueShouldBeGreaterThen0.getCellType().equals(CellType.FORMULA)) {
            double rawValue = Double.parseDouble(cellNumericValueShouldBeGreaterThen0.getRawValue());
            isThereTurnover = rawValue > 0;
        } else {
            isThereTurnover = false;
        }

        return isItForecastCell && isThereTurnover;
    }


    private boolean checkNamesWithSheetName(String sheetName, List<String> departmentNames) {
        for (String name : departmentNames) {
            if (sheetName.toLowerCase().contains(name)){
                return true;
            }
        }
        return false;
    }

}
