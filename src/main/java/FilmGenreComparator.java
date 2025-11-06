import java.util.Comparator;

public class FilmGenreComparator implements Comparator<Film>{
    @Override
    public int compare(Film film1, Film film2) {
        return film1.getGenre().compareTo(film2.getGenre());
    }
}
