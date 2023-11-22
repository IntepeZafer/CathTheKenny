package com.zafer.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    int S1 = 0;
    ImageView[] imageArrays;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageArrays = new ImageView[] {imageView2 , imageView3 , imageView4 , imageView5};
        hideImages();
        new CountDownTimer(10000 , 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                textView.setText("Game Over");
                handler.removeCallbacks(runnable);
                for (ImageView imageView : imageArrays){
                    imageView.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart?");
                alert.setMessage("Are You Sure Message");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this , "Game Over" , Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
            }
        }.start();
    }
    public void imageClick(View view){
        textView2.setText("Score: " + S1++);
    }

    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView imageView : imageArrays){
                    imageView.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(3);
                imageArrays[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this , 500);
            }
        };
        handler.post(runnable);
    }
}