package com.example.ali.databasepk;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.spec.ECField;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper db ;
    Button saveB,showB,updateB,deleteB;
    EditText nameET,classET,idET;
    String nameETD,classETD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new DataBaseHelper(this);
         nameET = (EditText) findViewById(R.id.nameET);
         nameETD = nameET.getText().toString();
         classET = (EditText) findViewById(R.id.classET);
         classETD = classET.getText().toString();
        idET=(EditText) findViewById(R.id.idET);
        saveB = (Button) findViewById(R.id.saveB);
        addData();// can you do it in a better way ?
        showB=( Button) findViewById(R.id.showB);
        showB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =db.getAllData();
                if(res.getCount()==0 ) Log.v("Show :", "Cursor count = 0 ");
                StringBuffer stringBuffer= new StringBuffer();
                while(res.moveToNext()){
                    stringBuffer.append(db.idColumn1+res.getString(0)+"\n");
                    stringBuffer.append(db.nameColumn2+res.getString(1)+"\n");
                    stringBuffer.append(db.classColumn3+res.getString(2)+"\n");
                    stringBuffer.toString();
                }
                AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Data");
                builder.setMessage(stringBuffer);
                builder.show();
            }
        });
        updateB=(Button) findViewById(R.id.updateB);
        updateB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Boolean updated=  db.updateData(idET.getText().toString(),nameET.getText().toString(),classET.getText().toString());
             if(updated)
                 Toast.makeText(MainActivity.this,"Update Done!",Toast.LENGTH_LONG).show();
                else
                 Toast.makeText(MainActivity.this,"Update Failed!",Toast.LENGTH_LONG).show();
            }
        });
        deleteB= (Button)findViewById(R.id.deleteB);
        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Integer deleted=   db.deleteData(idET.getText().toString());
                if(deleted>0) Toast.makeText(MainActivity.this,"Deleted!",Toast.LENGTH_LONG).show();
                else Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        });

    }
    public void addData (){
        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = db.insertData( nameET.getText().toString(),classET.getText().toString());
                if(isInserted==true) {
                    Toast.makeText(MainActivity.this,"Data Inserted "+ nameETD+" "+classETD,Toast.LENGTH_LONG).show();
                    Log.v("Data :",nameET.getText().toString()+" ,"+classET.getText().toString());
                }
                else {
                    Toast.makeText(MainActivity.this,"Faild to insert data",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
