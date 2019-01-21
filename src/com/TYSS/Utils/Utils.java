package com.TYSS.Utils;

public class Utils {
	
	public static boolean isTestCaseRunnable(Excel_Reader excel, String testCaseName) throws Throwable {
        boolean isExecutable = false;
        //System.out.println(excel.allRow);
        for (int x = 0; x < excel.getRowCount(); x++) {
            System.out.println((excel.getCellData(x,"TCID")) + "________________" + (excel.getCellData(x,"RunMode")));
            if (excel.getCellData(x,"TCID").equalsIgnoreCase(testCaseName)) {
                if (excel.getCellData(x,"RunMode").equalsIgnoreCase("y")) {
                    isExecutable = true;
                } else if (excel.getCellData(x,"RunMode").equalsIgnoreCase("n")) {
                    isExecutable = false;
                }
                }
            }
        return isExecutable;

    }
    public static int getRowNum(Excel_Reader excel,String id) throws Throwable
    {
        for(int x=2;x<=excel.getRowCount();x++)
        {
            String tcid=excel.getCellData(x,"TCID");
            if(tcid.equalsIgnoreCase(id))
            {
                excel=null;
                return x;
            }
            
        }
        return -1;    
    }
    public static void reportDatabaseResult(int rowNum,String data) throws Throwable
    {
        Excel_Reader.setCellData("Result",rowNum,data);
        
    }

}
