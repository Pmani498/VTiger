package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class EnterdataToExcel {
	public static void main(String[] args) throws IOException {
		FileInputStream fisExcel = new FileInputStream("./src/test/resources/Testdata.xlsx");
		Workbook workbook = WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("Practice");
		Row row = sheet.getRow(5);
		Cell cell = row.createCell(1);
		cell.setCellValue("Pass");
		FileOutputStream fosExcel = new FileOutputStream("./src/test/resources/Testdata.xlsx");
		workbook.write(fosExcel);
		System.out.println("Data is printed");
		workbook.close();

	}

}
