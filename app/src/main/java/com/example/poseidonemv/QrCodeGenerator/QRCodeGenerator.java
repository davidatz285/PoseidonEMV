package com.example.poseidonemv.QrCodeGenerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.EnumMap;

public class QRCodeGenerator {
    private Context context;
    private int mHeight;
    private int mWidth;
    private ErrorCorrectionLevel errorCorrectionLevel;
    private String content;
    private int margin;

    private QRCodeGenerator(Context context){
        mHeight = (int) (context.getResources().getDisplayMetrics().heightPixels / 2.4);
        mWidth = (int) (context.getResources().getDisplayMetrics().widthPixels / 1.3);
    }
    public static QRCodeGenerator newInstance(Context context){
        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator(context);
        return qrCodeGenerator;

    }
    public QRCodeGenerator setErrorCorrectionLevel(ErrorCorrectionLevel level){
        this.errorCorrectionLevel = level;
        return this;
    }
    public QRCodeGenerator setContent(String content){
        this.content = content;
        return this;
    }
    public QRCodeGenerator setMargin(int margin) {
        this.margin = margin;
        return this;
    }
    public Bitmap generate(){
        EnumMap<EncodeHintType, Object> hintsMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
        hintsMap.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        hintsMap.put(EncodeHintType.ERROR_CORRECTION, this.errorCorrectionLevel);
        hintsMap.put(EncodeHintType.MARGIN, this.margin);
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(this.content, BarcodeFormat.QR_CODE, mWidth, mHeight, hintsMap);
            int[] pixels = new int[mWidth * mHeight];
            for (int i = 0; i < mHeight; i++) {
                for (int j = 0; j < mWidth; j++) {
                    if (bitMatrix.get(j, i)) {
                        pixels[i * mWidth + j] = Color.BLACK;
                    } else {
                        pixels[i * mWidth + j] = Color.WHITE;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, mWidth, mHeight, Bitmap.Config.ARGB_8888);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
