package com.example.lenovo.myapplication.View;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

import static com.example.lenovo.myapplication.Bean.ShanYaoApplication.getContext;

/**
 * Created by lenovo on 2018/1/2.
 */

public class Utilea {


    public static String getImagePath(Uri uri) {
        String path = null;
        Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Log.i("TAG", "cursor.getColumnIndex(MediaStore.Images.Media.DATA:" + cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


    public static BmobDate getitem(String item) {
        BmobDate bmobDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = dateFormat.parse(item);
            bmobDate = new BmobDate(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bmobDate;
    }


    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static String getSmallBitmap(Uri uri) {
        String filePath = getImagePath(uri);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, 700, 700);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        Uri aa = Uri.parse(MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bitmap, null, null));
        String imagePath = getImagePath(aa);
        return imagePath;
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


    public static void downloadFile(BmobFile file, final Jiekou jiekou) {
        File saveFile = new File(Environment.getExternalStorageDirectory(), file.getFilename());
        file.download(saveFile, new DownloadFileListener() {
            @Override
            public void done(String savePath, BmobException e) {
                if (e == null) {
                    jiekou.getchenggong(savePath);

                    Log.i("bmob", "下载成功,保存路径:" + savePath);
                } else {
                    Log.i("bmob", "下载失败：" + e.getErrorCode() + "," + e.getMessage());
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmob", "下载进度：" + value + "," + newworkSpeed);
            }

        });
    }


    public interface Jiekou {
        void getchenggong(String savePath);
    }


}
