package com.example.marketing.adapter.in.web;

import com.example.marketing.application.service.MarketingService;
import com.example.marketing.domain.model.Produkt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/marketing")
public class MarketingController {

    private final MarketingService marketingService;

    public MarketingController(MarketingService marketingService) {
        this.marketingService = marketingService;
    }

    @GetMapping("/produkte")
    public List<Produkt> getProdukteMitRabatt(){
        return marketingService.getProduktMitRabatt();
    }
}
