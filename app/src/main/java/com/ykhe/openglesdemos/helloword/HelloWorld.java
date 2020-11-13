package com.ykhe.openglesdemos.helloword;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.ykhe.openglesdemos.common.IOpenGLDemo;
import com.ykhe.openglesdemos.common.OpenGLRender;

import javax.microedition.khronos.opengles.GL10;

/**
 * author: ykhe
 * date: 20-11-12
 * description:
 */
public class HelloWorld extends Activity implements IOpenGLDemo {
    private GLSurfaceView mGLSurfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setRenderer(new OpenGLRender(this));
        setContentView(mGLSurfaceView);
    }

    @Override
    public void DrawScene(GL10 gl10) {
          gl10.glClearColor(1.0f,0.0f,0.0f,0.0f);
          // Clears the screen and depth buffer.
          gl10.glClear(GL10.GL_COLOR_BUFFER_BIT
                | GL10.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
}
