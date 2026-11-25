package com.example.marketing.adapter.out.fake;

import com.example.marketing.application.port.out.LagerDatenPort;
import com.example.marketing.domain.model.Produkt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FakeLagerAdapter implements LagerDatenPort {

    @Override
    public List<Produkt> holeAlleProdukte() {
        return List.of(
                new Produkt(1L, "Testprodukt A", 10.0),
                new Produkt(2L, "Testprodukt B", 20.0)

        );
    }
}
