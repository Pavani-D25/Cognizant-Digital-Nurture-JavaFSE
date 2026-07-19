package com.cognizant.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2)
    private String countryCode;

    @Column(nullable = false)
    private String countryName;

    private double population;

    public Country() {}

    public Country(String countryCode, String countryName, double population) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.population = population;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }

    public double getPopulation() { return population; }
    public void setPopulation(double population) { this.population = population; }

    @Override
    public String toString() {
        return "Country{id=" + id + ", code='" + countryCode + "', name='" + countryName + "', population=" + population + "}";
    }
}
