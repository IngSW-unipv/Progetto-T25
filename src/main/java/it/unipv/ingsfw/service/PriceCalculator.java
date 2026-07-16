package it.unipv.ingsfw.service;

/**
 * Classe per il calcolo delle tariffe e del totale della prenotazione.
 * Applica i prezzi base per il percorso di gioco e somma i supplementi dell'attrezzatura a noleggio.
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class PriceCalculator {

    public static double calcolaTotale(int holes, int players, int cart, int golfbag, int trolley) {

        double basePrice = (holes == 9) ? 40.0 : 60.0;

        return (basePrice * players)
                + (cart * 30.0)
                + (golfbag * 15.0)
                + (trolley * 5.0);
    }
}

