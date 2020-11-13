package com.ykhe.openglesdemos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ykhe.openglesdemos.helloword.HelloWorld;
import com.ykhe.openglesdemos.point.DrawPoint;

/**
 * https://wiki.jikexueyuan.com/project/opengl-es-guide/
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_to_hw).setOnClickListener(this);
        findViewById(R.id.btn_to_dp).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_to_hw:
                startActivity(new Intent(this, HelloWorld.class));
                break;
            case R.id.btn_to_dp:
                startActivity(new Intent(this, DrawPoint.class));
                break;
        }
    }
}