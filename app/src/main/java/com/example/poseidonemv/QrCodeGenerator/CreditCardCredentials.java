package com.example.poseidonemv.QrCodeGenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stephen
 */
public class CreditCardCredentials {
    private String accountNumber;
    private String expirationDate;
    private final String SERVICE_CODE = "201";
    private String discretionaryData;

    public CreditCardCredentials(String accountNumber,String expirationDate,String discretionaryData){
        this.accountNumber = accountNumber;
        this.expirationDate = expirationDate;
        this.discretionaryData = discretionaryData;
    }
    public String concatAllInfo(){
        String result = accountNumber+"D"+expirationDate+SERVICE_CODE+discretionaryData;
        if(result.length()%2 != 0){
            result+= "F";
        }
        String tag = "57";
        String length = Integer.toHexString(result.length()/2);
        if(length.length()%2 != 0){
            length="0"+length;
        }
        return tag+length.toUpperCase()+result;
    }

}
