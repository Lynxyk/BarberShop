package com.example.demo;

public class AddressPostalCode {
    private String address;
    private String postalCode;

    public AddressPostalCode(String address, String postalCode) {
        this.address = address;
        this.postalCode = postalCode;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
