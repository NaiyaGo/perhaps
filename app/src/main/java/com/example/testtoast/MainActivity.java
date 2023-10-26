package com.example.testtoast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity  {

    static String[] aMemo={"1.单击可以编辑备忘","2.长按可以清除备忘","3.","4.","5.","6."};
    String[] temp={"1.单击可以编辑备忘","2.长按可以清除备忘","3.dasdassdas","4.dasdsad","5.fefsg","6.deawe"};
    ListView lv;
    ArrayAdapter<String>aa;
    int cnt=1;

    void setaMemo(int postion,String str){
        aMemo[postion]=str;
    }
    String getaMemo(int position){
        return aMemo[position];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultLauncher launcher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if(o.getResultCode()==RESULT_OK){
                            int no=o.getData().getIntExtra("res_no",0);
                            String nowStr=o.getData().getStringExtra("res_string");
                            aMemo[no-1]=nowStr;
                            aa.notifyDataSetChanged();
                        }
                    }
                }

        );

        lv=findViewById(R.id.listView);
        aa=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,aMemo);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(MainActivity.this,Edit.class);
                it.putExtra("编号",position+1);
                it.putExtra("备忘",aMemo[position]);
                launcher.launch(it);

            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                aMemo[position]=(position+1)+".";
                aa.notifyDataSetChanged();


                return true;
            }
        });

    }


}