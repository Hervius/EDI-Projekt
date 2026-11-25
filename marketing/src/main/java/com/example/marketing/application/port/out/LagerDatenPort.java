package com.example.marketing.application.port.out;

import com.example.marketing.domain.model.Produkt;
import java.util.List;

public interface LagerDatenPort {
    List<Produkt> holeAlleProdukte();
}