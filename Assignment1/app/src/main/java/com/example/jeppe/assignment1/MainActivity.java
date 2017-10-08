package com.example.jeppe.assignment1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final int INSPECT_PC_DETAILS = 0;
    static String PC_NAME;
    static String MEMORY;
    static String LAPTOP;

    ImageButton btnImage;
    Button btnDetails;
    TextView txtPcName;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public CharSequence memory = "?";
    public CharSequence laptop = "?";
    Bitmap pcImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnImage = (ImageButton) findViewById(R.id.imageButton);
        btnDetails = (Button) findViewById(R.id.btnDetails);
        txtPcName = (TextView) findViewById(R.id.txtPcName);

        PC_NAME = getString(R.string.txt_name);
        LAPTOP = getString(R.string.laptop);
        MEMORY = getString(R.string.txt_memory);

        if(savedInstanceState != null) {
            txtPcName.setText(savedInstanceState.getCharSequence(PC_NAME));
            memory = savedInstanceState.getCharSequence(MEMORY);
            laptop = savedInstanceState.getCharSequence(LAPTOP);
            pcImage = savedInstanceState.getParcelable("BitmapImage");
            btnImage.setImageBitmap(pcImage);
        }

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(PC_NAME, txtPcName.getText());
                intent.putExtra(MEMORY, memory);
                intent.putExtra(LAPTOP, laptop);
                startActivityForResult(intent, INSPECT_PC_DETAILS);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap thumbnail = (Bitmap) extras.get("data");
            btnImage.setImageBitmap(thumbnail);
            pcImage = thumbnail;
        }

         else if(requestCode == INSPECT_PC_DETAILS) {
            if(resultCode == RESULT_OK) {
                txtPcName.setText(data.getCharSequenceExtra(PC_NAME));
                memory = data.getCharSequenceExtra(MEMORY);
                laptop = data.getCharSequenceExtra(LAPTOP);
            }
        }
    }

    // Photo functionality shamelessly stolen from the example at
    //  https://developer.android.com/training/camera/photobasics.html
    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putCharSequence(PC_NAME, txtPcName.getText());
        outState.putCharSequence(MEMORY, memory);
        outState.putCharSequence(LAPTOP, laptop);
        outState.putParcelable("BitmapImage", pcImage);
        super.onSaveInstanceState(outState);
    }
}
