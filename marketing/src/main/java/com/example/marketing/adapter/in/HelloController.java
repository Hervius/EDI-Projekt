//package com.example.marketing.adapter.in;
//
//import com.example.marketing.application.service.MarketingService;
//import com.example.marketing.domain.model.Produkt;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/marketing")
//public class HelloController {
//
//    private final MarketingService marketingService;
//
//    public  HelloController(MarketingService marketingService) {
//        this.marketingService = marketingService;
//    }
//
//    @GetMapping("/hello")
//    public String sayHello() {
//        return "Hello from Marketing Service!";
//    }
//
//    @GetMapping("/rabatte")
//    public List<Produkt> getRabatte(){
//        return marketingService.getProduktMitRabatt();
//    }
//
//}
