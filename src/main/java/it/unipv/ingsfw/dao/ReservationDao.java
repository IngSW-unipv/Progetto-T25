package it.unipv.ingsfw.dao;

import it.unipv.ingsfw.model.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) per la gestione della persistenza delle prenotazioni.
 * Si interfaccia direttamente con la tabella "reservation".
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class ReservationDao {

    public ReservationDao() {
    }

    public int getPostiOccupati(String data, String orario) throws SQLException {

        String sql = "SELECT SUM(players) FROM reservation WHERE date = ? AND time = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, data);
            stmt.setString(2, orario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
    public void save(Reservation reservation) throws SQLException {

        String sql = "INSERT INTO reservation (contact_name, date, time, holes, players, extra, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reservation.getContactName());

            stmt.setDate(2, Date.valueOf(reservation.getDate()));
            stmt.setString(3, reservation.getTime());
            stmt.setInt(4, reservation.getHoles());
            stmt.setInt(5, reservation.getPlayers());
            stmt.setString(6, reservation.getExtra());
            stmt.setDouble(7, reservation.getTotalPrice());

            stmt.executeUpdate();
        }
    }

    public List<Reservation> getAll() throws SQLException {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservation";

        try (Connection conn = DbConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reservation r = new Reservation();

                r.setId(rs.getInt("id"));
                r.setContactName(rs.getString("contact_name"));

                r.setDate(rs.getDate("date").toLocalDate());
                r.setTime(rs.getString("time"));
                r.setHoles(rs.getInt("holes"));
                r.setPlayers(rs.getInt("players"));
                r.setExtra(rs.getString("extra"));
                r.setTotalPrice(rs.getDouble("total_price"));
                list.add(r);
            }
        }
        return list;
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM reservation WHERE id = ?";

        try (Connection conn = DbConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }
}