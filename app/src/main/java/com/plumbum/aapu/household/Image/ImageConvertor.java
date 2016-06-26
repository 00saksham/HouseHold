package com.plumbum.aapu.household.Image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Dawn on 6/25/2016.
 */
public class ImageConvertor
{

    protected Context context;
    public ImageConvertor(Context context)
    {
        this.context = context.getApplicationContext();
    }
    Bitmap bitmapImage;
    ByteArrayOutputStream byteArrayOutputStream;

    public byte[] convertImageInByte(int resourceImageId)
    {

        byteArrayOutputStream = new ByteArrayOutputStream();

        bitmapImage = BitmapFactory.decodeResource(context.getResources(), resourceImageId);
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();

    }

    public Bitmap convertByteInImage(byte[] imageByte)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
        return bitmap;
    }


}
