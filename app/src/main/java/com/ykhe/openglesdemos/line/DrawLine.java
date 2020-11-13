package com.ykhe.openglesdemos.line;

import android.os.Bundle;

import com.ykhe.openglesdemos.common.OpenGLESActivity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * author: ykhe
 * date: 20-11-13
 * description:
 */
public class DrawLine extends OpenGLESActivity {
    //定义四个顶点
    float vertexArray[] = {
            -0.8f, -0.4f * 1.732f, 0.0f,
            -0.4f, 0.4f * 1.732f, 0.0f,
            0.0f, -0.4f * 1.732f, 0.0f,
            0.4f, 0.4f * 1.732f, 0.0f,
    };
    int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // GLSurfaceView 的渲染模式有两种，一种是连续不断的更新屏幕，另一种为 on-demand，只有在调用 requestRender() 在更新屏幕。
    // 缺省为 RENDERMODE_CONTINUOUSLY 持续刷新屏幕
    @Override
    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);
        //申请内存:allocateDirect分配的字节缓冲区用中文叫做直接缓冲区（DirectByteBuffer）
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertexArray.length * 4);
        //设置字节顺序:返回本地jvm运行的硬件的字节顺序.使用和硬件一致的字节顺序可能使buffer更加有效.
        vbb.order(ByteOrder.nativeOrder());

        FloatBuffer vertex = vbb.asFloatBuffer();
        vertex.put(vertexArray);
        vertex.position(0);

        gl.glLoadIdentity();

        gl.glTranslatef(0, 0, -4);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertex);

        index++;
        index %= 10;
        switch (index) {
            case 0:
            case 1:
            case 2:
                gl.glColor4f(1.0f, 0f, 0f, 1f);
                gl.glDrawArrays(GL10.GL_LINES, 0, 4);//p0-p1;p2-p3
                sleep();
                break;
            case 3:
            case 4:
            case 5:
                gl.glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
                gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, 4);//p0-p1-p2-p3
                sleep();
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
                gl.glDrawArrays(GL10.GL_LINE_LOOP, 0, 4);//p0-p1-p2-p3-p0
                sleep();
                break;
        }
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
