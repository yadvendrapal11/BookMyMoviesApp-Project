package Service;

import Configurartion.DataBaseConfig;

import java.lang.module.Configuration;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class BookMyMoviesSystem {
    Scanner sc = new Scanner(System.in);

    //Display movie
    public void displayMovies(){

        try{
            Connection con = DataBaseConfig.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from movie");
            System.out.println("-------------Movies---------------");
            while(rs.next()){
                System.out.println(rs.getInt("movie_id")+". "+
                        rs.getString("title")+". "+
                        rs.getString("genre")+". ");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    //Show theators in a city
    public void DisplayTheaters(String city){
        try{
            Connection con = DataBaseConfig.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from theater where city =?");
            stmt.setString(1,city);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Theaters in "+city+":");
            while(rs.next()){
                System.out.println(rs.getInt("theater_id")+". "+rs.getString("name"));
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Display shows
    public void DisplayShows(int movieId , int theaterId){

        try{
            Connection con = DataBaseConfig.getConnection();
            PreparedStatement stmt = con.prepareStatement("select * from shows where movie_id = ? AND theater_id = ?");
            stmt.setInt(1,movieId);
            stmt.setInt(2,theaterId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Available Shows");
            while (rs.next()){
                System.out.println(rs.getInt("show_id")+". "+
                        rs.getString("timing")+". "+
                        rs.getInt("available_seat"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        
    }

    //Book ticket
    public void bookTicket(int userId , int showId , List<String> selectedSeats){
        try{
            Connection con = DataBaseConfig.getConnection();
            con.setAutoCommit(false);

            //check if seat are available
            boolean alreadyBookedSeat = false;
            for (String seat : selectedSeats){
                PreparedStatement stmt = con.prepareStatement("select * from seat where seat_number = ? AND show_id = ?");
                stmt.setString(1,seat);
                stmt.setInt(2, showId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next() && rs.getBoolean("is_booked")) {
                    alreadyBookedSeat = true;
                    System.out.println("seat: " + seat + " is already booked please choose another seat.");
                }
            }
            if (alreadyBookedSeat){
                System.out.println("Booking failed ! some seats are already booked.");
                con.rollback();
                return;
            }
            for (String seat : selectedSeats){
                PreparedStatement stmt = con.prepareStatement("update seat set is_booked = true where seat_number = ? AND show_id = ?");
                stmt.setString(1, seat);
                stmt.setInt(2 ,showId );
                stmt.executeUpdate();
            }

            double totalPrice = 0.0;
            for (String seat : selectedSeats) {
                PreparedStatement seatStmt = con.prepareStatement(
                        "SELECT * FROM seat WHERE seat_number = ? AND show_id = ?"
                );
                seatStmt.setString(1, seat);
                seatStmt.setInt(2, showId);
                ResultSet seatRs = seatStmt.executeQuery();

                if (seatRs.next()) {
                    double price = seatRs.getDouble("price");
                    totalPrice += price;
                }
            }


            PreparedStatement stmt = con.prepareStatement("insert into booking (user_id , show_id , seats_booked , total_price) values (? , ? , ? , ?)");
            stmt.setInt(1 , userId);
            stmt.setInt(2 , showId);
            stmt.setString(3,String.join(",",selectedSeats));
            stmt.setDouble(4 , totalPrice);
            stmt.executeUpdate();
            PreparedStatement updateSeatsStmt = con.prepareStatement(
                    "UPDATE shows SET available_seat = available_seat - ? WHERE show_id = ?"
            );
            updateSeatsStmt.setInt(1, selectedSeats.size());  // booked seats count
            updateSeatsStmt.setInt(2, showId);
            updateSeatsStmt.executeUpdate();

            con.commit();
            System.out.println("Booking successfull: seats"+selectedSeats+" | total price: "+totalPrice);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void showAvailableSeats(int showId) {
        try {
            Connection con = DataBaseConfig.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT seat_number FROM seat WHERE show_id = ? AND is_booked = false");
            stmt.setInt(1, showId);
            ResultSet rs = stmt.executeQuery();
            System.out.println("Available seats:");
            while (rs.next()) {
                System.out.print(rs.getString("seat_number") + " ");
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
