package it.unipv.ingsfw.model;

import java.time.LocalDate;

/**
 * Rappresenta la classe che contiene i dati di una prenotazione
 */

public class Reservation {

    private int id;
    private String contactName;
    private LocalDate date;
    private String time;
    private int holes;
    private int players;
    private String extra;
    private double totalPrice;

    public Reservation() {
    }



    public Reservation(int id, String contactName, LocalDate date, String time, int holes, int players, String extra, double totalPrice) {
        this.id = id;
        this.contactName = contactName;
        this.date = date;
        this.time = time;
        this.holes = holes;
        this.players = players;
        this.extra = extra;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHoles() {
        return holes;
    }

    public void setHoles(int holes) {
        this.holes = holes;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", contactName='" + contactName + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", holes=" + holes +
                ", players=" + players +
                ", extra='" + extra + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

}

