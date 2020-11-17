package AgendaCheckWeb.Forecast;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.util.List;

import static AgendaCheckWeb.Forecast.ForecastUtils.DEPARTMENT_TURNOVER_ROW;
import static AgendaCheckWeb.Forecast.ForecastUtils.DEPARTMENT_LABELS_COLUMN;

public class DepartmentTypeChecker {

    private final List<String> NONE_RETAIL_SHEET_NAMES = List.of("kas", "pok", "wykre", "admin", "nieobecno", "kierowni", "kadr");
    private final List<String> CASH_TILLS_SHEET_NAMES = List.of("kas", "pok", "punkt", "obsługi", "klienta");
    private final List<String> TURNOVER_ROW = List.of("Obrót", "2020", "PILOTAŻ");


    private XSSFSheet sheet;
    private int columnNrToCheckIfThereIsTurnOver;

    public DepartmentTypeChecker(XSSFSheet sheet, int columnNrToCheckIfThereIsTurnOver) {
        this.sheet = sheet;
        this.columnNrToCheckIfThereIsTurnOver = columnNrToCheckIfThereIsTurnOver;
    }

    public boolean isItCashTillDepartment() {
        return checkNamesWithSheetName(sheet.getSheetName(), CASH_TILLS_SHEET_NAMES);
    }

    //TODO rozbij na czy to jest dział? a później handlowy lub usługowy

    public boolean isRetailDepartment() {
        int columnToCheck = columnNrToCheckIfThereIsTurnOver;

        int numberOfRowsInSheet = sheet.getLastRowNum();
        boolean isSheetEmpty = numberOfRowsInSheet < 0;
        if (isSheetEmpty) {
            return false;
        }

        if (checkNamesWithSheetName(sheet.getSheetName(), NONE_RETAIL_SHEET_NAMES)) {
            return false;
        }

        if (sheet.getRow(DEPARTMENT_TURNOVER_ROW) == null){
            return false;
        }

        short lastCellNum = sheet.getRow(DEPARTMENT_TURNOVER_ROW).getLastCellNum();
        boolean tableIsNotLongEnoughToCheckThisMonth = lastCellNum < columnToCheck;
        if (tableIsNotLongEnoughToCheckThisMonth) {
            return false;
        }

        XSSFCell cellShouldBeString = sheet.getRow(DEPARTMENT_TURNOVER_ROW).getCell(DEPARTMENT_LABELS_COLUMN);
        if (cellShouldBeString == null) {
            return false;
        }

        boolean isItForecastCell;
        if (cellShouldBeString.getCellType().equals(CellType.STRING)) {
            String stringCellValue = cellShouldBeString.getStringCellValue();
            isItForecastCell = isTurnOverRow(stringCellValue, TURNOVER_ROW);
        } else {
            return false;
        }

        boolean isThereTurnover;
        XSSFCell cellNumericValueShouldBeGreaterThen0 = sheet.getRow(DEPARTMENT_TURNOVER_ROW).getCell(columnToCheck);
        if (cellNumericValueShouldBeGreaterThen0 == null) {
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
            if (sheetName.toLowerCase().contains(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTurnOverRow(String cellValue, List<String> requirements) {

        for (String name : requirements) {
            if (!cellValue.toLowerCase().contains(name.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}
