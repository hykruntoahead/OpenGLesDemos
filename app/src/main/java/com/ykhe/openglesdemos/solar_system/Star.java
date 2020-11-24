package com.ykhe.openglesdemos.solar_system;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * author: ykhe
 * date: 20-11-24
 * email: ykhe@grandstream.cn
 * description: 使用一个2D五角星做为天体而没有使用球体（绘制球体在后面有介绍），构造一个 Star 类
 */
public class Star {

    // Star 定义了五角星的五个顶点
    protected float vertices[];
    // Our vertex buffer.
    protected FloatBuffer vertexBuffer;
    public Star() {
        float a=(float)(1.0f/(2.0f-2f*Math.cos(72f*Math.PI/180.f)));
        float bx=(float)(a*Math.cos(18*Math.PI/180.0f));
        float by=(float)(a*Math.sin(18*Math.PI/180f));
        float cy=(float)(-a * Math.cos(18*Math.PI/180f));
        vertices=new float[]{
                0,a, 0.5f,cy, -bx,by, bx,by, -0.5f,cy
        };

        ByteBuffer vbb
                = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }
    /**
     * This function draws our star on screen.
     * @param gl
     */
    public void draw(GL10 gl) {
        // 逆时针缠绕
        gl.glFrontFace(GL10.GL_CCW);

        gl.glEnable(GL10.GL_CULL_FACE);
        // 去除背面绘制
        gl.glCullFace(GL10.GL_BACK);
        // 启用顶点缓冲区以进行写入和渲染期间使用。
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // 指定渲染时使用的“顶点”坐标数组的位置和数据格式。
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0,
                vertexBuffer);
        //使用 glDrawArrays 来绘制五角星
        gl.glDrawArrays(GL10.GL_LINE_LOOP, 0,5);
        // 禁用顶点缓冲区
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }
}
