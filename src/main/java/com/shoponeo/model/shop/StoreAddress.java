package com.shoponeo.model.shop;

import javax.persistence.*;

/**
 * Created by owczatom on 2016-10-21.
 */
@Entity
@Table(name = "STORE_ADDRESS")
public class StoreAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ADDRESS_ID")
    private int id;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Store store;

    public StoreAddress() {
    }

    public StoreAddress(String country, String city, String street, String number, String postalCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
    }

    public StoreAddress(String country, String city, String street, String number, String postalCode, Store store) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.store = store;
    }

    public int getId() {
        return id;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "StoreAddress{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
