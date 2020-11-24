package com.ykhe.openglesdemos.solar_system;

import android.opengl.GLU;
import android.os.Bundle;

import com.ykhe.openglesdemos.common.IOpenGLDemo;
import com.ykhe.openglesdemos.common.OpenGLESActivity;

import javax.microedition.khronos.opengles.GL10;

/**
 * author: ykhe
 * date: 20-11-13
 * description: 绘制迷你太阳系
 * 迷你太阳系，有一个红色的太阳，一个蓝色的地图和一个白色的月亮构成：
 *     太阳居中，逆时针自转。
 *     地球绕太阳顺时针公转，本身不自转。
 *     月亮绕地球顺时针公转，自身逆时针自转。
 */
public class DrawSolarSystem extends OpenGLESActivity
        implements IOpenGLDemo {
    private Star sun = new Star();
    private Star earth = new Star();
    private Star moon = new Star();
    private int angle = 0;
    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);
        gl.glLoadIdentity();
        //使用 GLU 的 gluLookAt 来定义 modelview Matrix,
        // 把相机放在正对太阳中心(0,0,0)，距离 15 (0,0,15)
        GLU.gluLookAt(gl, 0.0f, 0.0f, 15.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
        // Star A
        // 将当前 Matrix 入栈
        gl.glPushMatrix();
        // 以红色绘制太阳，并逆向转动，将当前 matrix 入栈的目的是在能够在绘制地球时恢复当前栈
        // 设置opengl中绘制实体的自转方式，即物体如何旋转 x,y为0，z不为0时，表示绕z轴旋转
        // 取值为正时，表示逆时针旋转；取值为负时，表示顺时针旋转
        gl.glRotatef(angle, 0, 0, 1);
        gl.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
        // Draw Star A.
        sun.draw(gl);
        // 将 Matrix 出栈
        gl.glPopMatrix();

        // Star B
        // 将当前 Matrix 入栈
        gl.glPushMatrix();
        // 绘制地球，使用局部坐标系来想象地球和太阳之间的相对运动，
        // 地球离开一距离绕太阳公转，相当于先旋转地球的局部坐标系，
        // 然后再平移地球的局部坐标系
        gl.glRotatef(-angle, 0, 0, 1);
        // Move Star B.
        gl.glTranslatef(3, 0, 0);
        // Scale it to 50% of Star A
        gl.glScalef(.5f, .5f, .5f);
        gl.glColor4f(0.0f, 0.0f, 1.0f, 1.0f);
        // Draw Star B.
        earth.draw(gl);


        // Star C
        // 绘制月亮 类似地球B绕太阳A
        // Save the current matrix
        gl.glPushMatrix();
        // Make the rotation around B
        gl.glRotatef(-angle, 0, 0, 1);
        gl.glTranslatef(2, 0, 0);
        // Scale it to 50% of Star B
        gl.glScalef(.5f, .5f, .5f);
        // Rotate around it's own center.
        gl.glRotatef(angle* 10, 0, 0, 1);
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        // Draw Star C.
        moon.draw(gl);

        // 恢复到C之前的矩阵。
        gl.glPopMatrix();
        // 恢复到B之前的矩阵。
        gl.glPopMatrix();
        // Increse the angle.
        angle++;
    }
}