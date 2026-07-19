package com.cognizant.jpa;

import com.cognizant.jpa.entity.Country;
import com.cognizant.jpa.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    void setUp() {
        countryRepository.deleteAll();
        countryRepository.save(new Country("US", "United States", 331000000));
        countryRepository.save(new Country("IN", "India", 1380000000));
        countryRepository.save(new Country("CN", "China", 1440000000));
        countryRepository.save(new Country("BR", "Brazil", 212000000));
    }

    @Test
    void testFindByCountryCode() {
        Optional<Country> country = countryRepository.findByCountryCode("IN");
        assertTrue(country.isPresent());
        assertEquals("India", country.get().getCountryName());
    }

    @Test
    void testFindByCountryNameContaining() {
        List<Country> results = countryRepository.findByCountryNameContainingIgnoreCase("united");
        assertEquals(1, results.size());
        assertEquals("United States", results.get(0).getCountryName());
    }

    @Test
    void testFindByPopulationGreaterThan() {
        List<Country> large = countryRepository.findByPopulationGreaterThan(1000000000);
        assertEquals(2, large.size());
    }

    @Test
    void testFindByPopulationBetween() {
        List<Country> midSize = countryRepository.findByPopulationBetween(200000000, 400000000);
        assertEquals(2, midSize.size());
    }

    @Test
    void testCustomJPQLQuery() {
        List<Country> results = countryRepository.searchByName("India");
        assertEquals(1, results.size());
        assertEquals("IN", results.get(0).getCountryCode());
    }

    @Test
    void testNativeQuery() {
        List<Country> results = countryRepository.findLargeCountries(1000000000);
        assertEquals(2, results.size());
    }
}
