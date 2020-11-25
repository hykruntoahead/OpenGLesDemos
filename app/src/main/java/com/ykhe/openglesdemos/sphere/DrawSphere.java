package com.ykhe.openglesdemos.sphere;

import com.ykhe.openglesdemos.common.OpenGLESActivity;

import javax.microedition.khronos.opengles.GL10;

/**
 * author: ykhe
 * date: 20-11-25
 * email: ykhe@grandstream.cn
 * description:
 */
public class DrawSphere extends OpenGLESActivity {

    private Sphere sphere = new Sphere();

    @Override
    public void DrawScene(GL10 gl) {
        super.DrawScene(gl);
        sphere.draw(gl);
    }
}
