package com.learzhu.news.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by Learzhu on 2016/10/29.
 */

public class PictureUtil {
    public static void saveImage(ImageView shot, Context context) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        //创建文件夹
        File directory = new File(externalStorageDirectory, "newspicture");
        if (!directory.exists()) {
            directory.mkdir();
        }
        Bitmap drawingCache = shot.getDrawingCache();
        try {
            File file = new File(directory, new Date().getTime() + ".jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            drawingCache.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);
            Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "我出错了", Toast.LENGTH_SHORT).show();
        }
    }
}
