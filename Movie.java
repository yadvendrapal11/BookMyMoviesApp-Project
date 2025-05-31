package Entity;

public class Movie {

    private int movieId;
    private String title;
    private String genre;
    private String lang;
    private int duration;


    public Movie(int movie_id, String title, String genre, String lang, int duration) {
        this.movieId = movie_id;
        this.title = title;
        this.genre = genre;
        this.lang = lang;
        this.duration = duration;
    }

    public int getMovie_id() {
        return movieId;
    }

    public void setMovie_id(int movie_id) {
        this.movieId = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
