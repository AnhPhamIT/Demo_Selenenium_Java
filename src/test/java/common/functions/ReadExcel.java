package common.functions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	// public static final Logger logger = LogManager.getLogger("Read Data From
	// Excel file");

	private String dataTest = "data";

	public String getConfigData(String key) {
		Object[][] arrayObject = getExcelData(System.getProperty("user.dir") + File.separator + dataTest + File.separator + "data.xls", "Configuration");
		String value = null;
		for (int i = 0; i < arrayObject.length; i++) {
			if (arrayObject[i][0].toString().toLowerCase().equals(key.toLowerCase())) {
				value = arrayObject[i][1].toString();
				break;
			}
		}
		if (value == null) {
			System.out.println("Unable to find test case");
		}
		return value;
	}

	public static String[][] getExcelData(String filePath, String sheet) {
		String[][] arrayExcelData = null;

		try {
			FileInputStream fs = new FileInputStream(filePath);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheet);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();

			arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];

			for (int i = 1; i < totalNoOfRows; i++) {
				for (int j = 0; j < totalNoOfCols; j++) {
					arrayExcelData[i - 1][j] = sh.getCell(j, i).getContents();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}

}
