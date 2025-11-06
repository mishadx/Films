import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Film {
    private int id;
    private String Name;
    private String Country;

    private Genre genre;
    private Date releaseDate;
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");



    public Film(int id, String Name, String Country, Date releaseDate, Genre genre) {
        this.id = id;
        this.Name = Name;
        this.Country = Country;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public Film(String name, String country, Genre typeOfGenre, Date date) {
        this.Name = name;
        this.Country = country;
        this.genre = typeOfGenre;
        this.releaseDate = date;
    }


    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCountry() {
        return Country;
    }
    public void setCountry(String Country) {
        this.Country = Country;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    @Override
    public String toString() {
        return String.format("| %-2d | %-25s | %-10s | %-10s | %-12s |",
                id,
                Name,
                Country,
                new SimpleDateFormat("dd.MM.yyyy").format(releaseDate),
                genre);
    }


}
