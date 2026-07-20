package com.cognizant.rest.model;

public class Country {

    private String countryCode;
    private String countryName;
    private double population;

    public Country() {}

    public Country(String countryCode, String countryName, double population) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.population = population;
    }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }

    public double getPopulation() { return population; }
    public void setPopulation(double population) { this.population = population; }
}
