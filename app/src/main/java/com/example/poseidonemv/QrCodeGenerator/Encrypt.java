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
import java.nio.ByteOrder;
import java.security.MessageDigest;
import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
    private Cipher cipher;
    private byte[] key;
    private final String initVector="proif-kelompok-2";
    private static final char[] LOOKUP_TABLE_LOWER = new char[]{0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66};
    private static final char[] LOOKUP_TABLE_UPPER = new char[]{0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46};
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
            md5.update(pin.getBytes());
            key = md5.digest();
            String hash = encode(key);
            this.key = md5.digest(key);
            secretKey = new SecretKeySpec(hash.getBytes(),"AES");
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
    public static String encode(byte[] byteArray, boolean upperCase, ByteOrder byteOrder) {

        // our output size will be exactly 2x byte-array length
        final char[] buffer = new char[byteArray.length * 2];

        // choose lower or uppercase lookup table
        final char[] lookup = upperCase ? LOOKUP_TABLE_UPPER : LOOKUP_TABLE_LOWER;

        int index;
        for (int i = 0; i < byteArray.length; i++) {
            // for little endian we count from last to first
            index = (byteOrder == ByteOrder.BIG_ENDIAN) ? i : byteArray.length - i - 1;

            // extract the upper 4 bit and look up char (0-A)
            buffer[i << 1] = lookup[(byteArray[index] >> 4) & 0xF];
            // extract the lower 4 bit and look up char (0-A)
            buffer[(i << 1) + 1] = lookup[(byteArray[index] & 0xF)];
        }
        return new String(buffer);
    }
    public static String encode(byte[] byteArray) {
        return encode(byteArray, false, ByteOrder.BIG_ENDIAN);
    }
}
