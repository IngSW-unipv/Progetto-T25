package it.unipv.ingsfw.service;


import it.unipv.ingsfw.model.TeeTimeSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe di test JUnit per verificare la corretta generazione e strutturazione
 * del planning giornaliero dei tee time della classe TeeTimePlanningService}.
 *
 * @author Leah Appiah
 * @version 1.0
 */
public class TeeTimePlanningServiceTest {

    private TeeTimePlanningService planningService;

    @BeforeEach
    public void setUp() {
        planningService = new TeeTimePlanningService();
    }


    @Test
    public void testGeneraPlanningGiornaliero_DimensioneEStruttura() throws SQLException {

        LocalDate oggi = LocalDate.now();

        List<TeeTimeSlot> planning = planningService.generaPlanningGiornaliero(oggi);

        assertNotNull(planning, "Il planning non dovrebbe essere null");
        assertEquals(25, planning.size(), "Il numero totale di slot generati tra le 8 e le 12 deve essere 25");
        assertEquals("08:00", planning.get(0).getTime(), "Il primo slot orario deve essere alle 08:00");

        assertEquals("12:00", planning.get(planning.size() - 1).getTime(), "L'ultimo slot orario deve essere alle 12:00");
    }
}