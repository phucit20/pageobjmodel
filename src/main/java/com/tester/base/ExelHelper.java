package com.tester.base;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExelHelper {
    private Workbook wb;
    private Sheet sh;
    private Row ro;
    private Cell ce;
    private FileInputStream fi;
    private FileOutputStream fo;
    private String ExelPath;
    public void setExelFile(String ExelFilePath, String SheetName) throws IOException {
        File f = new File(ExelFilePath);
        if (!f.exists()){
            f.createNewFile();
            System.out.println("File is Create");
        }
        fi = new FileInputStream(f);
        wb = WorkbookFactory.create(fi);
        sh = wb.getSheet(SheetName);
        if (sh == null){
            System.out.println("Please create Sheet");
        }
    }
    public String getCell(int colnum, int rownum){
        ro = sh.getRow(rownum);
        if (ro == null) { // Nếu hàng không tồn tại, trả về rỗng
            return "";
        }
        ce = ro.getCell(colnum);
        if (ce == null) { // Nếu ô không tồn tại, trả về rỗng
            return "";
        }
        String cellData = "";
        switch (ce.getCellType()){
            case  STRING : cellData = ce.getStringCellValue();
                break;
            case NUMERIC:  if (DateUtil.isCellDateFormatted(ce))
            {
                cellData = String.valueOf(ce.getDateCellValue());
            }
            else
            {
                cellData = String.valueOf((long)ce.getNumericCellValue());
            }
                break;
            case BOOLEAN : cellData = Boolean.toString(ce.getBooleanCellValue());
                break;
            case BLANK : cellData = "";
            default: cellData="";
                break;
        }
        return cellData;
    }
}
