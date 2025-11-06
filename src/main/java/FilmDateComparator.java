import java.util.Comparator;

public class FilmDateComparator implements Comparator<Film>{
    @Override
    public int compare(Film film1, Film film2) {
        return film1.getReleaseDate().compareTo(film2.getReleaseDate());
    }
}