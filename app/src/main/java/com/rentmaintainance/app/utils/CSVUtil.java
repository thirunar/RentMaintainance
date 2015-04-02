package com.rentmaintainance.app.utils;

import android.database.Cursor;
import com.csvreader.CsvWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


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
}
