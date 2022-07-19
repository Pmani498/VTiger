package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains all the Tested for Excel file
 * @author DELL
 *
 */
public class ExcelUtility {
	private Workbook workbook;
	private Sheet sheet;
/**
 * This method is used to initialize the excel file
 * @param excelpath
 */
	
	public void initializeExcelFile(String excelpath)
	{
		FileInputStream fisExcel;
		try {
			fisExcel = new FileInputStream(excelpath);
			workbook = WorkbookFactory.create(fisExcel);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This method is used to write the data to the Excel
	 * @param sheetName
	 * @param rowNumber
	 * @param value
	 * @param cellNumber
	 */
	public void setDataIntoExcel(String sheetName, int rowNumber, int cellNumber,String value)
	{
		Sheet sheet= workbook.getSheet(sheetName);
		sheet.getRow(rowNumber).createCell(cellNumber).setCellValue(value);
		System.out.println("Data Entered");
	}
	/**
	 * This method is used to provide data to the Excel
	 * @param excelSavepath
	 */
	public void provideDataToExcelPass(String excelSavepath)
	{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(excelSavepath);
			try {
				workbook.write(fos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	/**
	 * This method is used to read data from Excel
	 * @param sheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 */
	public String getDataFromExcel(String sheetName, int rowNumber, int cellNumber)
	{
		Sheet sheet = workbook.getSheet(sheetName);
		return new DataFormatter().formatCellValue(sheet.getRow(rowNumber).getCell(cellNumber));
	}
	
	
	/**
	 * This method is used to close workbook
	 */
	public void workbookclose()
	{
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

