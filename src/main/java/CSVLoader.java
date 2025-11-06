import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVLoader implements DataSource {

    @Override
    public List<Film> loadFilms(String sourceName) {
        List<Film> films = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(sourceName);
            CSVReader reader = new CSVReader(fileReader);
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            String[] nextLine;
            // Пропускаємо заголовок
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {
                int id = Integer.parseInt(nextLine[0]);
                String name = nextLine[1];
                String country = nextLine[2];
                Date releaseDate = dateFormat.parse(nextLine[3]);
                Genre genre = Genre.valueOf(nextLine[4]);

                films.add(new Film(id, name, country, releaseDate, genre));
            }
        } catch (Exception e) {
            System.out.println("Помилка відкриття файлу: " + e.getMessage());
            return null;
        }
        return films;
    }

    @Override
    public void saveFilms(String sourceName, List<Film> films) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(sourceName));
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


            writer.writeNext(new String[]{"id", "name", "country", "releaseDate", "genre"});

            for (Film film : films) {
                String[] record = new String[5];
                record[0] = Integer.toString(film.getId());
                record[1] = film.getName();
                record[2] = film.getCountry();
                record[3] = dateFormat.format(film.getReleaseDate());
                record[4] = film.getGenre().name();
                writer.writeNext(record);
            }
            writer.close();
            System.out.println("Фільми успішно збережені у файл");
        } catch (Exception e) {
            System.out.println("Помилка збереження файлу: " + e.getMessage());
        }
    }
}

