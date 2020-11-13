package com.ykhe.openglesdemos.point;

import android.os.Bundle;

import com.ykhe.openglesdemos.common.OpenGLESActivity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * author: ykhe
 * date: 20-11-12
 * description:
 */
public class DrawPoint extends OpenGLESActivity {
    float[] vertexArray = new float[]{
            -0.8f , -0.4f * 1.732f , 0.0f ,
            0.8f , -0.4f * 1.732f , 0.0f ,
            0.0f , 0.4f * 1.732f , 0.0f ,
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        super.DrawScene(gl);
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertexArray.length*4);
        vbb.order(ByteOrder.nativeOrder());
        FloatBuffer vertex = vbb.asFloatBuffer();
        vertex.put(vertexArray);
        vertex.position(0);

        gl.glColor4f(1.0f,0.0f,0.0f,1.0f);
        gl.glPointSize(8f);
        /*
         * OpenGL为我们提供了一个非常简单的恢复初始坐标系的手段，那就是调用glLoadIdentity()命令。
         * 该命令是一个无参的无值函数,其功能是用一个4×4的单位矩阵来替换当前矩阵，实际上就是对当前矩阵进行初始化。
         * 也就是说，无论以前进行了多少次矩阵变换，在该命令执行后，当前矩阵均恢复成一个单位矩阵，
         * 即相当于没有进行任何矩阵变换状态。
         */
        gl.glLoadIdentity();
        //将你绘点坐标的原点在当前原点的基础上平移一个(x,y,z)向量
        gl.glTranslatef(0,0,-4);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vertex);
        gl.glDrawArrays(GL10.GL_POINTS,0,3);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}
