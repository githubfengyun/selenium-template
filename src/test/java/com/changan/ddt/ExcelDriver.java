package com.changan.ddt;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

public class ExcelDriver {
    static Sheet  wrksheet;
    static Workbook wrkbook =null;
    static Hashtable dict= new Hashtable();

    //Create a Constructor
    public ExcelDriver(String ExcelSheetPath) throws IOException {
        //Initialize
        File excelFile = new File(ExcelSheetPath);
        FileInputStream excelFileInputStream = new FileInputStream(excelFile);
        wrkbook = new XSSFWorkbook(excelFileInputStream);
        //For Demo purpose the excel sheet path is hardcoded, but not recommended :
    }

    //Returns the Number of Rows
    public static int rowCount()
    {
        return wrksheet.getLastRowNum();
    }

    //Returns the Cell value by taking row and Column values as argument
    public static String readCell(int column,int row)
    {
        return wrksheet.getRow(row).getCell(column).getStringCellValue();
    }

    public static String readCell(String colName, int row) {
        return readCell(getCell(colName), row);
    }

    //Create Column Dictionary to hold all the Column Names
    public static void columnDictionary(String sheetName)
    {
        wrksheet = wrkbook.getSheet(sheetName);
        //Iterate through all the columns in the Excel sheet and store the value in Hashtable
        int colCount = wrksheet.getRow(0).getLastCellNum();
        for(int col=0;col < colCount;col++)
        {
            dict.put(readCell(col,0), col);
        }
    }

    //Read Column Names and output the column index
    public static int getCell(String colName)
    {
        try {
            int value;
            value = ((Integer) dict.get(colName)).intValue();
            return value;
        } catch (NullPointerException e) {
            return (0);

        }
    }
}
