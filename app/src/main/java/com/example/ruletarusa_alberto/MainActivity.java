package com.example.ruletarusa_alberto;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.Path;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Property;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    private int bullets [] = new int[5];
    Timer t = null;
    static int countb = 0;
    ObjectAnimator animator = null ;
    Thread i = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 5; i++) {
            bullets[i] = 1;

        }
        ImageButton bullet = findViewById(R.id.imageButton2);
        Button butbullet = findViewById(R.id.butbullet);
        ImageView cargador = findViewById(R.id.giftambor);
        ImageView disparo = findViewById(R.id.gifbang);
        ImageView bullet1 = findViewById(R.id.imgbullet1);
        ImageView bullet2 = findViewById(R.id.imgbullet2);
        ImageView bullet3 = findViewById(R.id.imgbullet3);
        Button butready = findViewById(R.id.butready);
        butready.setClickable(false);
        bullet.setX(100);
        bullet.setY(100);
        bullet.setVisibility(View.INVISIBLE);



            butbullet.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (countb <3) {
                        bullet.setVisibility(View.VISIBLE);
                        Random rd = new Random();
                        int num = rd.nextInt(5);
                        bullets[num] = 2;
                        DisplayMetrics d = new DisplayMetrics();
                        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                        int result = 0;
                        if (resId > 0) {
                            result = getResources().getDimensionPixelSize(resId);
                        }
                        Toast t1 = Toast.makeText(getApplicationContext(), String.valueOf(result), Toast.LENGTH_LONG);
                        // t1.show();
                        Path path = new Path();

                        path.arcTo(-1580f, 150f, 540f, 1500f, -290f, -180f, true);
                        animator = ObjectAnimator.ofFloat(bullet, View.X, View.Y, path);
                        animator.setDuration(500);
                        animator.start();
                        //esperar(bullet);
                        countb++;
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                if(countb == 1) bullet1.setVisibility(View.VISIBLE);
                                else if(countb ==2) bullet2.setVisibility(View.VISIBLE);
                                else bullet3.setVisibility(View.VISIBLE);
                            }
                        }, 500);   //5 second

                    }//end if
                }
            });// end on click


            butready.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countb > 0) cargador.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            cargador.setVisibility(View.INVISIBLE);
                        }
                    }, 2000);   //5 second



                }
            });


    }




}