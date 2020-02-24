package com.example.poseidonemv;

public class CardModel {
    protected String name;
    protected String cardNumber;
    protected String expirationDate;

    public String getName() {
        return name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public CardModel(String name, String cardNumber, String expirationDate) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
    }
}
