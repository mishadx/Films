import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelLoader implements DataSource {
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public List<Film> loadFilms(String sourceName) {
        List<Film> films = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(sourceName);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            boolean skipHeader = true;

            for (Row row : sheet) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String country = row.getCell(2).getStringCellValue();

                Cell dateCell = row.getCell(3);
                Date releaseDate = null;
                if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
                    releaseDate = dateCell.getDateCellValue();
                } else {
                    releaseDate = dateFormat.parse(dateCell.getStringCellValue());
                }

                Genre genre = Genre.valueOf(row.getCell(4).getStringCellValue());

                films.add(new Film(id, name, country, releaseDate, genre));
            }

        } catch (Exception e) {
            System.out.println("Помилка відкриття Excel: " + e.getMessage());
        }
        return films;
    }

    @Override
    public void saveFilms(String sourceName, List<Film> films) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Фільми");
            Row header = sheet.createRow(0);
            String[] columns = {"id", "name", "country", "releaseDate", "genre"};

            for (int i = 0; i < columns.length; i++) {
                header.createCell(i).setCellValue(columns[i]);
            }

            int rowIndex = 1;
            for (Film film : films) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(film.getId());
                row.createCell(1).setCellValue(film.getName());
                row.createCell(2).setCellValue(film.getCountry());
                row.createCell(3).setCellValue(dateFormat.format(film.getReleaseDate()));
                row.createCell(4).setCellValue(film.getGenre().name());
            }

            try (FileOutputStream fos = new FileOutputStream(sourceName)) {
                workbook.write(fos);
            }
            System.out.println("Фільми збережено в Excel");

        } catch (Exception e) {
            System.out.println("Помилка запису Excel: " + e.getMessage());
        }
    }
}



