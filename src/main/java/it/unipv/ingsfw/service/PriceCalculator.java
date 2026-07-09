package it.unipv.ingsfw.service;

/**
 * classe coi prezzi del campo da golf
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

