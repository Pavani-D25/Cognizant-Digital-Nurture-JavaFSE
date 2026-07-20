package com.cognizant.rest.service;

import com.cognizant.rest.model.Country;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final List<Country> countries = new ArrayList<>();

    public CountryService() {
        countries.add(new Country("US", "United States", 331000000));
        countries.add(new Country("IN", "India", 1380000000));
        countries.add(new Country("CN", "China", 1440000000));
        countries.add(new Country("BR", "Brazil", 212000000));
        countries.add(new Country("DE", "Germany", 83000000));
    }

    public List<Country> getAllCountries() {
        return new ArrayList<>(countries);
    }

    public Optional<Country> getCountryByCode(String code) {
        return countries.stream()
            .filter(c -> c.getCountryCode().equalsIgnoreCase(code))
            .findFirst();
    }

    public Country addCountry(Country country) {
        countries.add(country);
        return country;
    }

    public Country updateCountry(String code, Country updated) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getCountryCode().equalsIgnoreCase(code)) {
                countries.set(i, updated);
                return updated;
            }
        }
        throw new RuntimeException("Country not found: " + code);
    }

    public boolean deleteCountry(String code) {
        return countries.removeIf(c -> c.getCountryCode().equalsIgnoreCase(code));
    }
}
