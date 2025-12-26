package models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bank {
    private String cardType;
    private String currency;

    public String getCardType() {
        return cardType;
    }

    public String getCurrency() {
        return currency;
    }
}
