package Service;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookMyMovieApp {
    public static void main(String args[]){
        BookMyMoviesSystem mbs = new BookMyMoviesSystem();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your city: ");
        String city = sc.next();
        mbs.DisplayTheaters(city);

        mbs.displayMovies();

        System.out.println("Please enter your theater id and movie id: ");
        int theaterId = sc.nextInt();
        int movieId = sc.nextInt();
        mbs.DisplayShows(movieId , theaterId);

        System.out.println("Please enter show Id: ");
        int showId = sc.nextInt();

        mbs.showAvailableSeats(showId);

        System.out.println("Please enter seat numbers to book ( comma separated, e.g.,A1,A2,B3): ");
        sc.nextLine();
        String seatInput = sc.nextLine();//consume leftover newline
        List<String> ss = Arrays.asList(seatInput.split("\\s*,\\s*"));
        mbs.bookTicket(1,showId , ss);

    }
}
