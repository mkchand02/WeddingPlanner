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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.models.UserModel;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextPhone;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private SharedPrefHelper sharedPrefHelper;
    private RadioGroup rbGender;
    private RadioGroup rbType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseApp.initializeApp(this);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        setTitle("User Registration");
        if(firebaseAuth.getCurrentUser()!=null) {
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
                    sharedPrefHelper = new SharedPrefHelper(getApplicationContext());
                    try {
                        sharedPrefHelper.storeStudentData(name, email, photo, gender, phone, status, type);
                    }catch(Exception e){}
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }});
            finish();
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
        }
        progressDialog = new ProgressDialog(this);

        buttonRegister = findViewById(R.id.button_register);
        editTextName = findViewById(R.id.edit_text_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextConfirmPassword = findViewById(R.id.edit_text_c_password);
        editTextPhone = findViewById(R.id.edit_text_phone);
        textViewSignin = findViewById(R.id.text_view_signin);
        rbGender = findViewById(R.id.gender_rg);
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }

    private void registerUser()
    {
        final String name=editTextName.getText().toString().trim();
        final String email=editTextEmail.getText().toString().trim();
        final String gender = rbGender.getCheckedRadioButtonId()==R.id.gender_male?"Male":
                (rbGender.getCheckedRadioButtonId()==R.id.gender_female?"Female":"Other");
        final String type = rbGender.getCheckedRadioButtonId()==R.id.type_bride?"Bride":"Groom";
        String password=editTextPassword.getText().toString().trim();
        String confirmPassword=editTextConfirmPassword.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();
        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }if(TextUtils.isEmpty(password)) {
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }if(TextUtils.isEmpty(password)) {
            Toast.makeText(this,"Please confirm your password",Toast.LENGTH_SHORT).show();
            return;
        }if(!password.equals(confirmPassword)) {
            Toast.makeText(this,"Password and Confirm Password are not same.",Toast.LENGTH_SHORT).show();
            editTextConfirmPassword.setText("");
            return;
        }if(TextUtils.isEmpty(phone)) {
            Toast.makeText(this,"Please enter your Contact Number",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            // user is successfully registered
                            UserModel user = new UserModel(
                                    name, email, gender, type, phone
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(RegisterActivity.this,"Registration Succcessful", Toast.LENGTH_SHORT);
                                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                                finish();
                                            } else {
                                                Log.d("Register Activity Inner",task.getResult().toString());
                                                Toast.makeText(RegisterActivity.this," Could not register.Please try again",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                            });
                        } else {
                            Log.d("Register Activity",task.getResult().toString());
                            Toast.makeText(RegisterActivity.this," Could not register.Please try again",Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:
                registerUser();
                break;
            case R.id.text_view_signin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
