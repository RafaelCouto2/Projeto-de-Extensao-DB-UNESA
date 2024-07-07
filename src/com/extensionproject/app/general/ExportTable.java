package com.extensionproject.app.general;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExportTable implements IArquivo<JTable> {

    private File file;


    private void exportExcelTable(JTable table, String tablename){

        try(Workbook wb = new XSSFWorkbook()){
            Files.deleteIfExists(Path.of(".//tabelas//tabela_de_" + tablename + ".xlsx"));
            File filexlsx = new File(".//tabelas//tabela_de_" + tablename + ".xlsx");

            Sheet sheet = wb.createSheet(tablename);

            CellStyle style = wb.createCellStyle();
            CellStyle stylerow = wb.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            org.apache.poi.ss.usermodel.Font font = wb.createFont();
            font.setFontName("JetBrains Mono");
            font.setFontHeightInPoints((short) 10);
            font.setBold(true);
            font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            org.apache.poi.ss.usermodel.Font fontrow = wb.createFont();
            fontrow.setFontName("JetBrains Mono");
            fontrow.setFontHeightInPoints((short) 9);
            fontrow.setBold(true);
            fontrow.setColor(HSSFColor.HSSFColorPredefined.GREY_80_PERCENT.getIndex());
            stylerow.setFont(fontrow);
            stylerow.setBorderBottom(BorderStyle.THIN);
            stylerow.setBorderLeft(BorderStyle.THIN);
            stylerow.setBorderRight(BorderStyle.THIN);
            stylerow.setBorderTop(BorderStyle.THIN);
            stylerow.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            stylerow.setRightBorderColor(IndexedColors.BLACK.getIndex());
            stylerow.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            stylerow.setTopBorderColor(IndexedColors.BLACK.getIndex());
            stylerow.setAlignment(HorizontalAlignment.LEFT);

            Row row = sheet.createRow(1);
            TableModel model = table.getModel();
            Row header = sheet.createRow(0);

            //CRIAÇÃO DOS HEADINGS DE COLUNAS DA TABELA EXCEL. PARA CADA COLUNA, CRIA-SE UMA EXCEL.
            for(int headings = 0; headings < model.getColumnCount(); headings++){
                header.createCell(headings).setCellValue(table.getColumnName(headings));
                header.getCell(headings).setCellStyle(style);
                sheet.autoSizeColumn(headings);
            }
            for(int rows = 0; rows < model.getRowCount(); rows++){
                for(int columns = 0; columns < table.getColumnCount(); columns++){
                    if(columns == 0){
                        row.createCell(columns);
                        row.getCell(columns).setCellValue(Double.parseDouble(table.getValueAt(rows, columns).toString()));

                    } else {
                        row.createCell(columns).setCellValue(table.getValueAt(rows, columns).toString());
                    }
                    row.getCell(columns).setCellStyle(stylerow);
                    sheet.autoSizeColumn(columns);
                }
                row = sheet.createRow((rows + 2));
            }
            wb.write(new FileOutputStream(filexlsx));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void abrirArquivo(String filename, String extension) {
        try {
            Files.deleteIfExists(Path.of(".//tabelas//tabela_de_" + filename + "." + extension));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        file = new File(".//tabelas//tabela_de_" + filename + "." + extension);
    }

    @Override
    public void gravarArquivo(JTable table) {

        try(Workbook wb = new XSSFWorkbook()){

            Sheet sheet = wb.createSheet(file.getName());

            CellStyle style = wb.createCellStyle();
            CellStyle stylerow = wb.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            org.apache.poi.ss.usermodel.Font font = wb.createFont();
            font.setFontName("JetBrains Mono");
            font.setFontHeightInPoints((short) 10);
            font.setBold(true);
            font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            org.apache.poi.ss.usermodel.Font fontrow = wb.createFont();
            fontrow.setFontName("JetBrains Mono");
            fontrow.setFontHeightInPoints((short) 9);
            fontrow.setBold(true);
            fontrow.setColor(HSSFColor.HSSFColorPredefined.GREY_80_PERCENT.getIndex());
            stylerow.setFont(fontrow);
            stylerow.setBorderBottom(BorderStyle.THIN);
            stylerow.setBorderLeft(BorderStyle.THIN);
            stylerow.setBorderRight(BorderStyle.THIN);
            stylerow.setBorderTop(BorderStyle.THIN);
            stylerow.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            stylerow.setRightBorderColor(IndexedColors.BLACK.getIndex());
            stylerow.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            stylerow.setTopBorderColor(IndexedColors.BLACK.getIndex());
            stylerow.setAlignment(HorizontalAlignment.LEFT);

            Row row = sheet.createRow(1);
            TableModel model = table.getModel();
            Row header = sheet.createRow(0);

            //CRIAÇÃO DOS HEADINGS DE COLUNAS DA TABELA EXCEL. PARA CADA COLUNA, CRIA-SE UMA EXCEL.
            for(int headings = 0; headings < model.getColumnCount(); headings++){
                header.createCell(headings).setCellValue(table.getColumnName(headings));
                header.getCell(headings).setCellStyle(style);
                sheet.autoSizeColumn(headings);
            }
            for(int rows = 0; rows < model.getRowCount(); rows++){
                for(int columns = 0; columns < table.getColumnCount(); columns++){
                    if(columns == 0){
                        row.createCell(columns);
                        row.getCell(columns).setCellValue(Double.parseDouble(table.getValueAt(rows, columns).toString()));

                    } else {
                        row.createCell(columns).setCellValue(table.getValueAt(rows, columns).toString());
                    }
                    row.getCell(columns).setCellStyle(stylerow);
                    sheet.autoSizeColumn(columns);
                }
                row = sheet.createRow((rows + 2));
            }
            wb.write(new FileOutputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fecharArquivo() {

    }
}
