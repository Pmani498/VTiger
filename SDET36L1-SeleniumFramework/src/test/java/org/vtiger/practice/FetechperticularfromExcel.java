package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetechperticularfromExcel {

	public static void main(String[] args)  {
		DataFormatter dataformatter=new DataFormatter();
		FileInputStream fis = null;
		Sheet sheet = null;
		Workbook workbook = null;
		
			try {
				fis = new FileInputStream("./src/test/resources/TestDatafile.xlsx");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				workbook = WorkbookFactory.create(fis);
				sheet=workbook.getSheet("Contacts");
				
			} catch (EncryptedDocumentException|IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<=sheet.getLastRowNum();i++)
			{
				String data=dataformatter.formatCellValue((sheet.getRow(i).getCell(1)));
				if(data.equalsIgnoreCase("CompaignName"))
				{
					System.out.println(sheet.getRow(i+2).getCell(1));
					break;
				}
				try {
					workbook.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
			
		
}

