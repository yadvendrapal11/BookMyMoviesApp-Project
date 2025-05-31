package Entity;

public class Shows {

    private int showId;
    private int movieId;
    private int theaterId;
    private String timing;
    private int availableSeat;

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public int getAvailableSeat() {
        return availableSeat;
    }

    public void setAvailableSeat(int availableSeat) {
        this.availableSeat = availableSeat;
    }

    public Shows(int showId, int movieId, int theaterId, String timing, int availableSeat) {
        this.showId = showId;
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.timing = timing;
        this.availableSeat = availableSeat;


    }
}
