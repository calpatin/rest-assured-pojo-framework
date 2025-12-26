package models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    private String address;
    private String city;
    private String state;
    private String coutry;

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCoutry() {
        return coutry;
    }
}
