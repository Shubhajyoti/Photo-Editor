package com.example.dell.democartoon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    private Integer images[] = {R.drawable.assassins_creed_unity, R.drawable.bicycle, R.drawable.lion,R.drawable.face};
    private int currImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialImage();
        setImageRotateListener();
    }

    private void setImageRotateListener() {
        final Button btnRotateImage = (Button) findViewById(R.id.btnRotateImage);
        btnRotateImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                currImage++;
                if (currImage == 4) {
                    currImage = 0;
                }
                setCurrentImage();
            }
        });
    }

    private void setInitialImage() {
        setCurrentImage();
    }

    private void setCurrentImage() {

        final ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
        imageView.setImageResource(images[currImage]);

    }
}