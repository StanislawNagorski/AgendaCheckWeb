package AgendaCheckWeb;


import AgendaCheckWeb.Data.DataBank;
import AgendaCheckWeb.Forecast.ForecastReader;
import AgendaCheckWeb.ReportToXLSX.ReportWriter;
import AgendaCheckWeb.Schedule.ScheduleReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ReportGenerator {

    private final File forecastFile;
    private final File scheduleFile;
    private final double productivityTarget;
    private XSSFWorkbook report;
    ScheduleReader scheduleReader;
    ForecastReader forecastReader;
    DataBank dataBank;

    private final Logger logger = LogManager.getLogger();

    public ReportGenerator(File forecastFile, File scheduleFile, double productivityTarget) {
        this.forecastFile = forecastFile;
        this.scheduleFile = scheduleFile;
        this.productivityTarget = productivityTarget;

        setUpReaders();
        dataBank = new DataBank(scheduleReader, forecastReader, productivityTarget);
    }

    private void setUpReaders() {
        try {
            OPCPackage forecastInput = OPCPackage.open(forecastFile);
            XSSFWorkbook forecast = new XSSFWorkbook(forecastInput);
            forecastInput.close();

            OPCPackage scheduleInput = OPCPackage.open(scheduleFile);
            XSSFWorkbook schedule = new XSSFWorkbook(scheduleInput);
            scheduleInput.close();

            scheduleReader = new ScheduleReader(schedule);
            forecastReader = new ForecastReader(forecast);

        } catch (InvalidFormatException | IOException e) {
            logger.error(e);
        }

    }

    private void generateFullReport() {
        report = new XSSFWorkbook();
        ReportWriter reportWriter = new ReportWriter(report, dataBank);
        try {
            reportWriter.writeStoreSheet();
            reportWriter.writeAllDepartmentsSheets();
        } catch (IOException e) {
            logger.error(e);
        }

    }

    public File writeFullReport(String path) {

        generateFullReport();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        String storeNumber = forecastFile.getName().substring(0, 4);

        String fileName = storeNumber + "Raport  z  " + date.format(dt) + ".xlsx";

        try {
            FileOutputStream outputStream = new FileOutputStream(path + File.separator + fileName);
            report.write(outputStream);
            report.close();
        } catch (IOException e){
            logger.error(e);
        }

        File reportFile = new File(path + File.separator + fileName);
        return reportFile;
    }

}



