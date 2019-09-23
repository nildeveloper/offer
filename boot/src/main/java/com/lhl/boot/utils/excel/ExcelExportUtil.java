//package com.lhl.boot.utils.excel;
//
//
//import com.lhl.boot.exception.BusinessException;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.hssf.usermodel.*;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.util.CellRangeAddress;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.lang.reflect.Field;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * <p>
// * Title:ExcelExportUtil
// * </p>
// * <p>
// * Description:excel导出工具类
// * </p>
// *
// * @author chenzhenjiang
// * @date 2016-7-18 下午7:09:45
// */
//public class ExcelExportUtil {
//    public static void exportExcel(String filePath, String fileName, HSSFWorkbook workbook) throws Exception {
//        File fileFloder = new File(filePath);
//        if (!fileFloder.exists()) {
//            fileFloder.mkdirs();
//        }
//
//        File file = new File(filePath + fileName);
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        OutputStream os = null;
//        os = new FileOutputStream(file);
//        workbook.write(os);
//        os.close();
//    }
//
//    /**
//     * excel导出方法
//     *
//     * @param sheetName  sheet名称
//     * @param dataList   数据列表-实体类list
//     * @param headers    -标题数据
//     * @param fieldNames --实体类对应的字段名列表
//     * @return
//     * @throws Exception
//     */
//    public static HSSFWorkbook exportExcel(String sheetName, List<Map<String, String>> dataList, String[] headers, String[] fieldNames)
//            throws Exception {
//        // 声明一个工作薄
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(sheetName);
//        workbook.setSheetName(0, sheetName);
//        // 设置表格默认列宽度为15个字节
//        sheet.setDefaultColumnWidth((short) 25);
//
//        // 生成一个样式
//        HSSFCellStyle style = workbook.createCellStyle();
//        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.WHITE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 生成一个字体
//        HSSFFont font = workbook.createFont();
//        font.setColor(HSSFColor.BLACK.index);
//        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        // 把字体应用到当前的样式
//        style.setFont(font);
//        // 生成并设置另一个样式
//        HSSFCellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(HSSFColor.WHITE.index);
//        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        // 生成另一个字体
//        HSSFFont font2 = workbook.createFont();
//        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // 把字体应用到当前的样式
//        style2.setFont(font2);
//
//        HSSFRow row = sheet.createRow(0);
//        HSSFCell cell;
//        for (short i = 0; i < headers.length; i++) {
//            cell = row.createCell(i);
//            cell.setCellStyle(style);
//            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//            cell.setCellValue(text.getString());
//        }
//
//        Object cell_val;
//        if (null != dataList) {
//            for (int i = 0; i < dataList.size(); i++) {
//                Map<String, String> t = dataList.get(i);
//
//                row = sheet.createRow(i + 1);
//
//                for (short j = 0; j < fieldNames.length; j++) {
//                    cell = row.createCell(j);
//                    cell.setCellStyle(style2);
//                    cell_val = t.get(fieldNames[j]);
//                    cell.setCellValue(cell_val == null ? "" : cell_val.toString());
//                }
//            }
//        }
//        return workbook;
//    }
//
//    /**
//     * excel导出方法,可合并单元格
//     *
//     * @param sheetName            sheet名称
//     * @param dataList             数据列表-实体类list
//     * @param headers              -标题数据
//     * @param fieldNames           --实体类对应的字段名列表
//     * @return
//     * @throws Exception
//     */
//    public static HSSFWorkbook exportExcelWithCellRange(
//            String sheetName, List<Map<String, String>> dataList, String[] headers, String[] fieldNames, List<CellRangeAddress> craList)
//            throws Exception {
//        // 声明一个工作薄
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(sheetName);
//        workbook.setSheetName(0, sheetName);
//        // 设置表格默认列宽度为15个字节
//        sheet.setDefaultColumnWidth((short) 25);
//
//        // 生成一个样式
//        HSSFCellStyle style = workbook.createCellStyle();
//        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.WHITE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 生成一个字体
//        HSSFFont font = workbook.createFont();
//        font.setColor(HSSFColor.BLACK.index);
//        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        // 把字体应用到当前的样式
//        style.setFont(font);
//        // 生成并设置另一个样式
//        HSSFCellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(HSSFColor.WHITE.index);
//        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        // 生成另一个字体
//        HSSFFont font2 = workbook.createFont();
//        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // 把字体应用到当前的样式
//        style2.setFont(font2);
//
//        HSSFRow row = sheet.createRow(0);
//        HSSFCell cell;
//        for (short i = 0; i < headers.length; i++) {
//            cell = row.createCell(i);
//            cell.setCellStyle(style);
//            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//            cell.setCellValue(text.getString());
//        }
//
//        Object cell_val;
//        if (null != dataList) {
//            for (int i = 0; i < dataList.size(); i++) {
//                Map<String, String> t = dataList.get(i);
//
//                row = sheet.createRow(i + 1);
//
//                for (short j = 0; j < fieldNames.length; j++) {
//                    cell = row.createCell(j);
//                    cell.setCellStyle(style2);
//                    cell_val = t.get(fieldNames[j]);
//                    cell.setCellValue(cell_val == null ? "" : cell_val.toString());
//                }
//            }
//        }
//
//        if (CollectionUtils.isNotEmpty(craList)) {
//            for (CellRangeAddress cra : craList) {
//                sheet.addMergedRegion(cra);
//            }
//        }
//
//        return workbook;
//    }
//
//    /**
//     * excel导出方法,可合并单元格
//     *
//     * @param sheetName            sheet名称
//     * @param dataList             数据列表-实体类list
//     * @param headers              -标题数据
//     * @param fieldNames           --实体类对应的字段名列表
//     * @return
//     * @throws Exception
//     */
//    public static HSSFWorkbook exportExcelObjWithCellRange(
//            String sheetName, List<Map<String, Object>> dataList, String[] headers, String[] fieldNames, List<CellRangeAddress> craList)
//            throws Exception {
//        // 声明一个工作薄
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(sheetName);
//        workbook.setSheetName(0, sheetName);
//        // 设置表格默认列宽度为15个字节
//        sheet.setDefaultColumnWidth((short) 25);
//
//        // 生成一个样式
//        HSSFCellStyle style = workbook.createCellStyle();
//        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.WHITE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 生成一个字体
//        HSSFFont font = workbook.createFont();
//        font.setColor(HSSFColor.BLACK.index);
//        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        // 把字体应用到当前的样式
//        style.setFont(font);
//        // 生成并设置另一个样式
//        HSSFCellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(HSSFColor.WHITE.index);
//        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        // 生成另一个字体
//        HSSFFont font2 = workbook.createFont();
//        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // 把字体应用到当前的样式
//        style2.setFont(font2);
//
//        HSSFRow row = sheet.createRow(0);
//        HSSFCell cell;
//        for (short i = 0; i < headers.length; i++) {
//            cell = row.createCell(i);
//            cell.setCellStyle(style);
//            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//            cell.setCellValue(text.getString());
//        }
//
//        Object cell_val;
//        if (null != dataList) {
//            for (int i = 0; i < dataList.size(); i++) {
//                Map<String, Object> t = dataList.get(i);
//
//                row = sheet.createRow(i + 1);
//
//                for (short j = 0; j < fieldNames.length; j++) {
//                    cell = row.createCell(j);
//                    cell.setCellStyle(style2);
//                    cell_val = t.get(fieldNames[j]);
//                    cell.setCellValue(cell_val == null ? "" : cell_val.toString());
//                }
//            }
//        }
//
//        if (CollectionUtils.isNotEmpty(craList)) {
//            for (CellRangeAddress cra : craList) {
//                sheet.addMergedRegion(cra);
//            }
//        }
//
//        return workbook;
//    }
//
//
//
//    /**
//     * 导出excel
//     *
//     * @param sheetName excel名字
//     * @param clazz     需要导出的表头信息class 通过@ExcelColumn来标识这些字段是需要导出的表头信息
//     * @param dataList  数据集
//     * @return org.apache.poi.hssf.usermodel.HSSFWorkbook
//     * @throws
//     * @author: shixu
//     * @date 2018/6/8 17:28
//     */
//    public static <T> HSSFWorkbook exportExcelObj(String sheetName, Class<T> clazz, List<T> dataList) {
//        // 声明一个工作薄
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(sheetName);
//        workbook.setSheetName(0, sheetName);
//        // 设置表格默认列宽度为15个字节
//        sheet.setDefaultColumnWidth((short) 25);
//
//        // 生成一个样式
//        HSSFCellStyle style = workbook.createCellStyle();
//        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.WHITE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 生成一个字体
//        HSSFFont font = workbook.createFont();
//        font.setColor(HSSFColor.BLACK.index);
//        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        // 把字体应用到当前的样式
//        style.setFont(font);
//        // 生成并设置另一个样式
//        HSSFCellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(HSSFColor.WHITE.index);
//        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        style2.setWrapText(true);
//        // 生成另一个字体
//        HSSFFont font2 = workbook.createFont();
//        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // 把字体应用到当前的样式
//        style2.setFont(font2);
//
//        HSSFRow row = sheet.createRow(0);
//        HSSFCell cell;
//
//        Field[] fields = clazz.getDeclaredFields();
//        List<Field> excelFields = new ArrayList<>(fields.length);
//        List<String> headers = new ArrayList<>(fields.length);
//
//        for (int i = 0; i < fields.length; i++) {
//            ExcelColumn annotation = fields[i].getAnnotation(ExcelColumn.class);
//            if (annotation != null) {
//                String key = annotation.key();
//                fields[i].setAccessible(true);
//                excelFields.add(fields[i]);
//                headers.add(key);
//            }
//        }
//
//        for (int i = 0; i < headers.size(); i++) {
//            cell = row.createCell(i);
//            cell.setCellStyle(style);
//            HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
//            cell.setCellValue(text.getString());
//        }
//
//        Object cell_val;
//        try {
//            if (CollectionUtils.isNotEmpty(dataList)) {
//                for (int i = 0; i < dataList.size(); i++) {
//                    T t = dataList.get(i);
//                    row = sheet.createRow(i + 1);
//                    for (int j = 0; j < excelFields.size(); j++) {
//                        cell = row.createCell(j);
//                        cell.setCellStyle(style2);
//                        excelFields.get(j).setAccessible(true);
//                        cell_val = excelFields.get(j).get(t);
//                        cell.setCellValue(cell_val == null ? "" : cell_val.toString());
//                    }
//                }
//            }
//        } catch (IllegalAccessException e) {
//            throw new BusinessException("导出excel异常"+e);
//        }
//        return workbook;
//    }
//
//    /**
//     * excel导出方法
//     *
//     * @param sheetName  sheet名称
//     * @param dataList   数据列表-实体类list
//     * @param headers    -标题数据
//     * @param fieldNames --实体类对应的字段名列表
//     * @return
//     * @throws Exception
//     */
//    public static HSSFWorkbook exportExcelObj(HSSFWorkbook workbook,String sheetName, List<Map<String, Object>> dataList, String[] headers, String[] fieldNames)
//            throws Exception {
////        // 声明一个工作薄
////        HSSFWorkbook workbook = new HSSFWorkbook();
//        // 生成一个表格
//        HSSFSheet sheet = workbook.createSheet(sheetName);
////        workbook.setSheetName(0, sheetName);
//        // 设置表格默认列宽度为15个字节
//        sheet.setDefaultColumnWidth((short) 10);
//
//        // 生成一个样式
//        HSSFCellStyle style = workbook.createCellStyle();
//        // 设置这些样式
//        style.setFillForegroundColor(HSSFColor.WHITE.index);
//        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        // 生成一个字体
//        HSSFFont font = workbook.createFont();
//        font.setColor(HSSFColor.BLACK.index);
//        font.setFontHeightInPoints((short) 12);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        // 把字体应用到当前的样式
//        style.setFont(font);
//        // 生成并设置另一个样式
//        HSSFCellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(HSSFColor.WHITE.index);
//        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        style2.setWrapText(true);
//        // 生成另一个字体
//        HSSFFont font2 = workbook.createFont();
//        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // 把字体应用到当前的样式
//        style2.setFont(font2);
//
//        HSSFRow row = sheet.createRow(0);
//        HSSFCell cell;
//        for (short i = 0; i < headers.length; i++) {
//            cell = row.createCell(i);
//            cell.setCellStyle(style);
//            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//            cell.setCellValue(text.getString());
//        }
//
//        Object cell_val;
//        if (null != dataList) {
//            for (int i = 0; i < dataList.size(); i++) {
//                Map<String, Object> t = dataList.get(i);
//
//                row = sheet.createRow(i + 1);
//
//                for (short j = 0; j < fieldNames.length; j++) {
//                    cell = row.createCell(j);
//                    cell.setCellStyle(style2);
//                    cell_val = t.get(fieldNames[j]);
//                    cell.setCellValue(cell_val == null ? "" : cell_val.toString());
//                }
//            }
//        }
//        return workbook;
//
//    }
//
//
//    /**
//     * 下载excel文件
//     *
//     * @param res
//     * @param workbook
//     * @param filename
//     * @throws Exception
//     */
//    public static void downExcel(HttpServletRequest request, HttpServletResponse res, HSSFWorkbook workbook, String filename) throws Exception {
//        filename = URLEncoder.encode(filename, "utf-8");
//        filename = StringUtils.replace(filename, "+", "%20");
//        ServletOutputStream os = null;
//        try {
//            res.reset();
//            res.setContentType("application/msexcel");
//            res.setHeader("Content-Disposition", "attachment;Filename=" + filename);
//            os = res.getOutputStream();
//            workbook.write(os);
//            os.flush();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            if (null != os) {
//                os.close();
//            }
//        }
//    }
//}
