package com.cognizant.jpa;

import com.cognizant.jpa.entity.Country;
import com.cognizant.jpa.service.CountryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataJpaApplication implements CommandLineRunner {

    private final CountryService countryService;

    public SpringDataJpaApplication(CountryService countryService) {
        this.countryService = countryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Spring Data JPA Exercises ===\n");

        System.out.println("--- Adding Countries ---");
        countryService.addCountry(new Country("US", "United States", 331000000));
        countryService.addCountry(new Country("IN", "India", 1380000000));
        countryService.addCountry(new Country("CN", "China", 1440000000));
        countryService.addCountry(new Country("BR", "Brazil", 212000000));
        countryService.addCountry(new Country("DE", "Germany", 83000000));
        countryService.addCountry(new Country("JP", "Japan", 126000000));
        System.out.println("Added 6 countries\n");

        System.out.println("--- All Countries ---");
        countryService.getAllCountries().forEach(System.out::println);

        System.out.println("\n--- Find by Country Code (IN) ---");
        countryService.getCountryByCode("IN").ifPresent(System.out::println);

        System.out.println("\n--- Search by Name (united) ---");
        countryService.searchByName("united").forEach(System.out::println);

        System.out.println("\n--- Countries with Population > 1 Billion ---");
        countryService.getCountriesByMinPopulation(1000000000).forEach(System.out::println);

        System.out.println("\n--- Countries with Population between 80M and 250M ---");
        countryService.getCountriesByPopulationRange(80000000, 250000000).forEach(System.out::println);

        System.out.println("\n--- Updating Country (BR -> Brasil) ---");
        countryService.updateCountry(4L, new Country("BR", "Brasil", 213000000));
        countryService.getCountryByCode("BR").ifPresent(System.out::println);

        System.out.println("\n=== All Exercises Complete ===");
    }
}
