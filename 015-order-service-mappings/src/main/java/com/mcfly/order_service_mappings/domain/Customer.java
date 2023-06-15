package com.mcfly.order_service_mappings.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Customer extends BaseEntity {

    private String customerName;
    @Embedded
    private Address address;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "customer")
    private Set<OrderHeader> orders;

    public Customer() {
        this.orders = new LinkedHashSet<>();
    }

    public Customer(String customerName, Address address, String phone, String email) {
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        if (!Objects.equals(customerName, customer.customerName))
            return false;
        if (!Objects.equals(address, customer.address)) return false;
        if (!Objects.equals(phone, customer.phone)) return false;
        if (!Objects.equals(email, customer.email)) return false;
        return Objects.equals(orders, customer.orders);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<OrderHeader> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderHeader> orders) {
        this.orders = orders;
    }
}
