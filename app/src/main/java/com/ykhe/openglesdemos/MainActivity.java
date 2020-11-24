package com.ykhe.openglesdemos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ykhe.openglesdemos.helloword.HelloWorld;
import com.ykhe.openglesdemos.line.DrawLine;
import com.ykhe.openglesdemos.point.DrawPoint;
import com.ykhe.openglesdemos.sides.DrawSides20;
import com.ykhe.openglesdemos.solar_system.DrawSolarSystem;
import com.ykhe.openglesdemos.triangle.DrawTriangle;

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
        findViewById(R.id.btn_to_dl).setOnClickListener(this);
        findViewById(R.id.btn_to_dt).setOnClickListener(this);
        findViewById(R.id.btn_to_ds).setOnClickListener(this);
        findViewById(R.id.btn_to_dss).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_to_hw:
                startActivity(new Intent(this, HelloWorld.class));
                break;
            case R.id.btn_to_dp:
                startActivity(new Intent(this, DrawPoint.class));
                break;
            case R.id.btn_to_dl:
                startActivity(new Intent(this, DrawLine.class));
                break;
            case R.id.btn_to_dt:
                startActivity(new Intent(this, DrawTriangle.class));
            case R.id.btn_to_ds:
                startActivity(new Intent(this, DrawSides20.class));
            case R.id.btn_to_dss:
                startActivity(new Intent(this, DrawSolarSystem.class));
            default:
                break;
        }
    }
}