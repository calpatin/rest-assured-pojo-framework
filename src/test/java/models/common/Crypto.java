package models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Crypto {
    private String coin;
    private String network;

    public String getCoin() {
        return coin;
    }

    public String getNetwork() {
        return network;
    }
}
