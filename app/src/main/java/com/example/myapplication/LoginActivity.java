package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.util.SharedPrefHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    SharedPrefHelper sharedPrefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("User Login");
        sharedPrefHelper = new SharedPrefHelper(this);
        FirebaseApp.initializeApp(this);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            String user=firebaseAuth.getCurrentUser().getUid();
            firebaseDatabase.getReference("Users").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                String name,email,photo,gender,phone,type;
                int[] status = new int[6];
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        switch (dataSnapshot1.getKey()){
                            case "name":
                                name = dataSnapshot1.getValue().toString();
                                break;
                            case "email":
                                email = dataSnapshot1.getValue().toString();
                                break;
                            case "gender":
                                gender = dataSnapshot1.getValue().toString();
                                break;
                            case "phone":
                                phone = dataSnapshot1.getValue().toString();
                                break;
                            case "photo":
                                photo = dataSnapshot1.getValue().toString();
                                break;
                            case "type":
                                type = dataSnapshot1.getValue().toString();
                                break;
                            case "status1":
                                status[0] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                break;
                            case "status2":
                                status[1] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                break;
                            case "status3":
                                status[2] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                break;
                            case "status4":
                                status[3] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                break;
                            case "status5":
                                status[4] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                break;
                            case "status6":
                                status[5] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                break;
                        }
                    }
                    Log.d("LoginAct",name+email+photo+gender+phone+type+status.toString());
                    sharedPrefHelper.storeStudentData(name,email,photo,gender,phone,status,type);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            finish();
            if(sharedPrefHelper.getUserData("status1",true)==0)
                startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
            else
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        }


        editTextEmail =findViewById(R.id.editTextEmail);
        editTextPassword =findViewById(R.id.editTextPassword);
        buttonSignIn=findViewById(R.id.buttonSignIn);
        textViewSignUp=findViewById(R.id.textViewSignUp);
        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }

    private void userLogin()
    {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;

        }
        progressDialog.setMessage("Getting logged in ...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            firebaseDatabase.getReference("Users").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                String name,email,photo,gender,phone,type;
                                int[] status = new int[6];
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                                        switch (dataSnapshot1.getKey()){
                                            case "name":
                                                name = dataSnapshot1.getValue().toString();
                                                break;
                                            case "email":
                                                email = dataSnapshot1.getValue().toString();
                                                break;
                                            case "gender":
                                                gender = dataSnapshot1.getValue().toString();
                                                break;
                                            case "phone":
                                                phone = dataSnapshot1.getValue().toString();
                                                break;
                                            case "photo":
                                                photo = dataSnapshot1.getValue().toString();
                                                break;
                                            case "type":
                                                type = dataSnapshot1.getValue().toString();
                                                break;
                                            case "status1":
                                                status[0] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                                break;
                                            case "status2":
                                                status[1] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                                break;
                                            case "status3":
                                                status[2] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                                break;
                                            case "status4":
                                                status[3] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                                break;
                                            case "status5":
                                                status[4] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                                break;
                                            case "status6":
                                                status[5] = Integer.parseInt(dataSnapshot1.getValue().toString());
                                                break;
                                        }
                                    }
                                    sharedPrefHelper.storeStudentData(name,email,photo,gender,phone,status,type);
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }});
                            finish();
                            if(sharedPrefHelper.getUserData("status1",true)==0)
                                startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                            else
                                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT);
                        }
                        progressDialog.dismiss();

                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v==buttonSignIn) {
            userLogin();
        }
        if(v==textViewSignUp) {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}
