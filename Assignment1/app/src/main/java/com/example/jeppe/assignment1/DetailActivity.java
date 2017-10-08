package com.example.jeppe.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    static final int EDIT_PC_DETAILS = 1;
    static String PC_NAME;
    static String MEMORY;
    static String LAPTOP;

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

        PC_NAME = getString(R.string.txt_name);
        LAPTOP = getString(R.string.laptop);
        MEMORY = getString(R.string.txt_memory);

        txtPcName = (TextView) findViewById(R.id.txtPcName);
        txtMemory = (TextView) findViewById(R.id.txtMemory);
        txtLaptop = (TextView) findViewById(R.id.txtLaptop);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        txtPcName.setText(data.getCharSequenceExtra(PC_NAME));
        txtMemory.setText(data.getCharSequenceExtra(MEMORY));
        txtLaptop.setText(data.getCharSequenceExtra(LAPTOP));

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
                intent.putExtra(PC_NAME, txtPcName.getText());
                intent.putExtra(MEMORY, txtMemory.getText());
                intent.putExtra(LAPTOP, txtLaptop.getText());
                startActivityForResult(intent, EDIT_PC_DETAILS);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_PC_DETAILS && resultCode == RESULT_OK) {
            Intent newPcDetailsIntent = new Intent();
            newPcDetailsIntent.putExtra(PC_NAME, data.getCharSequenceExtra(PC_NAME));
            newPcDetailsIntent.putExtra(MEMORY, data.getCharSequenceExtra(MEMORY));
            newPcDetailsIntent.putExtra(LAPTOP, data.getCharSequenceExtra(LAPTOP));

            setResult(RESULT_OK, newPcDetailsIntent);
            finish();
        }
    }
}
