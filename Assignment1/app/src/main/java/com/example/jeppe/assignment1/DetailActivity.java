package com.example.jeppe.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    static final int EDIT_PC_DETAILS = 1;

    TextView txtPcName;
    TextView txtMemory;
    TextView txtLaptop;
    Button btnEdit;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent data = getIntent();

        txtPcName = (TextView) findViewById(R.id.txtPcName);
        txtMemory = (TextView) findViewById(R.id.txtMemory);
        txtLaptop = (TextView) findViewById(R.id.txtLaptop);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        txtPcName.setText(data.getCharSequenceExtra("PcName"));
        txtMemory.setText(data.getCharSequenceExtra("Memory"));
        txtLaptop.setText(data.getCharSequenceExtra("Laptop"));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, EditDetailActivity.class);
                intent.putExtra("PcName", txtPcName.getText());
                intent.putExtra("Memory", txtMemory.getText());
                intent.putExtra("Laptop", txtLaptop.getText());
                startActivityForResult(intent, EDIT_PC_DETAILS);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_PC_DETAILS && resultCode == RESULT_OK) {
            Intent newPcDetailsIntent = new Intent();
            newPcDetailsIntent.putExtra("PcName", data.getCharSequenceExtra("PcName"));
            newPcDetailsIntent.putExtra("Memory", data.getCharSequenceExtra("Memory"));
            newPcDetailsIntent.putExtra("Laptop", data.getCharSequenceExtra("Laptop"));

            setResult(RESULT_OK, newPcDetailsIntent);
            finish();
        }
    }
}
