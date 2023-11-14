package com.example.music_toy;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import android.content.Intent;

import android.util.Log;
public class MainActivity extends AppCompatActivity {
    RadioButton r1, r2, r3;
    Button finishBtn;
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
        //playBtn=findViewById(R.id.playBtn);
        finishBtn=findViewById(R.id.finish);
        finishBtn.setVisibility(View.VISIBLE);
        finishBtn.setOnClickListener(new mClick());
    }

    class  mClick implements View.OnClickListener {
        private  static final String TAG = "myTAG";
        public String[] name={"进击的巨人op1","进击的巨人op2","进击的巨人op3"};
        @Override
        public void onClick(View v) {
            Log.d(TAG, "click");
            finishBtn.setOnClickListener(v1 -> {
                if (r1.isChecked()) {

                    Intent intent=new Intent(MainActivity.this,music_play.class);
                    //显示调用ResultActivity
                    intent.putExtra("name",name[0]);
                    intent.putExtra("position",String.valueOf(0));
                    startActivity(intent);

                }
                if (r2.isChecked()) {
                    Intent intent=new Intent(MainActivity.this,music_play.class);
                    //显示调用ResultActivity
                    intent.putExtra("name",name[1]);
                    intent.putExtra("position",String.valueOf(1));

                    startActivity(intent);
                }
                if (r3.isChecked()) {
                    Intent intent=new Intent(MainActivity.this,music_play.class);
                    //显示调用ResultActivity
                    intent.putExtra("name",name[2]);
                    intent.putExtra("position",String.valueOf(2));

                    startActivity(intent);
                }

            });



        }

    }
}
