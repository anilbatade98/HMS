package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	
//	protected WebDriver driver;
	private static  Sheet sheet;
	
	public static void captureScreenshot(WebDriver driver, String testName,String testID) throws IOException {
		LocalDateTime dt = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss");
		String dateTime = formatter.format(dt);
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destination = new File("test-output\\screenshot\\"+testName+"_"+testID+"_"+dateTime+".png");
		FileHandler.copy(source, destination);
		System.out.println("Screenshot taken");
	}
	
	public static Sheet getSheet(String path,String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(path);
		return WorkbookFactory.create(file).getSheet(sheetName);
	}
	
	public static int getRowLength(Sheet sheet) {
		return sheet.getLastRowNum();
	}
	
	public static int getRowLength(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\Hospital.xlsx"); 
		int cell = WorkbookFactory.create(file).getSheet(sheetName).getLastRowNum();
		return cell;
	}
	
	public static int getColumnLength(Sheet sheet) {
		return sheet.getRow(0).getLastCellNum();
	}
	
	public static int getColumnLength(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\Hospital.xlsx"); 
		int cell = WorkbookFactory.create(file).getSheet(sheetName).getRow(0).getLastCellNum();
		return cell;
	}
	
	public static String [][] getExcelData(String sheetName, int row, int column) throws EncryptedDocumentException, IOException {
		
		String [][] data = new String [row][column];
		
		for(int i=0; i<row; i++) {
			for(int j=1; j<=column; j++) {
				try {
					data[i][(j-1)] = sheet.getRow(row).getCell(j).getStringCellValue();
				}
				catch(Exception e) {
					System.out.println(e);
				}
				
			}
		}
		
		return data;
	}
	
	public static String getExlData(String sheetName, int row, int column) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\Hospital.xlsx"); 
		Cell cell = WorkbookFactory.create(file).getSheet(sheetName).getRow(row).getCell(column);
		String data = "";
		DataFormatter format = new DataFormatter();
		try {
			data = format.formatCellValue(cell);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
}
