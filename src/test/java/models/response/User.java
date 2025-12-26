package models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import models.common.Address;
import models.common.Bank;
import models.common.Company;
import models.common.Crypto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;

    private Address address;
    private Company company;
    private Bank bank;
    private Crypto crypto;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }

    public Company getCompany() {
        return company;
    }

    public Bank getBank() {
        return bank;
    }

    public Crypto getCrypto() {
        return crypto;
    }
}
