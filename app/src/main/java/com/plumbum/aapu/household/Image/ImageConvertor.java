package com.plumbum.aapu.household.Image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.plumbum.aapu.household.R;

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

    byte[][] imageResources = new byte[24][];

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

    public byte[][] getAllImages()
    {
        imageResources[0]= convertImageInByte(R.drawable.category_icon_1);
        imageResources[1]= convertImageInByte(R.drawable.category_icon_2);
        imageResources[2]= convertImageInByte(R.drawable.category_icon_3);
        imageResources[3]= convertImageInByte(R.drawable.category_icon_4);
        imageResources[4]= convertImageInByte(R.drawable.category_icon_5);
        imageResources[5]= convertImageInByte(R.drawable.category_icon_6);
        imageResources[6]= convertImageInByte(R.drawable.category_icon_7);
        imageResources[7]= convertImageInByte(R.drawable.category_icon_8);
        imageResources[8]= convertImageInByte(R.drawable.category_icon_9);
        imageResources[9]= convertImageInByte(R.drawable.category_icon_10);
        imageResources[10]= convertImageInByte(R.drawable.category_icon_11);
        imageResources[11]= convertImageInByte(R.drawable.category_icon_12);
        imageResources[12]= convertImageInByte(R.drawable.category_icon_13);
        imageResources[13]= convertImageInByte(R.drawable.category_icon_14);
        imageResources[14]= convertImageInByte(R.drawable.category_icon_15);
        imageResources[15]= convertImageInByte(R.drawable.category_icon_16);
        imageResources[16]= convertImageInByte(R.drawable.category_icon_17);
        imageResources[17]= convertImageInByte(R.drawable.category_icon_18);
        imageResources[18]= convertImageInByte(R.drawable.category_icon_19);
        imageResources[19]= convertImageInByte(R.drawable.category_icon_20);
        imageResources[20]= convertImageInByte(R.drawable.category_icon_21);
        imageResources[21]= convertImageInByte(R.drawable.category_icon_22);
        imageResources[22]= convertImageInByte(R.drawable.category_icon_23);
        imageResources[23]= convertImageInByte(R.drawable.category_icon_24);

        return imageResources;
    }


}
