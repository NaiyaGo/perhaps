package com.example.testtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class Edit extends AppCompatActivity {

    String beforeStr,nowStr;
    String[] tempStringArr;

    int no;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent it=getIntent();
        no=it.getIntExtra("编号",0);
        s=it.getStringExtra("备忘");
        TextView txv=(TextView) findViewById(R.id.textView);
        txv.setText(no+".");
        EditText edt=(EditText)findViewById(R.id.editText);
        edt.setText(s);
        beforeStr=edt.getText().toString();
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                nowStr=s.toString();

            }
        });

    }
    public void onCancel(View v){

        finish();

    }
    public void onSave(View v){
        Intent it=new Intent();
        it.putExtra("res_no",no);
        it.putExtra("res_string",nowStr);setResult(RESULT_OK,it);
        finish();

    }
}