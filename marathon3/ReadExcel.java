package marathon3;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] readData(String fileName) throws IOException {
		
		//Set the path 
		XSSFWorkbook workbook = new XSSFWorkbook("Data/"+fileName+".xlsx");
		
		//Get the sheet
		XSSFSheet sheet = workbook.getSheetAt(0);

//To get all data inside excel
		//Row count
		int rowcount = sheet.getLastRowNum();
		
		//Col count
		short colcount = sheet.getRow(0).getLastCellNum();

//To integrate this excelsheet into create lead testscripts/methods
		String[][] data= new String[rowcount][colcount];
		for (int i = 1; i <= rowcount; i++) {  //replcae i=0 to i=1 to avoid header
				XSSFRow row = sheet.getRow(i);
			     for (int j = 0; j < colcount; j++) {
				   XSSFCell cell = row.getCell(j);
				   data[i-1][j]=cell.getStringCellValue();
				   //data[1][0] = Testleaf // but in createLead Testscript it has data[0][0], So change data[i][j]->data[i-1][j]
	    			System.out.println(cell);
			     }
		}
		workbook.close();
		return data;


	}
}
