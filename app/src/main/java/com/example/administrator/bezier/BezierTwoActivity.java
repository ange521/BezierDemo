package com.example.administrator.bezier;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.bezier.bezierclass.BezierTwo;

/**
 * Author：Tony.Zhou on 2016/11/11 16:13
 * E-mail：781689008@qq.com
 * CSDN :
 * GitHub :
 * If you always so wise, there will be a wonderful life.
 */
public class BezierTwoActivity extends Activity implements View.OnClickListener{

    private Button Mode_One, Mode_Two;
    private BezierTwo mBezierTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_two);
        Mode_One = (Button) findViewById(R.id.mode_one);
        Mode_Two = (Button) findViewById(R.id.mode_two);

        Mode_Two.setOnClickListener(this);
        Mode_One.setOnClickListener(this);

        mBezierTwo = (BezierTwo) findViewById(R.id.bezer_two);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mode_one:
                mBezierTwo.setMode(true);
                break;
            case R.id.mode_two:
                mBezierTwo.setMode(false);
                break;
        }

    }
}
