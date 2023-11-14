package com.example.music_toy;

import static java.lang.Integer.parseInt;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class music_play extends AppCompatActivity implements View.OnClickListener{
    public static int[] icons={R.drawable.bgc1,R.drawable.bgc2,R.drawable.bgc3};
    public static int[] musics={R.raw.mtest1,R.raw.mtest2,R.raw.mtest3};
    private static SeekBar sb;
    private static TextView tv_progress,tv_total,song_name;
    private ObjectAnimator animator;
    //private MusicService.MusicControl musicControl;
    String name;
    Intent intent1,intent2;
    boolean isplaying;

    MediaPlayer mplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_play);
        intent1=getIntent();
        init();
    }
    private void init(){
        isplaying=false;
    //绑定ID
        tv_progress=(TextView)findViewById(R.id.tv_progress);
        tv_total=(TextView)findViewById(R.id.tv_total);
        sb=(SeekBar) findViewById(R.id.sb);
       song_name=(TextView) findViewById(R.id.song_name);

       findViewById(R.id.btn_pause).setOnClickListener(this);
       findViewById(R.id.btn_start).setOnClickListener(this);
       findViewById(R.id.btn_exit).setOnClickListener(this);

       name=intent1.getStringExtra("name");
       song_name.setText(name);

        //为滑动条添加事件监听
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //进度条改变时，会调用此方法
                if (progress==seekBar.getMax()){//当滑动条到末端时，结束动画
                    animator.pause();//停止播放动画
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {//滑动条开始滑动时调用
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {//滑动条停止滑动时调用
                //根据拖动的进度改变音乐播放进度
                //int progress=seekBar.getProgress();//获取seekBar的进度
            }
        });
       //设置图片和图片旋转效果
        ImageView iv_music=(ImageView)findViewById(R.id.iv_music);
        String position= intent1.getStringExtra("position");
        int i=parseInt(position);
        iv_music.setImageResource(icons[i]);


        animator=ObjectAnimator.ofFloat(iv_music,"rotation",0f,360.0f);
        animator.setDuration(10000);//动画旋转一周的时间为10秒
        animator.setInterpolator(new LinearInterpolator());//匀速
        animator.setRepeatCount(-1);//-1表示设置动画无限循环

    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onClick(View v) {
        String position;
        int i;
        if(v.getId()==R.id.btn_start){
            position=intent1.getStringExtra("position");
            i=parseInt(position);
            if (mplayer != null && isplaying) {
                mplayer.release();
                mplayer.reset();
            }
            mplayer = MediaPlayer.create(this, musics[i]);
            Toast.makeText(this, "播放开始！", Toast.LENGTH_SHORT).show();
            mplayer.start();
            animator.start();
        }
        else if(v.getId()==R.id.btn_pause){
            if ( mplayer !=null&&isplaying) {
                Toast.makeText(this, "播放暂停！", Toast.LENGTH_SHORT).show();
                mplayer.pause();
                isplaying=false;
                animator.pause();
            }
            else{
                Toast.makeText(this, "播放继续！", Toast.LENGTH_SHORT).show();
                mplayer.start();
                isplaying=true;
                animator.start();
            }
        }
        else if(v.getId()==R.id.btn_exit){
            mplayer.release();
            Toast.makeText(this, "播放停止！", Toast.LENGTH_SHORT).show();
            intent2=new Intent(this,MainActivity.class);
            startActivity(intent2);
        }

        if(mplayer != null && isplaying)
        mplayer.setOnCompletionListener(mplayer -> {
            mplayer.release();
            Toast.makeText(this, "播放结束！", Toast.LENGTH_SHORT).show();
            isplaying = false;
        });
    }

}
