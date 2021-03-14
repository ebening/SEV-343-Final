package com.adinfi.seven.persistence.dto;

import java.io.Serializable;


/**
 *
 * @author christian
 */
public class UrlParameter implements Serializable {

    private String key;
    private String value;

    public UrlParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public UrlParameter() {
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
