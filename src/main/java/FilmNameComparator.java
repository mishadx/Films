import java.util.Comparator;

public class FilmNameComparator implements Comparator<Film>{
    @Override
    public int compare(Film film1, Film film2) {
        return film1.getName().compareTo(film2.getName());
    }
}
