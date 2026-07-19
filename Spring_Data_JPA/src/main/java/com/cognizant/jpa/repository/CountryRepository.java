package com.cognizant.jpa.repository;

import com.cognizant.jpa.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByCountryCode(String countryCode);

    List<Country> findByCountryNameContainingIgnoreCase(String name);

    List<Country> findByPopulationGreaterThan(double population);

    List<Country> findByPopulationBetween(double min, double max);

    @Query("SELECT c FROM Country c WHERE c.countryName LIKE %:keyword%")
    List<Country> searchByName(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM countries WHERE population > :threshold", nativeQuery = true)
    List<Country> findLargeCountries(@Param("threshold") double threshold);

    @Query("SELECT c.countryName FROM Country c ORDER BY c.population DESC")
    List<String> findCountryNamesByPopulationDesc();
}
