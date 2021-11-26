package com.example.ruletarusa_alberto;

import androidx.appcompat.app.AlertDialog;
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
    private final int[] bullets = new int[5];
    Timer t = null;
    static int countb = 0;
    static int shots = 0;
    private ImageView bullet1;
    private ImageView bullet2;
    private ImageView bullet3;
    private ImageView live;
    private ImageView live1;
    private ImageView live2;
    private int lives = 0;
    ObjectAnimator animator = null ;
    final boolean[] b = {false};
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
        bullet1 = findViewById(R.id.imgbullet1);
        bullet2 = findViewById(R.id.imgbullet2);
        bullet3 = findViewById(R.id.imgbullet3);
        live = findViewById(R.id.imglive);
        live1= findViewById(R.id.imglive1);
        live2 = findViewById(R.id.imglive2);
        Button butready = findViewById(R.id.butready);
        ImageButton butpistol = findViewById(R.id.butpistol);

        ImageView smoke = findViewById(R.id.giftsmoke);
        ImageView boom = findViewById(R.id.giftboom);

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
                    if (countb > 0){ cargador.setVisibility(View.VISIBLE); b[0] = true;}
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            cargador.setVisibility(View.INVISIBLE);

                        }
                    }, 2000);   //5 second

                }
            });
            butpistol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (b[0]) {
                        if (bullets[shots] == 1) {
                            smoke.setVisibility(View.VISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    smoke.setVisibility(View.INVISIBLE);
                                }
                            }, 2000);   //5 second

                        } else {
                            boom.setVisibility(View.VISIBLE);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    boom.setVisibility(View.INVISIBLE);
                                }

                            }, 2000);
                            lives++;
                            checkLives();
                            Restart();
                        }

                        shots++;

                    }
                }
            });




    }

    private void checkLives() {

        if(lives == 1) live.setVisibility(View.INVISIBLE);
        else if (lives == 2) live1.setVisibility(View.INVISIBLE);
        else if(lives == 3){

            live2.setVisibility(View.INVISIBLE);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage(R.string.dialog_message)
                    .setTitle(R.string.dialog_title);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Restart();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();




        }


    }

    private void Restart() {

        bullet1.setVisibility(View.INVISIBLE);
        bullet2.setVisibility(View.INVISIBLE);
        bullet3.setVisibility(View.INVISIBLE);
        b[0] = false;
        for (int i = 0; i < 5; i++) {
            bullets[i] = 1;

        }
        countb = 0;
        if (lives ==3)
        {
            live.setVisibility(View.VISIBLE);
            live1.setVisibility(View.VISIBLE);
            live2.setVisibility(View.VISIBLE);
            lives = 0;

        }


    }


}