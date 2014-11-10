package com.epam.edu.jmp.model;

public enum Currency {
    USD("Dollar USA", "$", 841), EUR("EURO", "€", 978), RUR("Russian Ruble",
            "р", 643);

    private String name;
    private String shortCode;
    private int code;

    private Currency(String name, String shortCode, int code) {
        this.name = name;
        this.shortCode = shortCode;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getShortCode() {
        return shortCode;
    }

    public Currency getCurrencyByCode(int code) {
        for (Currency cur : Currency.values()) {
            if (cur.getCode() == code) {
                return cur;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Currency [" + name + ", " + code + ", " + shortCode + "]";
    }

}
