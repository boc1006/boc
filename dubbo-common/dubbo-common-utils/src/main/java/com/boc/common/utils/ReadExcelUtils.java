package com.boc.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
/**
 * 
 * <p>@Title ReadExcelUtils</p>
 * <p>@Description Excel 读取工具 List<Map<String, Object>></p>
 * <p>@Version 1.0.0 版本号</p>
 * <p>@author wenjie</p>
 * <p>@date 2017</p>
 * <p>wenjie@dgg.net</p>
 * <p>Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */
public class ReadExcelUtils {
	
	public static List<Map<String, Object>> ReadExcel(InputStream inputStream) throws IOException {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		// 获取文件流
		POIFSFileSystem pois = new POIFSFileSystem(inputStream);
		// 新建WorkBook
		HSSFWorkbook wb = new HSSFWorkbook(pois);
		// 获取Sheet（工作薄）总个数
		int sheetNumber = wb.getNumberOfSheets();
		for (int i = 0; i < sheetNumber; i++) {
			// 获取Sheet（工作薄）
			HSSFSheet sheet = wb.getSheetAt(i);
			Iterator<Row> rows = sheet.iterator();
			while (rows.hasNext()) {
				int rowN = 0;
				// 获得行数据
				map = new HashMap<String, Object>();
				Row row = rows.next();
				// 获取每个cell
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					Object cellValue = getCellValue(cell);
					map.put("row" + rowN, cellValue);
					rowN++;
				}
				datas.add(map);
			}
		}
		if(inputStream!=null){inputStream.close();}
		return datas;
	}
	
	public static List<Map<String, Object>> ReadExcel(InputStream inputStream,Integer asKeyRow,Integer startRow) throws IOException {
		if((asKeyRow!=null && startRow!=null) && (asKeyRow >= startRow)) {
			throw new IndexOutOfBoundsException(String.format("keys num out of define max start num %s", String.valueOf(startRow)));
		}
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		// 获取文件流
		POIFSFileSystem pois = new POIFSFileSystem(inputStream);
		// 新建WorkBook
		HSSFWorkbook wb = new HSSFWorkbook(pois);
		// 获取Sheet（工作薄）总个数
		int sheetNumber = wb.getNumberOfSheets();
		for (int i = 0; i < sheetNumber; i++) {
			// 获取Sheet（工作薄）
			HSSFSheet sheet = wb.getSheetAt(i);
			Iterator<Row> rows = sheet.iterator();
			List<String> headers = new ArrayList<String>();
			int rowsN = 0;
			while (rows.hasNext()) {
				// 获得行数据
				Row row = rows.next();
				int cellsN = 0;
				if (asKeyRow != null && rowsN == asKeyRow) {
					Iterator<Cell> cells = row.cellIterator();
					while (cells.hasNext()) {
						Cell cell = cells.next();
						Object key = getCellValue(cell);
						headers.add(key.toString());
						cellsN++;
					}
					rowsN++;
				} else if (startRow != null && rowsN < startRow) {
					rowsN++;
					continue;
				} else {
					map = new HashMap<String, Object>();
					Iterator<Cell> cells = row.cellIterator();
					while (cells.hasNext()) {
						Cell cell = cells.next();
						Object cellValue = getCellValue(cell);
						String key="";
						if(headers.size()>cellsN){
							key=headers.get(cellsN);
						}else{
							key="row"+cellsN;
						}
						map.put(key, cellValue);
						cellsN++;
					}
					rowsN++;
					datas.add(map);
				}
			}
		}
		if(inputStream!=null){inputStream.close();}
		return datas;
	}
	
	public static List<Map<String, Object>> ReadExcel(FileInputStream fileInputStream,int rowsNumber,int cellsNumber) throws IOException {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		// 获取文件流
		POIFSFileSystem pois = new POIFSFileSystem(fileInputStream);
		// 新建WorkBook
		HSSFWorkbook wb = new HSSFWorkbook(pois);
		// 获取Sheet（工作薄）总个数
		int sheetNumber = wb.getNumberOfSheets();
		for (int i = 0; i < sheetNumber; i++) {
			// 获取Sheet（工作薄）
			HSSFSheet sheet = wb.getSheetAt(i);
			Iterator<Row> rows = sheet.iterator();
			int rowsN = 0;
			while (rows.hasNext()) {
				if (rowsN > rowsNumber) break;
				// 获得行数据
				map = new HashMap<String, Object>();
				Row row = rows.next();
				// 获取每个cell
				Iterator<Cell> cells = row.cellIterator();
				int rowN = 0;
				while (cells.hasNext()) {
					Cell cell = cells.next();
					Object cellValue = getCellValue(cell);
					map.put("row" + rowN, cellValue);
					rowN++;
					if (rowN > cellsNumber) break;
				}
				datas.add(map);
				rowsN++;
			}
		}
		if(fileInputStream!=null){fileInputStream.close();}
		return datas;
	}

	public static List<Map<String, Object>> ReadExcel(FileInputStream fileInputStream, int sheetIndex, int rowsNumber,int cellsNumber) throws IOException {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		// 获取文件流
		POIFSFileSystem pois = new POIFSFileSystem(fileInputStream);
		// 新建WorkBook
		HSSFWorkbook wb = new HSSFWorkbook(pois);
		// 获取Sheet（工作薄）总个数
		int sheetNumber = wb.getNumberOfSheets();
		if (sheetIndex > sheetNumber) {
			throw new IndexOutOfBoundsException(String.format("sheet num out of define max sheet num %s", String.valueOf(sheetNumber)));
		}
		// 获取Sheet（工作薄）
		HSSFSheet sheet = wb.getSheetAt(sheetIndex);
		Iterator<Row> rows = sheet.iterator();
		int rowsN = 0;
		while (rows.hasNext()) {
			if (rowsN > rowsNumber) break;
			//获得行数据
			Row row = rows.next();
			map = new HashMap<String, Object>();
			// 获取row的每个cell
			int rowN = 0;
			Iterator<Cell> cells = row.cellIterator();
			while (cells.hasNext()) {
				Cell cell = cells.next();
				Object cellValue = getCellValue(cell);
				map.put("row" + rowN, cellValue);
				rowN++;
				if (rowN > cellsNumber) break;
			}
			rowsN++;
			datas.add(map);
		}
		if(fileInputStream!=null){fileInputStream.close();}
		return datas;
	}

	public static Object getCellValue(Cell cell) {
		Object obj;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			obj = "";
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_ERROR:
			obj = null;
			break;
		case Cell.CELL_TYPE_FORMULA:
			Workbook wb = cell.getSheet().getWorkbook();
			CreationHelper crateHelper = wb.getCreationHelper();
			FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
			obj = getCellValue(evaluator.evaluateInCell(cell));
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				obj = cell.getDateCellValue();
			} else {
				// obj = cell.getNumericCellValue();
				obj = NumberToTextConverter.toText(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_STRING:
			obj = cell.getRichStringCellValue().getString();
			break;
		default:
			obj = null;
		}
		return obj;
	}

}