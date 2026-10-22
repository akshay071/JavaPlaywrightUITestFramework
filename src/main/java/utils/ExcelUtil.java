package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {

    private static final String FILE_PATH = System.getProperty("user.dir") + "src/main/resources/TestData/TestData.xlsx";

    private static final String SHEET_NAME = "TestData";

    private ExcelUtil(){ }

    public static Map<String, String> getTestData(String testCaseId){
        Map<String, String> testData = new HashMap<>();
        try(FileInputStream file = new FileInputStream(FILE_PATH);
                Workbook workbook = new XSSFWorkbook(file)){

            Sheet sheet = workbook.getSheet(SHEET_NAME);

            if(sheet == null){
                throw new RuntimeException("Sheet not found: "+SHEET_NAME);
            }

            Row headerRow = sheet.getRow(0);

            if(headerRow == null){
                throw new RuntimeException("Header row not found");
            }

            DataFormatter formatter = new DataFormatter();

            for(int i=1;i<= sheet.getLastRowNum(); i++){

                Row dataRow = sheet.getRow(i);

                if(dataRow == null){

                    continue;

                }

                String currentTestCaseID = formatter.formatCellValue(dataRow.getCell(0)).trim();

                if(currentTestCaseID.equalsIgnoreCase(testCaseId)){

                    for(int j=0; j< headerRow.getLastCellNum(); j++){

                        String columnName = formatter.formatCellValue(headerRow.getCell(j)).trim();

                        String columnValue = formatter.formatCellValue(dataRow.getCell(j));

                        testData.put(columnName,columnValue);
                    }
                    return testData;
                }
            }

            throw  new RuntimeException("TestCaseID not found: "+ testCaseId);
        }catch (IOException e){
            throw new RuntimeException("Unable to read Excel file: "+ FILE_PATH, e);
        }
    }
}
