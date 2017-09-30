package com.example.jeppe.lab3_2;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends AppCompatActivity {

        public final static int EDIT_TEXT_REQ = 1;

    Button btnEdit;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        btnEdit = (Button) findViewById(R.id.btnEdit);
        txt = (TextView) findViewById(R.id.txt);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewActivity.this, EditActivity.class);
                startActivityForResult(intent, EDIT_TEXT_REQ);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case EDIT_TEXT_REQ: {
                if(data != null) {
                    Toast.makeText(this, data.getStringExtra("txtEdit"), Toast.LENGTH_SHORT).show();
                    txt.setText(data.getStringExtra("txtEdit"));
                    Toast.makeText(this, "result ok", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
