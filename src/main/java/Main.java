public class Main {1
    public static void main(String[] args) {
        FilmsManager films = new FilmsManager();
        System.out.println("\n============== CINEMA ==============");
        try{
        films.loadFilms();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}