package com.kodonho.android.basicwidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 결과값이 출력되는 텍스트뷰
    TextView tv;
    // 라디오그룹
    RadioGroup rg;
    // 체크박스
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    //스위치
    Switch sw;
    //Toggle

    //진행바
    ProgressBar pb;

    //탐색바
    SeekBar sb;
    //탐색바의 값을 입력받는 View
    TextView sb_tv;

    // 등급바
    RatingBar rb;
    TextView rb_tv; // 레이팅바의 값을 입력받는 뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.textView2);

        rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //현재 체크된 라디오버튼아이디를 가져온다
                //int checked = rg.getCheckedRadioButtonId();
                Intent intent = null;
                switch(checkedId){

                    case R.id.rdApple:
                        intent = new Intent(MainActivity.this,TabActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.rdOrange:
                        intent = new Intent(MainActivity.this,DateActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.rdBanana:
                        intent = new Intent(MainActivity.this,TextActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });

        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        cb1.setOnCheckedChangeListener(checkedChangeListener);
        cb2.setOnCheckedChangeListener(checkedChangeListener);
        cb3.setOnCheckedChangeListener(checkedChangeListener);

        sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(compoundButton.isChecked()){
                    tv.setText("스위치가 On 되었습니다");
                    pb.setVisibility(View.VISIBLE);
                }else{
                    tv.setText("스위치 Off");
                    pb.setVisibility(View.INVISIBLE);
                }
            }
        });
        pb = (ProgressBar) findViewById(R.id.progressBar);

        sb = (SeekBar) findViewById(R.id.seekBar);
        sb_tv = (TextView) findViewById(R.id.textView9);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sb_tv.setText(progress+" %");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, seekBar.getProgress()+" 위치에서 터치가 시작됨",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, seekBar.getProgress()+" 위치에서 터치가 종료됨",
                        Toast.LENGTH_SHORT).show();
            }
        });

        rb = (RatingBar) findViewById(R.id.ratingBar2);
        rb_tv = (TextView) findViewById(R.id.textView11);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rb_tv.setText(rating+"/5");
            }
        });

    }

    // 컴파운드 계열(체크박스, 토글, 스위치...등) 버튼에서 사용되는 체크변화를 감지하는 리스너
    CompoundButton.OnCheckedChangeListener checkedChangeListener
            = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            StringBuilder sb = new StringBuilder();
            if(cb1.isChecked()){
                sb.append("Dog ");
            }
            if(cb2.isChecked()){
                sb.append("Pig ");
            }
            if(cb3.isChecked()){
                Intent intent = new Intent(MainActivity.this, SpinnerActivity.class);
                startActivity(intent);
            }
            tv.setText(sb.toString());
        }
    };
}
