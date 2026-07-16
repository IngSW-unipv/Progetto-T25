package it.unipv.ingsfw.service;

import it.unipv.ingsfw.exceptions.InvalidReservationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test JUnit per verificare le regole di validazione delle prenotazioni
 * implementate nella classe ReservationValidator.
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class ReservationValidatorTest {

    private ReservationValidator validator;


    @BeforeEach
    public void setUp() {
        validator = new ReservationValidator();
    }



    @Test
    public void testValidaDataOggiEDomani_Valide() {
        assertDoesNotThrow(() -> validator.validaData(LocalDate.now()));
        assertDoesNotThrow(() -> validator.validaData(LocalDate.now().plusDays(1)));
    }


    @Test
    public void testValidaDataNelPassato_LanciaEccezione() {
        LocalDate dataPassata = LocalDate.now().minusDays(1);

        InvalidReservationException exception = assertThrows(
                InvalidReservationException.class,
                () -> validator.validaData(dataPassata)
        );
        assertTrue(exception.getMessage().contains("Impossibile inserire una prenotazione nel passato"));
    }

    @Test
    public void testValidaAttrezzaturaCoerente_Valida() {
        assertDoesNotThrow(() -> validator.validaAttrezzatura(2, 2, 1, 1));

        assertDoesNotThrow(() -> validator.validaAttrezzatura(4, 1, 2, 0));
    }

    @Test
    public void testValidaTroppeSacche_LanciaEccezione() {
        assertThrows(
                InvalidReservationException.class,
                () -> validator.validaAttrezzatura(2, 3, 0, 0)
        );
    }

    @Test
    public void testValidaTroppiVeicoli_LanciaEccezione() {
        assertThrows(
                InvalidReservationException.class,
                () -> validator.validaAttrezzatura(2, 1, 2, 1)
        );
    }
}