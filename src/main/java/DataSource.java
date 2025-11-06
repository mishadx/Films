import java.util.List;

public interface DataSource {
    int SOURCE_CSV = 1;
    int SOURCE_EXCEL = 2;
    int SOURCE_GENERATE = 3;

    String FILE_CSV = "Films.csv";
    String FILE_EXCEL = "FilmsEXEL.xlsx";

    List<Film> loadFilms(String sourceName);
    void saveFilms(String sourceName, List<Film> films);
}
