package com.ykhe.openglesdemos.sides;

import android.os.Bundle;

import com.ykhe.openglesdemos.common.OpenGLESActivity;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * author: ykhe
 * date: 20-11-13
 * description: 绘制20面体
 * 一个正 20 面体，有 12 个顶点，20 个面，30 条边构成：
 */
public class DrawSides20 extends OpenGLESActivity {
    //定义20面体的12个顶点&20个面
    static final float X = .525731112119133606f;
    static final float Z = .850650808352039932f;
    static float vertices[] = new float[]{
            -X, 0.0f, Z, X, 0.0f, Z,
            -X, 0.0f, -Z, X, 0.0f, -Z,
            0.0f, Z, X, 0.0f, Z, -X,
            0.0f, -Z, X, 0.0f, -Z, -X,
            Z, X, 0.0f, -Z, X, 0.0f,
            Z, -X, 0.0f, -Z, -X, 0.0f
    };
    static short indices[] = new short[]{
            0, 4, 1, 0, 9, 4, 9, 5, 4, 4, 5, 8,
            4, 8, 1, 8, 10, 1, 8, 3, 10, 5, 3, 8,
            5, 2, 3, 2, 7, 3, 7, 10, 3, 7, 6, 10,
            7, 11, 6, 11, 0, 6, 0, 1, 6, 6, 1, 10,
            9, 0, 11, 9, 11, 2, 9, 2, 5, 7, 2, 11
    };

    // OpenGL ES 缺省使用同一种颜色来绘制图形，为了能够更好的显示3D效果，我们为每个顶点随机定义一些颜色如下：
    float[] colors = {
            0f, 0f, 0f, 1f,
            0f, 0f, 1f, 1f,
            0f, 1f, 0f, 1f,
            0f, 1f, 1f, 1f,
            1f, 0f, 0f, 1f,
            1f, 0f, 1f, 1f,
            1f, 1f, 0f, 1f,
            1f, 1f, 1f, 1f,
            1f, 0f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 0f, 1f, 1f,
            1f, 0f, 1f, 1f
    };

    //将顶点，面，颜色存放到 Buffer 中以提高性能：
    //顶点
    private FloatBuffer vertexBuffer;
    //颜色
    private FloatBuffer colorBuffer;
    //面
    private ShortBuffer indexBuffer;

    private int angle = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ByteBuffer vbb
                = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);


        ByteBuffer cbb
                = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);

        ByteBuffer ibb
                = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indexBuffer = ibb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    @Override
    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);
        gl.glColor4f(1f, 0f, 0f, 1f);

        gl.glLoadIdentity();

        gl.glTranslatef(0, 0, -4);
        /*
        该函数用来设置opengl中绘制实体的自转方式，即物体如何旋转
        参数说明：
        angle：旋转的角度，单位为度；
        x,y,z表示绕着那个轴旋转，如果取值都为0，则表示默认的绕x轴逆时针旋转
        x,y为0，z不为0时，表示绕z轴旋转；x,z为0，y不为0时，表示绕y轴旋转；y,z为0，x不为0，表示绕x轴旋转
        旋转的逆顺时针是通过x，y，z值得正负来确定的：取值为正时，表示逆时针旋转；取值为负时，表示顺时针旋转
         */
        gl.glRotatef(angle, 0, 1, 0);

        /*
           定义前面和背面多边形
           参数:
           mode  指明前面多边形的方向。可选的值有GL_CW和GL_CCW。默认值是GL_CCW
           描述：
           在一个全部由不透明封闭表面组成的场景中，背面多边形是永远看不见的。
           剔除这些不可见的多边形对于加速图形的渲染有很大的益处。
           开启和关闭剔除功能可以调用带GL_CULL_FACE参数的glEnable和glDisable函数。默认剔除功能是关闭的。
           如果一个虚构的对象的顶点是按照多边形内部顺时针的方向进行绘制的，
           那么可以称这个多边形基于窗口坐标的投影是顺时针的。反之，则为逆时针。

           glFrontFace就是用来指定多边形在窗口坐标中的方向是逆时针还是顺时针的。
           GL_CCW说明逆时针多边形为正面，而GL_CW说明顺时针多边形为正面。默认是逆时针多边形为正面

         */
        gl.glFrontFace(GL10.GL_CCW);

        gl.glEnable(GL10.GL_CULL_FACE);

        //glCullFace()参数包括GL_FRONT和GL_BACK。
        //意义：两个参数分别表示禁用多边形正面或者背面上的光照、阴影和颜色计算及操作，消除不必要的渲染计算。
        gl.glCullFace(GL10.GL_BACK);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,vertexBuffer);

        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        /*
         设定指向颜色数组的指针:
         size: 每种颜色组件的数量。 值必须为 3 或 4。
         type: 颜色数组中的每个颜色分量的数据类型。
         使用下列常量指定可接受的数据类型：GL_BYTE GL_UNSIGNED_BYTE，GL_SHORT GL_UNSIGNED_SHORT，GL_INT GL_UNSIGNED_INT，GL_FLOAT，或 GL_DOUBLE。
         stride：连续颜色之间的字节偏移量。 当偏移量为0时，表示数据是连续的。
         pointer：即颜色的Buffer
         */
        gl.glColorPointer(4,GL10.GL_FLOAT,0,colorBuffer);

        /*
          函数作用:使用count个元素定义一个几何序列，这些元素的索引值保存在indices数组中。
          mode:接受的值和在glBegin()中接受的值一样，可以是GL_POLYGON、GL_TRIANGLES、GL_TRIANGLE_STRIP、GL_LINE_STRIP等。
          count: 组合几何图形的元素的个数，一般是点的个数。
          type: indeices数组的数据类型，既然是索引，一般是整型的。
          indices: 索引数组
         */
        gl.glDrawElements(GL10.GL_TRIANGLES,indices.length,GL10.GL_UNSIGNED_SHORT,indexBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);

        angle++;
    }
}
