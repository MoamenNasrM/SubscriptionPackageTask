package readers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {


    static FileInputStream fis = null;
    XSSFWorkbook wb;
    XSSFSheet sheet;

    public FileInputStream getFileInputStream(String fileName)
    {
        String filePath = System.getProperty("user.dir")+ "/test-resources/test-data/" + fileName;
        //System.out.println("File Path Excel : " + filePath);
        File srcFile = new File(filePath);

        try {
            fis = new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("Test data file not found. terminating process !! : check file path of test data");
            System.exit(0);
        }
        return fis;

    }

    public Object[][] getExcelData(String fileName) throws IOException {
        fis = getFileInputStream(fileName);
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheetAt(0);

        int TotalNumberOfRows = (sheet.getLastRowNum()+1);
        int TotalNumberOfCols =  4;

        String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfCols];

        for (int i = 0; i < TotalNumberOfRows; i++)
        {
            for (int j = 0; j < TotalNumberOfCols; j++){

                XSSFRow row = sheet.getRow(i);
                arrayExcelData[i][j] = String.valueOf(row.getCell(j));

            }

        }

        wb.close();
        return arrayExcelData;

    }


    public Object getData(String fileName, int sheetnumber, int row, int column) throws IOException {
        fis = getFileInputStream(fileName);
        wb = new XSSFWorkbook(fis);
        Object data;
        sheet = wb.getSheetAt(sheetnumber);
        if (sheet.getRow(row).getCell(column).getCellType() == CellType.NUMERIC){
            data = sheet.getRow(row).getCell(column).getNumericCellValue();
        }else {
            data = sheet.getRow(row).getCell(column).getStringCellValue();
        }
        return data;
    }

}