package com.example.jeppe.lab3_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    Button btnOk;
    Button btnCancel;
    TextView txtEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        txtEdit = (TextView) findViewById(R.id.editText);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent okIntent = new Intent();
                okIntent.putExtra("txtEdit", txtEdit.getText().toString());
                setResult(RESULT_OK, okIntent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent(EditActivity.this, ViewActivity.class);
                Toast.makeText(EditActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED, cancelIntent);
                finish();
            }
        });
    }
}
