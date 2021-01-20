package com.peterholub.constructorredirectscreator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peterholub.constructorredirectscreator.model.Redirect;
import com.peterholub.constructorredirectscreator.service.CSVtoObjectParser;
import com.peterholub.constructorredirectscreator.service.RedirectCreationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class ConstructorRedirectsCreatorApplication implements CommandLineRunner {
    @Autowired
    private RedirectCreationProcessor redirectCreationProcessor;

    public static void main(String[] args) {
        SpringApplication.run(ConstructorRedirectsCreatorApplication.class, args);

    }

    @Override
    public void run(String... args) {
        System.out.println("Enter file url");
        Scanner in = new Scanner(System.in);

        String filename = in.nextLine();
        redirectCreationProcessor.processRedirectsCreation(filename);

    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
