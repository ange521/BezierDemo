package com.example.administrator.bezier;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button go_bezer_one, go_bezer_two, go_bezer_three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go_bezer_one = (Button) findViewById(R.id.button_bezer_one);
        go_bezer_two = (Button) findViewById(R.id.button_bezer_two);
        go_bezer_three = (Button) findViewById(R.id.button_bezer_three);

        go_bezer_one.setOnClickListener(this);
        go_bezer_two.setOnClickListener(this);
        go_bezer_three.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_bezer_one:
                Intent intent = new Intent(this,BezierOneActivity.class);
                        startActivity(intent);
                break;
            case R.id.button_bezer_two:
                Intent intent2 = new Intent(this,BezierTwoActivity.class);
                startActivity(intent2);
                break;
            case R.id.button_bezer_three:
                Intent intent3 = new Intent(this,BezierThreeActivity.class);
                startActivity(intent3);
                break;
        }

    }
}
