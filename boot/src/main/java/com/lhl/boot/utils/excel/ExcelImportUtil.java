//package com.lhl.boot.utils.excel;
//
//import com.bm.framework.util.DateUtil;
//import org.apache.poi.hssf.usermodel.*;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.math.BigDecimal;
//import java.util.*;
//
///**
// * excel导入工具类
// *
// * @author zhenjiang.chen
// *
// */
//public class ExcelImportUtil {
//	/**
//	 * 解析excel
//	 *
//	 * @param file
//	 * @return
//	 * @author zhaolimin
//	 * @date 2014-9-2 上午9:44:05
//	 *
//	 */
//	public static List<Map<String, Object>> analyzeExcelForlistMap(File file) {
//		try {
//			// 文件二进制输入流
//			InputStream in = new FileInputStream(file);
//			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
//			List<String> titleList = new ArrayList<String>();
//			// 读取excel工作簿
////          InputStream is = new FileInputStream( "D:\\excel\\xls_test2.xls");
//			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
//
//			// 循环工作表Sheet
//			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
//				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
//				if (hssfSheet == null) {
//					continue;
//				}
//
//				// 循环行Row
//				for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
//					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
//					if (hssfRow == null) {
//						break;
//					}
//					// 循环列Cell
//					Map<String, Object> dataMap = new HashMap<String, Object>();
//					for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
//						HSSFCell hssfCell = hssfRow.getCell((short) cellNum);
////                  if(hssfCell == null){
////                    continue;
////                  }
//						if (rowNum == 0) {
//							titleList.add(getValue(hssfCell));
//						} else {
//							dataMap.put(titleList.get(cellNum), getValue(hssfCell));
//						}
//					}
//					dataList.add(dataMap);
//				}
//			}
//			// 如果is不为空，则关闭InputSteam文件输入流
//			if (in != null) {
//				in.close();
//			}
//			return dataList;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	/**
//	 * 解析excel
//	 *
//	 * @param file
//	 * @return
//	 * @author zhaolimin
//	 * @date 2014-9-2 上午9:44:05
//	 *
//	 */
//	public static List<List<Object>> analyzeExcelForList(File file) {
//		try {
//			InputStream in = new FileInputStream(file);
//			return ExcelImportUtil.analyzeExcelForList(in);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return new ArrayList<List<Object>>();
//		}
//	}
//
//	/**
//	 *
//	 * @param in 文件二进制输入流
//	 * @return
//	 * @author maozhaoyuan
//	 * @date 2015-3-23 上午9:51:28
//	 *
//	 */
//	public static List<List<Object>> analyzeExcelForList(InputStream in) {
//		try {
//			List<List<Object>> dataList = new ArrayList<List<Object>>();
//
//			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
//
//			// 循环工作表Sheet
//			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
//				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
//				if (hssfSheet == null) {
//					continue;
//				}
//
//				// 循环行Row
//				for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
//					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
//					if (hssfRow == null) {
//						break;
//					}
//					// 循环列Cell
//					List<Object> dList = new ArrayList<Object>();
//					for (int cellNum = 0; cellNum < hssfRow.getLastCellNum(); cellNum++) {
//						HSSFCell hssfCell = hssfRow.getCell((short) cellNum);
//						dList.add(getValue(hssfCell));
//					}
//					dataList.add(dList);
//				}
//			}
//			// 如果is不为空，则关闭InputSteam文件输入流
//			if (in != null) {
//				in.close();
//			}
//			return dataList;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ArrayList<List<Object>>();
//		}
//	}
//
//	private static String getValue(HSSFCell hssfCell) {
//		String cellValue = null;
//		if (hssfCell == null) {
//			return null;
//		}
//		if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
//			if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
//				Date date = hssfCell.getDateCellValue();
//				cellValue = DateUtil.parseDateToString(date, DateUtil.C_YYYY_MM_DD);
//			} else {
//				BigDecimal bd = new BigDecimal(hssfCell.getNumericCellValue());
//				cellValue = bd.toPlainString();
//			}
//		} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
//			cellValue = hssfCell.getStringCellValue();
//		}
//		return cellValue;
//	}
//}
