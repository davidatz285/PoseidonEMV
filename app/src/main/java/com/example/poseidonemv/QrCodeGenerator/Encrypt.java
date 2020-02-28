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
import java.security.MessageDigest;
import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
    private Cipher cipher;
    private byte[] key;
    private final String initVector="proif-kelompok-2";
    public Encrypt(){

    }
    public String encrypt(String word,String pin){
        SecretKeySpec secretKey;
        MessageDigest md5 = null;
        try{
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            this.key = pin.getBytes("UTF-8");
            md5 = MessageDigest.getInstance("MD5");
            this.key = md5.digest(key);
            secretKey = new SecretKeySpec(this.key,"AES");
            this.cipher.init(Cipher.ENCRYPT_MODE, secretKey,iv);
            byte[] encrypted = cipher.doFinal(word.getBytes());
            this.cipher.init(Cipher.DECRYPT_MODE,secretKey,iv);
            String result = Base64.encodeToString(encrypted,Base64.NO_WRAP);
            System.out.println(result);
            return result;

        }catch(Exception e){
            System.out.println("error cipher init "+e.toString());
        }
        return null;
    }
}
