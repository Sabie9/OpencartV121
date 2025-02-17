package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		String path=".\\testData\\Opencart_LoginData.xlsx"; //storing path of testData file as a string
		
		ExcelUtility xlutil= new ExcelUtility(path); //creating ExcelUtility object with the path
		
		int totalrows= xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]= new String[totalrows][totalcols]; // creating 2D string array to store the test data table
		
		for(int i=1;i<=totalrows;i++) { //In excel the first row index starts from 1
			for(int j=0;j<totalcols;j++) { //In excel the first column index starts from 0
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		
		return logindata;
	}
}
