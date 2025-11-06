import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FilmsManager {
    private List<Film>filmsList=new ArrayList<>();

    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private final int SORT_BY_NAME=1;
    private final int SORT_BY_GENRE=2;
    private final int SEARCH_BY_YEAR=3;
    private final int ADD_FILM=4;
    private final int DELETE_FILM=5;
    private final int EDIT_FILM=6;
    private final int PRINT_ALL_FILMS =7;
    private final int SORT_BY_DATE=8;

    private final int GENERATE_FILMS=1;
    private final int LOAD_FILMS_CSV=2;
    private final int LOAD_FILMS_XLSX=3;

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private DataSource filmsDataSourse;

    private int userChoice;

    Scanner taxi = new Scanner(System.in);

    public void loadFilms() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try{
            System.out.println(ANSI_GREEN +
                    "\nВиберіть тип завантаження фільмів:\n" +
                    "1) Завантажити фільми за допомогою Java\n" +
                    "2) Завантажити фільми з файла CSV\n" +
                    "3) Завантажити фільми з файла EXEL\n"
            );
            userChoice = taxi.nextInt();

            switch (userChoice) {
                case GENERATE_FILMS:{
                    filmsList.add(new Film(1, "Interstellar", "USA", dateFormat.parse("07.11.2014"), Genre.SCI_FI));
                    filmsList.add(new Film(2, "Inception", "USA", dateFormat.parse("16.07.2010"), Genre.ACTION));
                    filmsList.add(new Film(3, "Titanic", "USA", dateFormat.parse("19.12.1997"), Genre.DRAMA));
                    filmsList.add(new Film(4, "Amélie", "France", dateFormat.parse("25.04.2001"), Genre.COMEDY));
                    filmsList.add(new Film(5, "Parasite", "South Korea", dateFormat.parse("30.05.2019"), Genre.THRILLER));
                    filmsList.add(new Film(6, "The Matrix", "USA", dateFormat.parse("31.03.1999"), Genre.SCI_FI));
                    filmsList.add(new Film(7, "Spirited Away", "Japan", dateFormat.parse("20.07.2001"), Genre.ANIMATION));
                    filmsList.add(new Film(8, "The Godfather", "USA", dateFormat.parse("24.03.1972"), Genre.DRAMA));
                    filmsList.add(new Film(9, "The Dark Knight", "USA", dateFormat.parse("18.07.2008"), Genre.ACTION));
                    filmsList.add(new Film(10, "Forrest Gump", "USA", dateFormat.parse("06.07.1994"), Genre.DRAMA));
                    System.out.println("Фільми успішно додано:");
                    menuOfFunctions();
                    break;
                }
                case LOAD_FILMS_CSV:{
                    filmsDataSourse = new CSVLoader();
                    filmsList = filmsDataSourse.loadFilms(DataSource.FILE_CSV);
                    System.out.println("Фільми успішно додано:");
                    menuOfFunctions();
                    break;
                }
                case LOAD_FILMS_XLSX:{
                    filmsDataSourse = new ExcelLoader();
                    filmsList = filmsDataSourse.loadFilms(DataSource.FILE_EXCEL);
                    System.out.println("Фільми успішно додано:");
                    menuOfFunctions();
                    break;
                }
                default:{
                    System.err.println("Такого пункту меню не існує");
                    loadFilms();
                }
            }
        }catch (ParseException e){
        e.printStackTrace();
        }
    }

    public void menuOfFunctions(){
        try {
            boolean exit = false;
            int answer;

            do {
                System.out.print(ANSI_YELLOW +
                        "\nВиберіть функцію:\n" +
                        "1) Посортувати за назвою\n" +
                        "2) Посортувати за жанром\n" +
                        "3) Знайти за роком випуску\n" +
                        "4) Додати фільм\n" +
                        "5) Видалити фільм\n" +
                        "6) Редагувати фільм\n" +
                        "7) Вивести всі фільми\n" +
                        "8) Посортувати за датою\n"
                );

                System.out.println("\n0) Вихід з програми\n");

                userChoice = taxi.nextInt();

                switch (userChoice) {
                    case SORT_BY_NAME:
                    case SORT_BY_GENRE:{
                        sortFilms(userChoice);
                        break;
                    }
                    case SEARCH_BY_YEAR:{
                        System.out.println(ANSI_WHITE + "\nПошук фільмів за роком: \n");
                        printFilmByYear();
                        break;
                    }
                    case ADD_FILM: {
                        System.out.println(ANSI_PURPLE + "\nЗаповніть інформацію про фільм\n");
                        addNewFilm();
                        break;
                    }
                    case DELETE_FILM: {
                        System.out.println(ANSI_CYAN + "\nВведіть ID фільму, який ви хочете видалити\n");
                        deleteFilm();
                        break;
                    }
                    case EDIT_FILM: {
                        System.out.println(ANSI_PURPLE + "\nВведіть ID фільму, який ви хочете відредагувати\n");
                        editFilm();
                        break;
                    }
                    case PRINT_ALL_FILMS: {
                        System.out.println(ANSI_BLUE + "\nСписок всіх фільмів у колекції: \n");
                        printFilms();
                        break;
                    }
                    case 8: {
                        sortFilms(userChoice);
                    }
                    case 0:{
                        exit = true;
                        System.out.println(ANSI_BLUE +
                                "Зберегти файл\n" +
                                "1)У форматі CSV\n" + "2)У форматі XLSX\n" + "3)Не зберігати\n");
                        answer = taxi.nextInt();
                        if (answer == 1) {
                            saveFilms();
                        }
                        else if (answer == 2) {
                            saveFilmsEXEL();
                        }
                        break;
                    }
                    default:
                        throw new FilmException("Don`t found");
                }

            }while (!exit);

        }catch (Exception e){
            System.out.println("Такого пункту головного меню не існує");
            return;
        }
    }

    public Genre menuOfGenre() {
        System.out.println(
                "\nВиберіть жанр:\n" +
                        "1) ACTION\n" +
                        "2) ADVENTURE\n" +
                        "3) DRAMA\n" +
                        "4) DOCUMENTARY\n" +
                        "5) THRILLER\n" +
                        "6) COMEDY\n" +
                        "7) ANIMATION\n" +
                        "8) SCI-FI\n");
        try {
            userChoice = taxi.nextInt();
            Genre typeOfGenre;
            if (userChoice == 1) {
                typeOfGenre = Genre.ACTION;
            } else if (userChoice == 2) {
                typeOfGenre = Genre.ADVENTURE;
            } else if (userChoice == 3) {
                typeOfGenre = Genre.DRAMA;
            } else if (userChoice == 4) {
                typeOfGenre = Genre.DOCUMENTARY;
            } else if (userChoice == 5) {
                typeOfGenre = Genre.THRILLER;
            } else if (userChoice == 6) {
                typeOfGenre = Genre.COMEDY;
            } else if (userChoice == 7) {
                typeOfGenre = Genre.ANIMATION;
            } else if (userChoice == 8) {
                typeOfGenre = Genre.SCI_FI;
            }
            else{
                typeOfGenre = Genre.UNKNOWN;
                menuOfGenre();
            }
            return typeOfGenre;

        } catch (Exception e) {
            System.err.println("Такого пункту меню не існує");
            return null;
        }
    }

    public Film createFilm() throws ParseException {
        try {
            System.out.println("Введіть дату випуску");
            Date date = dateFormat.parse(taxi.next());

            System.out.println("Введіть назву фільму");
            String Name = taxi.next();

            Genre typeOfGenre = menuOfGenre();

            System.out.println("Введіть країну");
            String Country = taxi.next();

            return new Film(Name, Country, typeOfGenre, date);

        }
        catch (Exception e) {
            System.err.println("Виникла помилка: " + e.getMessage());
            return null;
        }
    }

    public void saveFilms(){
            filmsDataSourse = new CSVLoader();
            filmsDataSourse.saveFilms(DataSource.FILE_CSV, filmsList);
    }

    public void saveFilmsEXEL(){
        filmsDataSourse = new ExcelLoader();
        filmsDataSourse.saveFilms(DataSource.FILE_EXCEL, filmsList);
    }




    public void sortFilms(int SortMethod) {
        switch (SortMethod) {
            case SORT_BY_NAME: {
                Collections.sort(filmsList, new FilmNameComparator());
                System.out.println(ANSI_BLUE + "\nВідбулося сортування за назвою: \n");
                printFilms();
                break;
            }
            case SORT_BY_GENRE: {
                Collections.sort(filmsList, new FilmGenreComparator());
                System.out.println(ANSI_BLUE + "\nВідбулося сортування за жанром: \n");
                printFilms();
                break;
            }
            case SORT_BY_DATE: {
                Collections.sort(filmsList, new FilmDateComparator());
                System.out.println(ANSI_BLUE + "\nВідбулося сортування за датою: \n");
                printFilms();
            }
        }
    }

    /*   public void printAllFilms() {  //Простіший вивід
        for (Film currentFilm : filmsList) {
            System.out.println(currentFilm);
        }
    } */

    public void printFilms() {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("| %-2s | %-25s | %-10s | %-10s | %-12s |\n", "ID", "Назва", "Країна", "Дата", "Жанр");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Film film : filmsList) {
            System.out.println(film);
        }

        System.out.println("---------------------------------------------------------------------------------------------");
    }


    public void addNewFilm() {
        try{
            Film film = createFilm();
            int id = filmsList.size() +1;
            film.setId(id);
            filmsList.add(film);
        }
        catch (Exception e){
            System.err.println("Виникла помилка:" + e.getMessage());
        }
    }

    public void editFilm() {
        boolean finding = false;

        try {
            int id = taxi.nextInt();
            for (Film currentFilm : filmsList) {
                if (id == currentFilm.getId()) {
                    System.out.println("\nРедагування фільму:\n " + currentFilm + "\n");
                    Film film = createFilm();
                    currentFilm.setName(film.getName());
                    currentFilm.setCountry(film.getCountry());
                    currentFilm.setGenre(film.getGenre());
                    currentFilm.setReleaseDate(film.getReleaseDate());
                    System.out.println(ANSI_CYAN + "\nФільм було змінено на: \n" + currentFilm + "\n");
                    finding = true;
                }
            }

            if(!finding){
                System.err.println("Такого фільму не існує");
            }
        }catch (Exception e){
            System.err.println("Виникла помилка при редагуванні");
        }
    }

    public void deleteFilm() {
        try {
            int id = taxi.nextInt();

            boolean finding = false;

            for (Iterator<Film> iter = filmsList.iterator(); iter.hasNext();) {
                Film currentFilm = iter.next();
                if (id == currentFilm.getId()) {
                    System.out.println(currentFilm);
                    iter.remove();
                    finding = true;
                    break;
                }
            }

            if(!finding){
                System.err.println("Такого фільму не існує");
                return;
            }

            System.out.println(ANSI_PURPLE + "\nВиводимо список без видаленого фільму:\n");
            printFilms();
        }
        catch (Exception e){
            System.err.println("Виникла помилка при видалені");
        }
    }

    public void printFilmByYear() {
        try {
            System.out.println("Введіть рік для пошуку:");
            int yearToSearch = taxi.nextInt();

            boolean found = false;
            Calendar calendar = Calendar.getInstance();

            for (Film film : filmsList) {
                Date releaseDate = film.getReleaseDate();
                calendar.setTime(releaseDate);
                int filmYear = calendar.get(Calendar.YEAR);

                if (filmYear == yearToSearch) {
                    System.out.println(film);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Фільми за цей рік не знайдено.");
            }

        } catch (Exception e) {
            System.err.println("Помилка під час пошуку: " + e.getMessage());
        }
    }
}
