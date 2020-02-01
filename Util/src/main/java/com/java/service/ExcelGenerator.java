package com.java.service;

import com.java.config.AppConfig;
import net.bytebuddy.utility.RandomString;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class ExcelGenerator {

    private String[] columnHeaders;
    private Object[] columnFooter;
    private ArrayList<Object[]> rows;
    private ArrayList<Object[]> priorHeaderArray;
    private Workbook workbook;

    public ExcelGenerator(String[] columnHeaders, ArrayList<Object[]> rows) {
        this.columnHeaders = columnHeaders;
        this.rows = rows;
    }

    public ExcelGenerator(String[] columnHeaders, ArrayList<Object[]> rows, Object[] columnFooter) {
        this.columnHeaders = columnHeaders;
        this.rows = rows;
        this.columnFooter = columnFooter;
    }

    public ExcelGenerator(ArrayList<Object[]> priorHeaderArray,String[] columnHeaders, ArrayList<Object[]> rows, Object[] columnFooter) {
        this.priorHeaderArray = priorHeaderArray;
        this.columnHeaders = columnHeaders;
        this.rows = rows;
        this.columnFooter = columnFooter;
    }

    public File getExcelFile() throws IOException{
        return this.generateExcel();
    }

    public byte[] getExcelFileByteArray() throws IOException{
        return Files.readAllBytes(this.generateExcel().toPath());
    }

    private File generateExcel() throws IOException {

        workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet 1");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);

        if(priorHeaderArray != null){
            this.generateExcelRows(sheet,priorHeaderArray);
        }

        if(columnHeaders != null){
            this.generateExcelHeader(sheet,columnHeaders);
        }

//        CellStyle style = workbook.createCellStyle();
//        style.setWrapText(true);

        if(rows != null){
            this.generateExcelRows(sheet,rows);
        }

        if(columnFooter != null){
            this.generateExcelFooter(sheet,columnFooter);
        }

        String fileSavingLocation = AppConfig.UPLOAD_BASE_DIR + "excels/";
        File filePath = new File(fileSavingLocation);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        String filename = fileSavingLocation + "excel-" + new RandomString(5).nextString() +".xlsx";

        FileOutputStream outputStream = new FileOutputStream(filename);
        workbook.write(outputStream);
        workbook.close();

        return new File(filename);
    }

    private Sheet generateExcelRows(Sheet sheet, ArrayList<Object[]> arraylist){
        for (int i = 0; i < arraylist.size(); i++) {
            Object[] columns = arraylist.get(i);
            Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());

            int rootRowNo = row.getRowNum();
            for (int j = 0; j < columns.length; j++) {
                Object o = columns[j];
                Cell cell = row.createCell(j);

                if(o instanceof ArrayList){
                    ArrayList currentRowList = (ArrayList) o;
                    for (int z = 0; z < currentRowList.size(); z++) {
                        row = sheet.getRow(rootRowNo + z);
                        row = row == null? sheet.createRow(rootRowNo + z) : row;
                        Object currentRowObj = currentRowList.get(z);
                        Cell currentRowCell = row.createCell(j);
                        currentRowCell.setCellValue(currentRowObj == null? "" : currentRowObj.toString());
                    }
                }else {
                    cell.setCellValue(o == null? "" : o.toString());
                }
//                cell.setCellStyle(style);
            }
        }
        return sheet;
    }

    private Sheet generateExcelHeader(Sheet sheet, String[] arraylist){
        CellStyle headerStyle = workbook.createCellStyle();
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        headerStyle.setFont(font);

        Row header = sheet.createRow(sheet.getPhysicalNumberOfRows());
        for (int col = 0; col < arraylist.length; col++) {
            Cell headerCell = header.createCell(col);
            headerCell.setCellValue(arraylist[col]);
            headerCell.setCellStyle(headerStyle);
        }
        return sheet;
    }

    private Sheet generateExcelFooter(Sheet sheet, Object[] arraylist){
        CellStyle footerStyle = workbook.createCellStyle();
        XSSFFont footerFont = ((XSSFWorkbook) workbook).createFont();
        footerFont.setBold(true);
        footerStyle.setFont(footerFont);

        Row footer = sheet.createRow(sheet.getPhysicalNumberOfRows());
        for (int col = 0; col < arraylist.length; col++) {
            Cell footerCell = footer.createCell(col);
            footerCell.setCellValue(arraylist[col].toString());
            footerCell.setCellStyle(footerStyle);
        }
        return sheet;
    }

}
