package com.mcfly.order_service.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Embeddable
public class Address {

    @Size(max = 30)
    private String address;
    @Size(max = 30)
    private String city;
    @Size(max = 30)
    private String state;
    @Size(max = 30)
    private String zipCode;

    public Address() {
    }

    public Address(String address, String city, String state, String zipCode) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        if (!Objects.equals(address, address1.address)) return false;
        if (!Objects.equals(city, address1.city)) return false;
        if (!Objects.equals(state, address1.state)) return false;
        return Objects.equals(zipCode, address1.zipCode);
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        return result;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
