package com.example.dell.myapplicationdemo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends Activity {
    Button btnGal;
    ImageView ivGalImg;
    Bitmap bmp;
    private static int RESULT_LOAD_IMG = 1;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGal = (Button) findViewById(R.id.buttonLoadPicture);
        ivGalImg = (ImageView) findViewById(R.id.imgView);

        btnGal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                openGallery();
            }
        });
    }

    private void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultcode, Intent intent) {
        super.onActivityResult(requestCode, resultcode, intent);

        if (requestCode == 1) {
            if (intent != null && resultcode == RESULT_OK) {

                Uri selectedImage = intent.getData();

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();

                if (bmp != null && !bmp.isRecycled()) {
                    bmp = null;
                }

                bmp = BitmapFactory.decodeFile(filePath);
                ivGalImg.setBackgroundResource(0);
                ivGalImg.setImageBitmap(bmp);
            } else {
                Log.d("Status:", "Photopicker canceled");
            }
        }
    }
}