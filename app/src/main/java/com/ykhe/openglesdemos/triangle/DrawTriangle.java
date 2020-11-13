package com.ykhe.openglesdemos.triangle;

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
public class DrawTriangle extends OpenGLESActivity {
    //  6 个顶点 使用三种不同模式来绘制三角形
    float vertexArray[] = {
            -0.8f, -0.4f * 1.732f, 0.0f,
            0.0f, -0.4f * 1.732f, 0.0f,
            -0.4f, 0.4f * 1.732f, 0.0f,
            0.0f, -0.0f * 1.732f, 0.0f,
            0.8f, -0.0f * 1.732f, 0.0f,
            0.4f, 0.4f * 1.732f, 0.0f,
    };
    private int index = -1;

    @Override
    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertexArray.length*4);
        vbb.order(ByteOrder.nativeOrder());

        FloatBuffer vertex = vbb.asFloatBuffer();
        vertex.put(vertexArray);
        vertex.position(0);

        gl.glLoadIdentity();
        gl.glTranslatef(0,0,-4);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vertex);

        index++;
        index%=3;
        switch (index){
            case 0:
                gl.glColor4f(1f,0f,0f,1f);
                // 每隔三个顶点构成一个三角形，为多个三角形组成
                gl.glDrawArrays(GL10.GL_TRIANGLES,0,6);
                break;
            case 1:
                gl.glColor4f(0f,1f,0f,1f);
                // 每相邻三个顶点组成一个三角形，为一系列相接三角形构成
                gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,6);
                break;
            case 2:
                gl.glColor4f(0f,0f,1f,1f);
                // 以一个点为三角形公共顶点，组成一系列相邻的三角形
                gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,0,6);
                break;
        }
        sleep();
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }


    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
