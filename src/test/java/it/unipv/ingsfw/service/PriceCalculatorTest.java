package it.unipv.ingsfw.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PriceCalculatorTest {

    @Test
    public void testCalcoloPrezzo9Buche() {
        int holes = 9;
        int players = 2;
        int cart = 0;
        int golfbag = 0;
        int trolley = 0;
        double totale = PriceCalculator.calcolaTotale(holes, players, cart, golfbag, trolley);

        assertEquals(80.0, totale);
    }


    @Test
    public void testCalcoloPrezzo18BucheConExtra() {
        int holes = 18;
        int players = 2;
        int cart = 1;
        int golfbag = 1;
        int trolley = 1;

        double totale = PriceCalculator.calcolaTotale(holes, players, cart, golfbag, trolley);
        double atteso = (60.0 * 2) + 30.0 + 15.0 + 5.0;
        assertEquals(atteso, totale);
    }
}