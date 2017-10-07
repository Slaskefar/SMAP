package com.example.jeppe.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditDetailActivity extends AppCompatActivity {

    EditText editTxtName;
    EditText editTxtMemory;
    RadioGroup radioGroupLaptop;
    Button btnOk;
    Button btnCancel;
    RadioButton radioButtonYes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);

        Intent data = getIntent();

        editTxtName = (EditText) findViewById(R.id.editTxtName);
        editTxtMemory = (EditText) findViewById(R.id.editTxtMemory);
        radioGroupLaptop = (RadioGroup) findViewById(R.id.radioGroupLaptop);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        radioButtonYes = (RadioButton) findViewById(R.id.radioButtonYes);

        editTxtName.setText(data.getCharSequenceExtra("PcName"));
        editTxtMemory.setText(data.getCharSequenceExtra("Memory"));

        if(data.getCharSequenceExtra("Laptop").toString() == "Yes") {
            radioGroupLaptop.check(R.id.radioButtonYes);
        } else if (data.getCharSequenceExtra("Laptop").toString() == "No") {
            radioGroupLaptop.check(R.id.radioButtonNo);
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("PcName", editTxtName.getText());
                intent.putExtra("Memory", editTxtMemory.getText());
                CharSequence laptop;
                if(radioGroupLaptop.getCheckedRadioButtonId() == radioButtonYes.getId()) {
                    laptop = getString(R.string.yes);
                } else {
                    laptop = getString(R.string.no);
                }
                intent.putExtra("Laptop", laptop);
                setResult(RESULT_OK, intent);
                finish();
             }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });


    }
}
