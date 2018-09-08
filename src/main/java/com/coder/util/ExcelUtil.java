package com.coder.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.web.multipart.MultipartFile;

public final class ExcelUtil {

    private static final String XLSX = "xlsx";
    private static final String XLS = "xls";

    /**
     * 判断得到的workbook是不是excel
     * @param workbook
     * @return
     */
    public static boolean isExcel(Workbook workbook){
        return workbook != null;
    }

    /**
     * 打开.xls文件
     * 兼容.xlsx
     * @param file
     * @param filename
     * @return
     * @throws Exception
     */
    public static Workbook openWorkbookForRead(File file, String filename) throws Exception{

        if(file == null || StringUtils.isNullOrEmpty(filename)){
            return null;
        }
        Workbook workbook = null;
        try {
            //默认读取excel2007
            if (filename.endsWith(XLSX)) {
                workbook = new XSSFWorkbook(new FileInputStream(file));
            }else if(filename.endsWith(XLS)) {
                //读取excel2003
                workbook = new HSSFWorkbook(new FileInputStream(file));
            }
        } catch (OfficeXmlFileException e) {
            return null;
        }
        return workbook;
    }

    public static Workbook openWorkbookForRead(MultipartFile file, String filename){

        if(file == null || StringUtils.isNullOrEmpty(filename)){
            return null;
        }
        Workbook workbook = null;
        try {
            //默认读取excel2007
            if (filename.endsWith(XLSX)) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }else if(filename.endsWith(XLS)) {
                //读取excel2003
                workbook = new HSSFWorkbook(file.getInputStream());
            }
        } catch (OfficeXmlFileException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * 定义一个方法用来处理cell的类型，来返回不同类型的值
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell){
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_ERROR:
                // 错误
            case Cell.CELL_TYPE_BLANK:
                // 空值
                return null;
            case Cell.CELL_TYPE_BOOLEAN:
                // 布尔型
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                // 数字
                double d = cell.getNumericCellValue();
                //首先判断当前cell是否为日期
                return DateUtil.isCellDateFormatted(cell) ? HSSFDateUtil.getJavaDate(d):d;
            case Cell.CELL_TYPE_STRING:
                // 字符串型
                return cell.getStringCellValue().trim();
            case Cell.CELL_TYPE_FORMULA:
                // 公式型
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String value = cell.getStringCellValue();
                if (StringUtils.isNullOrEmpty(value)) {
                    value = value.replaceAll("#N/A", StringUtils.EMPTY).trim();
                }
                return value;
            default:
                return null;
        }
    }
}
