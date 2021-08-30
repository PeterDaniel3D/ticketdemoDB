package dat.ticketdemoDB.persistance;

import dat.ticketdemoDB.exceptions.DatabaseException;
import dat.ticketdemoDB.Student;
import dat.ticketdemoDB.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datamapper {

    public static List<Ticket> getAllTickets() throws DatabaseException  {
        List<Ticket> ticketList = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM `ticket`";
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                ticketList.add(new Ticket(id, new Student(name)));
            }
        } 
        catch ( SQLException | ClassNotFoundException ex ) {
            throw new DatabaseException( ex.getMessage() );
        }
        return ticketList;
    }
    
    public static void createTicket(Ticket ticket) throws DatabaseException {
        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO `ticket` (`name`) VALUES (?)";
            try (PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);) {
                ps.setString(1, ticket.getStudents().getName());
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DatabaseException(ex.getMessage());
            }
        }
        catch ( SQLException | ClassNotFoundException ex ) {
            throw new DatabaseException( ex.getMessage() );
        }
    }

    public static void removeTicket() throws DatabaseException {
        try {
            Connection con = Connector.connection();
            String SQL = "DELETE FROM `ticket` WHERE `id` > 0 LIMIT 1";
            try (PreparedStatement ps = con.prepareStatement(SQL);) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new DatabaseException(ex.getMessage());
            }
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new DatabaseException( ex.getMessage() );
        }
    }
}
