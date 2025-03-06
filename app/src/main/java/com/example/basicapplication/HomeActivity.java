package com.example.basicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    ImageButton logoutBtn;
    TextView txtEmail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        txtEmail = findViewById(R.id.txtEmail);
        mAuth = FirebaseAuth.getInstance();
        logoutBtn = findViewById(R.id.logoutBtn);
        FirebaseUser currentUser =  mAuth.getCurrentUser();

        String email = "";

        if (currentUser != null){
            email = currentUser.getEmail();
        }

        txtEmail.setText("Hi, " + email);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(HomeActivity.this,"Logging out...",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser =  mAuth.getCurrentUser();

        if (currentUser == null){
            reload();
        }
    }
    public void reload(){
        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}