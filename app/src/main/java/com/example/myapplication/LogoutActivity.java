package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class LogoutActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(LogoutActivity.this,LoginActivity.class));
        }
        Intent intent = getIntent();
        String received = intent.getStringExtra("key");
        setTitle(received);

        logout = findViewById(R.id.btn_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                if (getIntent().getBooleanExtra("EXIT", false)) {
                    finish();
                }
            }
        });

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_engagement:
                Intent intent = new Intent(LogoutActivity.this,LogoutActivity.class);
                intent.putExtra("key","VENUE");
                startActivity(intent);
                break;
            case R.id.btn_mehendi:
                Intent intent1 = new Intent(LogoutActivity.this,LogoutActivity.class);
                intent1.putExtra("key","DECOR");
                startActivity(intent1);
                break;
            case R.id.btn_sangeet:
                Intent intent2 = new Intent(LogoutActivity.this,LogoutActivity.class);
                intent2.putExtra("key","SANGEET");
                startActivity(intent2);
                break;
            case R.id.btn_reception:
                Intent intent3 = new Intent(LogoutActivity.this,LogoutActivity.class);
                intent3.putExtra("key","RECEPTION");
                startActivity(intent3);
                break;
            case R.id.btn_wedding:
                Intent intent4 = new Intent(LogoutActivity.this,LogoutActivity.class);
                intent4.putExtra("key","WEDDING");
                startActivity(intent4);
                break;
        }
    }
}
