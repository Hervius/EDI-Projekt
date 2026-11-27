package com.example.marketing.application.service;

import com.example.marketing.application.port.out.LagerDatenPort;
import com.example.marketing.domain.model.Produkt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // 1. Aktiviert Mockito für diese Klasse
public class MarketingServiceTest {

    @Mock
    private LagerDatenPort  lagerDatenPort; // 2. Erstellt eine Attrape des Interfaces

    @InjectMocks
    private MarketingService marketingService;  // 3. Erstellt den Service und steckt den Mock rein

    @Test
    void testGetProduktMitRabatt(){
        // 4. GIVEN (Vorbereitung): Wir sagen dem Mock, was er zurückgeben soll
        Produkt p1 = new Produkt(1L, "Test A", 10.0);
        Produkt p2 = new Produkt(2L, "Test B", 20.0);

        // Wenn jemand holeAlleProdukte() aufruft, gib diese Liste zurück
        when(lagerDatenPort.holeAlleProdukte()).thenReturn(List.of(p1, p2));

        // 5. WHEN (Aktion): Wir rufen die echte Methode im Service auf
        List<Produkt> ergebnis = marketingService.getProduktMitRabatt();

        // 6. THEN (Überprüfung): Wir prüfen, ob die Preise korrekt berechnet wurden
        assertEquals(9.0, ergebnis.get(0).getPreis(), 0.001);    // 10 * 0.9 = 9.0
        assertEquals(18.0, ergebnis.get(1).getPreis(), 0.001);    // 20 * 0.9 = 18.0



    }

}
