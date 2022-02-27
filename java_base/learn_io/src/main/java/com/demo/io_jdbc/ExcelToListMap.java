package com.demo.io_jdbc;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 将excel存储为list map对象
 * @Author junode
 * @Date 2020/12/2
 */
public class ExcelToListMap {

    public static void main(String[] args) {
        String filePath = "D:\\demo/uploadGroupData2.xlsx";
        try {
            List<Map> maps = ExcelToListMap.readFile(filePath);
            maps.stream().map(k->k.keySet()).forEach(k-> System.out.println(k));
            maps.stream().map(k->k.values()).forEach(v-> System.out.println(v));
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }




    /**
     * @Description 读取EXCEL文件，以表头作为key值，拼接成list map对象
     * @param file_path 文件路径
     * @return
     * @Author junode
     * @Date 2020/12/2
     **/
    public static List<Map> readFile(String file_path) throws IOException, InvalidFormatException {
        // 总行数
        int totalRows = 0;
        // 总列数
        int totalCells = 0;

        // 打开指定位置的Excel文件
//        Workbook wb = new XSSFWorkbook(new FileInputStream(new File(file_path)));

        Workbook wb = WorkbookFactory.create(new FileInputStream(new File(file_path)));
        // 获得模板总sheet页数
        int totalSheets = wb.getNumberOfSheets();

        ArrayList<Map> result = new ArrayList<>();

        for (int j = 0; j < totalSheets; j++) {
            Sheet sheet = wb.getSheetAt(j);
            int lastRowNum = sheet.getLastRowNum();

            // 第0行为excel表头，作为list map的key值。
            Row row0 = sheet.getRow(0);
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                short lastCellNum = row.getLastCellNum();
                Map map = new HashMap();
                for (int k = 0; k < lastCellNum; k++) {
                    map.put(row0.getCell(k),row.getCell(k));
                }
                result.add(map);
            }
        }
        // 关文件
        return result;
    }
}
