package com.example.netflixcloneapp.login;

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
import com.example.netflixcloneapp.Register.NetflixRegister;
import com.example.netflixcloneapp.databinding.NetflixLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Netflixlogin extends AppCompatActivity {


    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private Toolbar toolbar;
    private EditText LoginEmail, LoginPwd;
    private Button LoginBtn;
    private TextView LoginTextQes;
    private ProgressDialog Loader;
    private TextView text_email;
    private TextView txt_pass;
    private FirebaseAuth mAuth;
    private ProgressDialog loader;
    private FirebaseAuth.AuthStateListener authStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        NetflixLoginBinding bind = DataBindingUtil.setContentView(this, R.layout.netflix_login);
        Loader = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(Netflixlogin.this, MovieActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        toolbar = bind.loginToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
//onclicklogin
        bind.loginPageQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Netflixlogin.this, NetflixRegister.class);
                startActivity(intent);
            }
        });

        bind.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = bind.loginEmail.getText().toString().trim();
                String password = bind.loginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    bind.loginEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    bind.loginPassword.setError("Password is required");
                    return;
                } else {
                    Loader.setMessage("Login in progress");
                    Loader.setCancelable(false);
                    // Loader.setCanceledOnTouchOutside(false);
                    Loader.show();

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Intent intent = new Intent(Netflixlogin.this, MovieActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                String error = task.getException().toString();
                                Toast.makeText(Netflixlogin.this, "Login failed" + error, Toast.LENGTH_SHORT).show();

                            }
                            Loader.dismiss();
                        }

                    });


                }


            }
        });
    }
}