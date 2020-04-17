package com.bignose.realmdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bignose.realmdatabase.model.Students;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name,mark;
    Button save;
    TextView log;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.edit);
        mark = findViewById(R.id.edit2);
        save = findViewById(R.id.btn);
        log = findViewById(R.id.log);
        realm = Realm.getDefaultInstance();
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        wirteToDB(name.getText().toString().trim(),Integer.parseInt(mark.getText().toString().trim()));
        showData();
    }

    private void showData() {
        RealmResults<Students> guests = realm.where(Students.class).findAll();
        String op="";
        realm.beginTransaction();
        for(Students guest:guests){
            op+=guest.toString();
        }
        log.setText(op);
        realm.commitTransaction();
    }


    private void wirteToDB(final String trim, final int parseInt) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Students students = bgRealm.createObject(Students.class);
                students.setName(trim);
                students.setMarks(parseInt);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.v("thành công","thành công");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.v("Database",error.getMessage());
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
