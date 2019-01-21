package com.TYSS.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.TYSS.Base.BaseClass;


public class Excel_Reader {
	
	public static String path = BaseClass.xlsProperties.getProperty("module1");
    public static String sheet = BaseClass.xlsProperties.getProperty("module1Sheet");
    public static WorkbookFactory workbook;
    public static FileInputStream fis;
    public static File file;

    public static Workbook getWorkbook() throws Throwable 
    {
        fis = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(fis);

        return wb;

    }

    public static Sheet getSheet() throws Throwable 
    {
        Sheet sheetname = getWorkbook().getSheet(sheet);

        return sheetname;

    }

    public static int getRowCount() throws Throwable 
    {
        int row = getSheet().getPhysicalNumberOfRows();
        return row;

    }

   
	public static String getCellData(int rownum, String colname) throws Throwable 
    {
        String celltest = "";
        try 
        {
            int rowcount = getRowCount();
            if (rowcount <= 0)
                return "";
            int colNum = -1;
            Row row = getSheet().getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++)
            {
                if (colname.trim().equals(row.getCell(i).getStringCellValue().trim()))
                {
                    colNum = 1;
                }
            }
            if (colNum == -1) 
            {
                return "";
            }
            Cell cell = getSheet().getRow(rownum).getCell(colNum);
            if (cell == null)
                return "";
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) 
            {
                celltest = String.valueOf(cell.getNumericCellValue());
            }
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                celltest = cell.getStringCellValue();
            }
            if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                celltest = String.valueOf(cell.getBooleanCellValue());
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());

        }

        return celltest;

    }
    public static String getCellData(int rownum, int colnum) throws Throwable
    {
        String celltest = "";
        try 
        {
            int rowcount = getRowCount();
            if (rowcount <= 0)
                return "";
            int colNum = -1;
            Row row = getSheet().getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++)
            {
                
            }
            if (colNum == -1) 
            
                return "";
            
            Cell cell = getSheet().getRow(rownum).getCell(colNum);
            if (cell == null)
                return "";
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                celltest = String.valueOf(cell.getNumericCellValue());
            }
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                celltest = cell.getStringCellValue();
            }
            if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                celltest = String.valueOf(cell.getBooleanCellValue());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return celltest;

    }

    public static void setCellData(String columnname, int rownum, String data) throws Throwable {
    
        try {
            Workbook wb = getWorkbook(); 
            Sheet sh = getSheet();
            int col=-1;
            Row row = sh.getRow(0);
            for (int i =0 ; i <= row.getLastCellNum(); i++) {
            if(row.getCell(i).getStringCellValue().trim().equals(columnname))
            {
                col=i;
            }
            }
                row=sh.getRow(rownum);
                if(row==null)
                {
                    
                    row=sh.createRow(rownum);
                }
                Cell cell = row.getCell(col);
                if(cell==null)
                {
                    cell = row.createCell(col);
                }
                cell.setAsActiveCell();
                cell.setCellValue(data);
                FileOutputStream fos = new FileOutputStream(path);
                wb.write(fos);
        }
        catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

            }
    public static int getCellRowNum(String colname,String testcasename) throws Throwable
    {
        int rownum = -1;
        for(int i=1;i<getRowCount();i++)
        {
            if(getCellData(i, colname).equalsIgnoreCase(testcasename))
            {
                rownum=i;
            }
        }
        return rownum;
        
    }

}
