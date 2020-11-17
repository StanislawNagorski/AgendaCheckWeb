package AgendaCheckWeb.Data;


import AgendaCheckWeb.Forecast.ForecastReader;
import AgendaCheckWeb.ReportToXLSX.ReportWriter;
import AgendaCheckWeb.Schedule.ScheduleReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Ignore
public class BackTest {
    @Test
    public void shouldCreateReportForMayForJune() throws IOException, InvalidFormatException {

        //Given
        OPCPackage scheduleInput = OPCPackage.open(new File("SampleInput/godzinyCzerwiec.xlsx"));
        XSSFWorkbook schedule = new XSSFWorkbook(scheduleInput);
        scheduleInput.close();

        OPCPackage forecastInput = OPCPackage.open(new File("SampleInput/643_Gessef 2020.xlsx"));
        XSSFWorkbook forecast = new XSSFWorkbook(forecastInput);
        forecastInput.close();

        XSSFWorkbook report = new XSSFWorkbook();

        ScheduleReader scheduleReader = new ScheduleReader(schedule);
        ForecastReader forecastReader = new ForecastReader(forecast);
        double productivityTargetUserInput = 800.0;
        DataBank dataBank = new DataBank(scheduleReader, forecastReader, productivityTargetUserInput);
        ReportWriter reportWriter = new ReportWriter(report, dataBank);
        //When
        reportWriter.writeStoreSheet();
        reportWriter.writeAllDepartmentsSheets();

        report.write(new FileOutputStream("TestResults/Czerwiec643.xlsx"));
        report.close();
        File checkFile = new File("TestResults/Czerwiec643.xlsx");

        //Then
        Assert.assertTrue(checkFile.exists());

    }

    @Test
    public void shouldCreateReportForAugust() throws IOException, InvalidFormatException {

        //Given
        OPCPackage scheduleInput = OPCPackage.open(new File("SampleInput/godzinySierpień.xlsx"));
        XSSFWorkbook schedule = new XSSFWorkbook(scheduleInput);
        scheduleInput.close();

        OPCPackage forecastInput = OPCPackage.open(new File("SampleInput/643_Gessef 2020.xlsx"));
        XSSFWorkbook forecast = new XSSFWorkbook(forecastInput);
        forecastInput.close();

        XSSFWorkbook report = new XSSFWorkbook();

        ScheduleReader scheduleReader = new ScheduleReader(schedule);
        ForecastReader forecastReader = new ForecastReader(forecast);
        double productivityTargetUserInput = 800.0;
        DataBank dataBank = new DataBank(scheduleReader, forecastReader, productivityTargetUserInput);
        ReportWriter reportWriter = new ReportWriter(report, dataBank);
        //When
        reportWriter.writeStoreSheet();
        reportWriter.writeAllDepartmentsSheets();

        report.write(new FileOutputStream("TestResults/Sierpień643.xlsx"));
        report.close();

        File checkFile = new File("TestResults/Sierpień643.xlsx");

        //Then
        Assert.assertTrue(checkFile.exists());


    }

    @Test
    public void shouldCreateReportForMayForStore729() throws IOException, InvalidFormatException {

        //Given
        OPCPackage scheduleInput = OPCPackage.open(new File("SampleInput/godzinyCzerwiec729.xlsx"));
        XSSFWorkbook schedule = new XSSFWorkbook(scheduleInput);
        scheduleInput.close();

        OPCPackage forecastInput = OPCPackage.open(new File("SampleInput/729 Gessef 2020.xlsx"));
        XSSFWorkbook forecast = new XSSFWorkbook(forecastInput);
        forecastInput.close();

        XSSFWorkbook report = new XSSFWorkbook();

        ScheduleReader scheduleReader = new ScheduleReader(schedule);
        ForecastReader forecastReader = new ForecastReader(forecast);
        double productivityTargetUserInput = 800.0;
        DataBank dataBank = new DataBank(scheduleReader, forecastReader, productivityTargetUserInput);
        ReportWriter reportWriter = new ReportWriter(report, dataBank);
        //When
        reportWriter.writeStoreSheet();
        reportWriter.writeAllDepartmentsSheets();

        report.write(new FileOutputStream("TestResults/Maj729.xlsx"));
        report.close();
        File checkFile = new File("TestResults/Maj729.xlsx");

        //Then
        Assert.assertTrue(checkFile.exists());

    }

    @Test
    public void shouldCreateReportForStore643ForOctober() throws IOException, InvalidFormatException {

        //Given
        OPCPackage scheduleInput = OPCPackage.open(new File("SampleInput/sumDivisionReport_20201020_181315.xlsx"));
        XSSFWorkbook schedule = new XSSFWorkbook(scheduleInput);
        scheduleInput.close();

        OPCPackage forecastInput = OPCPackage.open(new File("SampleInput/643_Gessef 2020.xlsx"));
        XSSFWorkbook forecast = new XSSFWorkbook(forecastInput);
        forecastInput.close();

        XSSFWorkbook report = new XSSFWorkbook();

        ScheduleReader scheduleReader = new ScheduleReader(schedule);
        ForecastReader forecastReader = new ForecastReader(forecast);
        double productivityTargetUserInput = 800.0;
        DataBank dataBank = new DataBank(scheduleReader, forecastReader, productivityTargetUserInput);
        ReportWriter reportWriter = new ReportWriter(report, dataBank);
        //When
        reportWriter.writeStoreSheet();
        reportWriter.writeAllDepartmentsSheets();

        report.write(new FileOutputStream("TestResults/643October.xlsx"));
        report.close();
        File checkFile = new File("TestResults/643October.xlsx");

        //Then
        Assert.assertTrue(checkFile.exists());

    }

    @Test
    public void shouldCreateReportForStore376ForNovember() throws IOException, InvalidFormatException {

        //Given
        OPCPackage scheduleInput = OPCPackage.open(new File("SampleInput/Bielany/sumDivisionReport_20201027_112815.xlsx"));
        XSSFWorkbook schedule = new XSSFWorkbook(scheduleInput);
        scheduleInput.close();

        OPCPackage forecastInput = OPCPackage.open(new File("SampleInput/Bielany/376 Gessef 2020.xlsx"));
        XSSFWorkbook forecast = new XSSFWorkbook(forecastInput);
        forecastInput.close();

        XSSFWorkbook report = new XSSFWorkbook();

        ScheduleReader scheduleReader = new ScheduleReader(schedule);
        ForecastReader forecastReader = new ForecastReader(forecast);
        double productivityTargetUserInput = 800.0;
        DataBank dataBank = new DataBank(scheduleReader, forecastReader, productivityTargetUserInput);
        ReportWriter reportWriter = new ReportWriter(report, dataBank);
        //When
        reportWriter.writeStoreSheet();
        reportWriter.writeAllDepartmentsSheets();

        report.write(new FileOutputStream("TestResults/376November.xlsx"));
        report.close();
        File checkFile = new File("TestResults/376November.xlsx");

        //Then
        Assert.assertTrue(checkFile.exists());

    }

    @Test
    public void shouldCreateReportForStore272ForNovember() throws IOException, InvalidFormatException {

        //Given
        OPCPackage scheduleInput = OPCPackage.open(new File("SampleInput/targo/sumDivisionReport_20201031_194523.xlsx"));
        XSSFWorkbook schedule = new XSSFWorkbook(scheduleInput);
        scheduleInput.close();

        OPCPackage forecastInput = OPCPackage.open(new File("SampleInput/targo/272 Gessef 2020.xlsx"));
        XSSFWorkbook forecast = new XSSFWorkbook(forecastInput);
        forecastInput.close();

        XSSFWorkbook report = new XSSFWorkbook();

        ScheduleReader scheduleReader = new ScheduleReader(schedule);
        ForecastReader forecastReader = new ForecastReader(forecast);
        double productivityTargetUserInput = 800.0;
        DataBank dataBank = new DataBank(scheduleReader, forecastReader, productivityTargetUserInput);
        ReportWriter reportWriter = new ReportWriter(report, dataBank);
        //When
        reportWriter.writeStoreSheet();
        reportWriter.writeAllDepartmentsSheets();

        report.write(new FileOutputStream("TestResults/272November.xlsx"));
        report.close();
        File checkFile = new File("TestResults/272November.xlsx");

        //Then
        Assert.assertTrue(checkFile.exists());

    }

    @Test
    public void shouldCreateReportForStore527() throws IOException, InvalidFormatException {

        //Given
        OPCPackage scheduleInput = OPCPackage.open(new File("SampleInput/527/sumDivisionReport_20201112_132811.xlsx"));
        XSSFWorkbook schedule = new XSSFWorkbook(scheduleInput);
        scheduleInput.close();

        OPCPackage forecastInput = OPCPackage.open(new File("SampleInput/527/527 Gessef 2020  6 STREF.xlsx"));
        XSSFWorkbook forecast = new XSSFWorkbook(forecastInput);
        forecastInput.close();

        XSSFWorkbook report = new XSSFWorkbook();

        ScheduleReader scheduleReader = new ScheduleReader(schedule);
        ForecastReader forecastReader = new ForecastReader(forecast);
        double productivityTargetUserInput = 800.0;
        DataBank dataBank = new DataBank(scheduleReader, forecastReader, productivityTargetUserInput);
        ReportWriter reportWriter = new ReportWriter(report, dataBank);
        //When
        reportWriter.writeStoreSheet();
        reportWriter.writeAllDepartmentsSheets();

        report.write(new FileOutputStream("TestResults/527.xlsx"));
        report.close();
        File checkFile = new File("TestResults/527.xlsx");

        //Then
        Assert.assertTrue(checkFile.exists());

    }
}
