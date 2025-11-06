public enum Genre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    DRAMA("Drama"),
    DOCUMENTARY("Documentary"),
    THRILLER("Thriller"),
    ANIMATION("Animation"),
    SCI_FI("Sci-Fi"),
    COMEDY("Comedy"),
    UNKNOWN("Unknown");

    private final String typeOfGenre;

    Genre(String typeOfGenre) {
        this.typeOfGenre = typeOfGenre;
    }

    public String getTypeOfGenre() {
        return typeOfGenre;
    }

    @Override
    public String toString() {
        return typeOfGenre;
    }
}


