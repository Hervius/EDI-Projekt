package com.example.marketing.application.service;

import com.example.marketing.application.port.out.LagerDatenPort;
import com.example.marketing.domain.model.Produkt;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MarketingService { // Herzstück (die Application in der hexagolnalen Architektur)
    // Er ruft das Interface auf, berechnet den Rabatt und gibt das Ergebnis zurück

    private final LagerDatenPort lagerDatenPort;

    public MarketingService(LagerDatenPort lagerDatenPort) {
        this.lagerDatenPort = lagerDatenPort;
    }

    public List<Produkt> getProduktMitRabatt() {
        List<Produkt> produkt = lagerDatenPort.holeAlleProdukte();

        for(Produkt p :  produkt) {
            double neuerPreis = p.getPreis() * 0.90;    // 10% Rabatt
            p.setPreis(neuerPreis);
        }

        return produkt;
    }



}
