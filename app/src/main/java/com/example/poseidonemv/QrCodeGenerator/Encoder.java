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
import android.util.Base64;
public class Encoder {
    private String PAYLOAD_FORMAT_INDICATOR = "85054350563031";
    private String applicationTemplate;
    private final String APPLICATION_DEFINITION_FILE_NAME = "proif-kelompok-2";
    private CreditCardCredentials data;
    private Encrypt encrypt;
    public Encoder(){
        this.encrypt = new Encrypt();
    }
    public String encryptBase64(String accountNumber,String expirationDate,String Discretionary,String pin){
        String base64 = this.convertToBase64(concateAll(accountNumber,expirationDate,Discretionary));
        return this.encrypt.encrypt(base64,pin);
    }
    public String concateAll(String accountNumber,String expirationDate,String directionaryData){
        this.data = new CreditCardCredentials(accountNumber,expirationDate,directionaryData);
        this.applicationTemplate = this.convertStringToHex()+this.data.concatAllInfo();
        String tag ="61";
        String length = Integer.toHexString(this.applicationTemplate.length()/2);
        if(length.length()%2 != 0){
            length="0"+length;
        }
        return PAYLOAD_FORMAT_INDICATOR+tag+length+this.applicationTemplate;
    }
    public String convertStringToHex(){
        StringBuffer temp = new StringBuffer();
        char ch[] = APPLICATION_DEFINITION_FILE_NAME.toCharArray();
        for(int i=0;i<ch.length;i++){
            String hexString = Integer.toHexString(ch[i]);
            temp.append(hexString);
        }
        String result = temp.toString();
        String tag = "4F";
        String length = Integer.toHexString(result.length()/2);
        if(length.length()%2 != 0){
            length="0"+length;
        }
        return tag+length+result;

    }
    public String convertToBase64(String hexString){
        byte[] bytes = new byte[hexString.length()/2];
        for(int i=0;i<bytes.length;i++){
            int index = i * 2;
            int j = Integer.parseInt(hexString.substring(index, index + 2), 16);
            bytes[i] = (byte)j;
        }
        String result = Base64.encodeToString(bytes,Base64.NO_WRAP);
        return result;
    }

}

