package com.example.beomseok.greekday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.beomseok.greekday.model.User;
import com.example.beomseok.greekday.util.NetWorkListener;
import com.example.beomseok.greekday.util.NetworkTask;
import com.google.firebase.database.DatabaseReference;

public class SelectNickNameActivity extends AppCompatActivity {
    Toolbar toolbar;
    CardView cardView;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_nick_name);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("닉네임 설정");
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("userInfo");


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cardView = (CardView) findViewById(R.id.btn_ok);
        editText1 = (EditText) findViewById(R.id.editText_nickname);
        editText2 = (EditText) findViewById(R.id.editText_phone_number);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText1.getText().toString() == null || editText1.getText().toString().equals("") || editText2.getText().toString() == null || editText2.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "빈칸을 채워주세요", Toast.LENGTH_SHORT).show();
                    NetworkTask networkTask = new NetworkTask(new NetWorkListener() {

                        @Override
                        public void postSucceed(Object object) {
                            Log.d("thisis",object.toString());
                        }
                    });
                    networkTask.execute();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }
}
