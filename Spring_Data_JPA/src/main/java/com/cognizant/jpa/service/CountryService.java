package com.cognizant.jpa.service;

import com.cognizant.jpa.entity.Country;
import com.cognizant.jpa.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountryByCode(String code) {
        return countryRepository.findByCountryCode(code);
    }

    public List<Country> searchByName(String name) {
        return countryRepository.findByCountryNameContainingIgnoreCase(name);
    }

    public List<Country> getCountriesByMinPopulation(double minPopulation) {
        return countryRepository.findByPopulationGreaterThan(minPopulation);
    }

    public List<Country> getCountriesByPopulationRange(double min, double max) {
        return countryRepository.findByPopulationBetween(min, max);
    }

    public Country updateCountry(Long id, Country updated) {
        Country existing = countryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Country not found with id: " + id));
        existing.setCountryCode(updated.getCountryCode());
        existing.setCountryName(updated.getCountryName());
        existing.setPopulation(updated.getPopulation());
        return countryRepository.save(existing);
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
