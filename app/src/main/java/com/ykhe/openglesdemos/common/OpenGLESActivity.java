package com.ykhe.openglesdemos.common;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.ykhe.openglesdemos.common.IOpenGLDemo;
import com.ykhe.openglesdemos.common.OpenGLRender;

import javax.microedition.khronos.opengles.GL10;

/**
 * author: ykhe
 * date: 20-11-12
 * description:
 */
public class OpenGLESActivity extends Activity implements IOpenGLDemo {

    private GLSurfaceView mGLSurfaceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setRenderer(new OpenGLRender(this));
        setContentView(mGLSurfaceView);
    }

    /**
     *
     首先是使用 FloatBuffer 存放三个顶点坐标。
     使用 glColor4f(float red, float green, float blue, float alpha) 将当前颜色设为红色。
     glPointSize(float size) 可以用来设置绘制点的大小。
     使用 glEnableClientState 打开 Pipeline 的Vectex 顶点“开关”
     使用 glVertexPointer 通知 OpenGL ES 图形库顶点坐标。
     使用 GL_POINTS 模式使用 glDrawArrays 绘制 3 个顶点。
     * @param gl
     */
    @Override
    public void DrawScene(GL10 gl) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        // OpenGL ES 内部存放图形数据的 Buffer 有 COLOR ,DEPTH (深度信息）等，
        // 在绘制图形之前一般需要清空 COLOR 和 DEPTH Buffer。
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT
                | GL10.GL_DEPTH_BUFFER_BIT);


    }
}
