package com.techelevator;

import com.techelevator.services.RestCatFactService;
import com.techelevator.services.RestCatPicService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatCardsApplication {

    private final RestCatFactService catFactService = new RestCatFactService();
    private final RestCatPicService catPicService = new RestCatPicService();

    public static void main(String[] args) {
        SpringApplication.run(CatCardsApplication.class, args);
    }

}
