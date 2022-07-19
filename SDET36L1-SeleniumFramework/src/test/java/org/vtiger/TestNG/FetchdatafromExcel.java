package org.vtiger.TestNG;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchdatafromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		DataFormatter  dataFormatter=new DataFormatter();
		FileInputStream fisExcel=new FileInputStream("./src/test/resources/TestDatafile.xlsx");
		Workbook workbook=WorkbookFactory.create(fisExcel);
		Sheet sheet=workbook.getSheet("Contacts");
		for(int i=0;i<=sheet.getLastRowNum();i++)
		{
			String data=dataFormatter.formatCellValue(sheet.getRow(i).getCell(1));
			if(data.equalsIgnoreCase("CompaignName"))
			{
				System.out.println(sheet.getRow(i+2).getCell(1));
				break;
			}
		}
		workbook.close();
	}
}
