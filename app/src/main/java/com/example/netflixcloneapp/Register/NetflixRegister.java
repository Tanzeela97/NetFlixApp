package com.example.netflixcloneapp.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.example.netflixcloneapp.Movie.MovieActivity;
import com.example.netflixcloneapp.R;
import com.example.netflixcloneapp.databinding.NetflixRegisterBinding;
import com.example.netflixcloneapp.login.Netflixlogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NetflixRegister  extends AppCompatActivity {





    private Toolbar toolbar;
private  FirebaseAuth mAuth;

    private ProgressDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setContentView(R.layout.activity_register);


       NetflixRegisterBinding bindReg = DataBindingUtil.setContentView(this, R.layout.netflix_register);
        toolbar = bindReg.RegisterToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registration");

        mAuth =FirebaseAuth.getInstance();
        loader = new ProgressDialog(this);



        bindReg.registerPageQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NetflixRegister.this, Netflixlogin.class);
                startActivity(intent);
            }
        });

        bindReg.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = bindReg.registerEmail.getText().toString().trim();
                String password = bindReg.registerPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    bindReg.registerEmail.setError("email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    bindReg.registerPassword.setError("Password required");
                    return;
                }else {
                    loader.setMessage("Registration in progress");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Intent intent = new Intent(NetflixRegister.this, MovieActivity.class);
                                startActivity(intent);
                                finish();
                                loader.dismiss();
                            }else {
                                String error = task.getException().toString();
                                Toast.makeText(NetflixRegister.this, "Registration failed" + error, Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }

                        }
                    });
                }



            }
        });
    }
}
