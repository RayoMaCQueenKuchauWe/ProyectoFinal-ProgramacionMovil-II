package com.example.proyectofinal_programacionmovil_ii;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    //Variables
    Animation topAnim, bottomAnim;
    ImageView ivIgnis;
    TextView tvEmail, tvCopy, tvMembers, tvTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        //Hooks
        ivIgnis = findViewById(R.id.ivIgnis);
        tvTeam = findViewById(R.id.tvTeam);
        tvEmail = findViewById(R.id.tvEmail);
        tvMembers = findViewById(R.id.tvMembers);
        tvCopy = findViewById(R.id.tvCopy);

        ivIgnis.setAnimation(topAnim);
        tvTeam.setAnimation(topAnim);
        tvEmail.setAnimation(bottomAnim);
        tvMembers.setAnimation(bottomAnim);
        tvCopy.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}
