package com.example.music_toy;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import android.util.Log;
public class MainActivity extends AppCompatActivity {
    RadioButton r1, r2, r3;
    Button playBtn, stopBtn, pauseBtn;
    MediaPlayer mplayer;
    private boolean isPlaying;
    private boolean ispausing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r1 =  findViewById(R.id.radioButton1);
        r2 =  findViewById(R.id.radioButton2);
        r3 =  findViewById(R.id.radioButton3);
        playBtn=findViewById(R.id.playBtn);
        stopBtn=findViewById(R.id.stopBtn);
        pauseBtn=findViewById(R.id.pauseBtn);
        playBtn.setVisibility(View.VISIBLE);
        pauseBtn.setVisibility(View.VISIBLE);
        isPlaying = false;
        ispausing=false;
        playBtn.setOnClickListener(new mClick());
        stopBtn.setOnClickListener(new mClick());
        pauseBtn.setOnClickListener(new mClick());
    }

    class  mClick implements View.OnClickListener {
        private  static final String TAG = "myTAG";
        @Override
        public void onClick(View v) {
            Log.d(TAG, "click");
            playBtn.setOnClickListener(v1 -> {
                if (r1.isChecked()) {
                    Log.d(TAG, "check1");
                    if (mplayer != null && isPlaying) {
                        mplayer.release();
                        mplayer.reset();
                        Log.d(TAG, "clickif");
                    }
                    mplayer = MediaPlayer.create(MainActivity.this, R.raw.mtest1);
                }
                if (r2.isChecked()) {
                    Log.d(TAG, "check2");
                    if (mplayer != null && isPlaying) {
                        mplayer.release();
                        mplayer.reset();
                    }
                    mplayer = MediaPlayer.create(MainActivity.this, R.raw.mtest2);
                }
                if (r3.isChecked()) {
                    Log.d(TAG, "check3");
                    if (mplayer != null && isPlaying) {
                        mplayer.release();
                        mplayer.reset();
                    }
                    mplayer = MediaPlayer.create(MainActivity.this, R.raw.mtest3);
                }

                if (mplayer != null && !isPlaying) {
                    playBtn.setVisibility(View.GONE);
                    pauseBtn.setVisibility(View.VISIBLE);
                    isPlaying = true;
                    mplayer.start();
                    Log.d(TAG, "bofaang");
                    mplayer.setOnCompletionListener(mplayer -> {
                        mplayer.release();
                        Toast.makeText(MainActivity.this, "播放结束！", Toast.LENGTH_SHORT).show();
                        playBtn.setVisibility(View.VISIBLE);
                        isPlaying = false;
                    });
                }
            });

            pauseBtn.setOnClickListener(v12 -> {
                if (isPlaying) {
                    mplayer.pause();
                    Toast.makeText(MainActivity.this, "播放暂停！", Toast.LENGTH_SHORT).show();
                    isPlaying = false;
                    ispausing=true;
                }
                else {
                    mplayer.start();
                    Toast.makeText(MainActivity.this, "继续播放！", Toast.LENGTH_SHORT).show();
                    isPlaying = true;
                    ispausing=false;
                }
            });

            stopBtn.setOnClickListener(v13 -> {
                if (isPlaying) {
                    mplayer.release();
                    //mplayer.reset();
                    Toast.makeText(MainActivity.this, "播放停止！", Toast.LENGTH_SHORT).show();
                    playBtn.setVisibility(View.VISIBLE);
                    isPlaying = false;
                    ispausing=false;
                }
                else{
                    Toast.makeText(MainActivity.this, "播放停止！", Toast.LENGTH_SHORT).show();
                    playBtn.setVisibility(View.VISIBLE);
                    isPlaying = false;
                    if(ispausing){
                        mplayer.release();
                        //mplayer.reset();
                        ispausing=false;
                    }

                }
            });

        }

    }
}
