package com.example.marketing.application.service;

import com.example.marketing.application.port.out.LagerDatenPort;
import com.example.marketing.domain.model.Produkt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketingService {

    private final LagerDatenPort lagerDatenPort;

    public MarketingService(LagerDatenPort lagerDatenPort) {
        this.lagerDatenPort = lagerDatenPort;
    }

    public List<Produkt> getProduktMitRabatt() {
        List<Produkt> produkte = lagerDatenPort.holeAlleProdukte();

        // HIER IST DER FEHLER PASSIERT:
        // Du hast wahrscheinlich außerhalb der Schleife "10.0 * 0.9" gerechnet
        // und diesen festen Wert jedem Produkt zugewiesen.

        // So ist es richtig: Jedes Produkt einzeln berechnen
        return produkte.stream()
                .map(p -> {
                    double neuerPreis = p.getPreis() * 0.9; // 10% Rabatt auf den individuellen Preis
                    return new Produkt(p.getId(), p.getName(), neuerPreis);
                })
                .collect(Collectors.toList());
    }
}