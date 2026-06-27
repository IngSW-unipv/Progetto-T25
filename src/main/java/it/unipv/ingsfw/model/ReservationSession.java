package it.unipv.ingsfw.model;

import java.time.LocalDate;

/**
 * Rappresenta la classe che mantiene i dati negli step della prenotazione
 */

public class ReservationSession {

    private static ReservationSession instance;
    private int holes;
    private int players;
    private LocalDate date;
    private String time;
    private String customerName;
    private String extra;
    private int carts;
    private int bags;
    private int trolleys;
    private double totalPrice;

    private ReservationSession() {}

    public static ReservationSession getInstance() {
        if (instance == null) {
            instance = new ReservationSession();
        }
        return instance;
    }

    public void reset() {
        this.holes = 0;
        this.players = 0;
        this.date = null;
        this.time = null;
        this.customerName = null;
        this.extra = null;
        this.carts = 0;
        this.bags = 0;
        this.trolleys = 0;
        this.totalPrice = 0.0;
    }

    public int getHoles() { return holes; }
    public void setHoles(int holes) { this.holes = holes; }

    public int getPlayers() { return players; }
    public void setPlayers(int players) { this.players = players; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getExtra() { return extra; }
    public void setExtra(String extra) { this.extra = extra; }

    public int getCarts() { return carts; }
    public void setCarts(int carts) { this.carts = carts; }

    public int getBags() { return bags; }
    public void setBags(int bags) { this.bags = bags; }

    public int getTrolleys() { return trolleys; }
    public void setTrolleys(int trolleys) { this.trolleys = trolleys; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
