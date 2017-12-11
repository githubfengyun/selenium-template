package com.changan.ddt;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class ExcelSheetDriver {
    static Sheet wrksheet;
    static Workbook wrkbook =null;
    static Hashtable dict= new Hashtable();
    //Create a Constructor
    public ExcelSheetDriver(String ExcelSheetPath) throws BiffException, IOException
    {
        //Initialize
        wrkbook = Workbook.getWorkbook(new File(ExcelSheetPath));
        //For Demo purpose the excel sheet path is hardcoded, but not recommended :)
        wrksheet = wrkbook.getSheet("Example Test");
    }

    //Returns the Number of Rows
    public static int rowCount()
    {
        return wrksheet.getRows();
    }

    //Returns the Cell value by taking row and Column values as argument
    public static String readCell(int column,int row)
    {
        return wrksheet.getCell(column,row).getContents();
    }

    public static String readCell(String colName, int row) {
        return readCell(getCell(colName), row);
    }

    //Create Column Dictionary to hold all the Column Names
    public static void columnDictionary()
    {
        //Iterate through all the columns in the Excel sheet and store the value in Hashtable
        for(int col=0;col < wrksheet.getColumns();col++)
        {
            dict.put(readCell(col,0), col);
        }
    }

    //Read Column Names
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
