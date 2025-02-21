package id.ac.ui.cs.advprog.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public final class EshopApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EshopApplication.class, args);
    }
    private EshopApplication(){
        //empty
    }
}