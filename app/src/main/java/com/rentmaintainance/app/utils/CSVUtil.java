package com.rentmaintainance.app.utils;

import android.database.Cursor;
import com.csvreader.CsvWriter;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;


public class CSVUtil {

    public void generateCSV(Cursor cursor, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        CsvWriter csvWriter = new CsvWriter(fileWriter, ',');

        for (int coursorIndex = 0; coursorIndex < cursor.getCount(); coursorIndex++) {

            int numberOfColumns = cursor.getColumnCount();
            String[] values = new String[numberOfColumns];

            for (int index = 0; index < numberOfColumns; index++) {
                int type = cursor.getType(index);
                if (type == Cursor.FIELD_TYPE_STRING)
                    values[index] = cursor.getString(index);
                else if (type == Cursor.FIELD_TYPE_FLOAT)
                    values[index] = Float.toString(cursor.getFloat(index));
                else if (type == Cursor.FIELD_TYPE_INTEGER)
                    values[index] = Integer.toString(cursor.getInt(index));
            }
            csvWriter.writeRecord(values);
        }
        csvWriter.close();
        fileWriter.close();
    }

//    public void generateExcel(Cursor cursor, File file) throws IOException {
//        XSSFWorkbook workBook = new XSSFWorkbook();
//        XSSFSheet sheet = workBook.createSheet("sheet1");
//        int rowNum = 0;
//
//        for (int coursorIndex = 0; coursorIndex < cursor.getCount(); coursorIndex++) {
//            rowNum++;
//            XSSFRow currentRow = sheet.createRow(rowNum);
//
//            int numberOfColumns = cursor.getColumnCount();
//            String[] values = new String[numberOfColumns];
//
//            for (int index = 0; index < numberOfColumns; index++) {
//                int type = cursor.getType(index);
//                if (type == Cursor.FIELD_TYPE_STRING)
//                    values[index] = cursor.getString(index);
//                else if (type == Cursor.FIELD_TYPE_FLOAT)
//                    values[index] = Float.toString(cursor.getFloat(index));
//                else if (type == Cursor.FIELD_TYPE_INTEGER)
//                    values[index] = Integer.toString(cursor.getInt(index));
//                currentRow.createCell(index).setCellValue(values[index]);
//            }
//        }
//        FileOutputStream fileOutputStream =  new FileOutputStream(file);
//        workBook.write(fileOutputStream);
//        fileOutputStream.close();
//    }


    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;

    public void write(Cursor cursor, File ofile) throws IOException, WriteException {
        File file = new File(ofile.getAbsolutePath());
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("en", "EN"));

        WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
        workbook.createSheet("Report", 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        createLabel(excelSheet);
        createContent(excelSheet, cursor);

        workbook.write();
        workbook.close();
    }

    private void createLabel(WritableSheet sheet)
            throws WriteException {
        // Lets create a times font
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
        // Define the cell format
        times = new WritableCellFormat(times10pt);
        // Lets automatically wrap the cells
        times.setWrap(true);

        // create create a bold font with unterlines
        WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
                UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
        // Lets automatically wrap the cells
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        // Write a few headers
        addCaption(sheet, 0, 0, "Header 1");
        addCaption(sheet, 1, 0, "This is another header");


    }

    private void createContent(WritableSheet sheet, Cursor cursor) throws WriteException,
            RowsExceededException {

        for (int row = 0; row < cursor.getCount(); row++) {

            int numberOfColumns = cursor.getColumnCount();

            for (int column = 0; column < numberOfColumns; column++) {
                int type = cursor.getType(column);
                if (type == Cursor.FIELD_TYPE_STRING)
                    addLabel(sheet, column, row, cursor.getString(column));
                else if (type == Cursor.FIELD_TYPE_FLOAT)
                    addLabel(sheet, column, row, Float.toString(cursor.getFloat(column)));
                else if (type == Cursor.FIELD_TYPE_INTEGER)
                    addLabel(sheet, column, row, Integer.toString(cursor.getInt(column)));
            }
        }


        // now a bit of text
        for (int i = 12; i < 20; i++) {
            // First column
            addLabel(sheet, 0, i, "Boring text " + i);
            // Second column
            addLabel(sheet, 1, i, "Another text");
        }
    }

    private void addCaption(WritableSheet sheet, int column, int row, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(column, row, s, timesBoldUnderline);
        sheet.addCell(label);
    }

    private void addNumber(WritableSheet sheet, int column, int row,
                           Integer integer) throws WriteException, RowsExceededException {
        Number number;
        number = new Number(column, row, integer, times);
        sheet.addCell(number);
    }

    private void addLabel(WritableSheet sheet, int column, int row, String s)
            throws WriteException, RowsExceededException {
        Label label;
        label = new Label(column, row, s, times);
        sheet.addCell(label);
    }

}
