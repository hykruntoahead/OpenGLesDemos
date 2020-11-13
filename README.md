# OpenGLesDemos
Android OpenGLEs学习
### 一.基本概念
##### 1.什么是OpenGLEs
OpenGL ES(OpenGL for Embedded Systems)实际上就是OpenGL(Open Graphics Library)在移动端的阉割版.<br/>
OpenGL是跨编程语言、跨平台的编程接口或者叫图形库。
##### 2.EGL是什么
EGL主要负责初始化OpenGL的运行环境和设备间的交互，简单说就是OpenGL负责绘图EGL负责和设备交互。
##### 3.需要了解的知识

###### 坐标系 (x,y,z)
###### 线性代数
矩阵相乘,单元矩阵、转置、投影、拉普拉斯变换、高斯变换
###### 相机
这里的“相机”和现实世界中的相机不是一个东西，但概念的相同的，都是捕获世界的景像呈现到二维平面上。这里的“相机”就是捕获这个三维世界的图像呈现到设备(屏幕)上的。
     
### 二.基本几何图形定义
OpenGL ES 支持绘制的基本几何图形分为三类：点，线段，三角形。也就是说 OpenGL ES 只能绘制这三种基本几何图形。
任何复杂的2D或是3D图形都是通过这三种几何图形构造而成的.<br/>
点，线段，三角形都是通过顶点来定义的，也就是顶点数组来定义。
对应平面上的一系列顶点，可以看出一个个孤立的点 (Point)，也可以两个两个连接成线段 (Line Segment) ，
也可以三个三个连成三角形 (Triangle)。这些对一组顶点的不同解释就定义了 Android OpenGL ES 可以绘制的基本几何图形，
下面定义了 OpenGL ES 定义的几种模式：
###### GL_POINTS 绘制独立的点
###### GL_LINE_STRIP 绘制一系列线段
###### GL_LINE_LOOP 类同上，但是首尾相连，构成一个封闭曲线
###### GL_LINES 顶点两两连接，为多条线段构成
###### GL_TRIANGLES  每隔三个顶点构成一个三角形，为多个三角形组成
###### GL_TRIANGLE_STRIP 每相邻三个顶点组成一个三角形，为一系列相接三角形构成
###### GL_TRIANGLE_FAN 以一个点为三角形公共顶点，组成一系列相邻的三角形
##### OpenGL ES 提供了两类方法来绘制一个空间几何图形：
```
    public abstract void glDrawArrays(int mode, int first, int count)
        使用 VetexBuffer 来绘制，顶点的顺序由 vertexBuffer 中的顺序指定。
    public abstract void glDrawElements(int mode, int count, int type, Buffer indices)
        可以重新定义顶点的顺序，顶点的顺序由 indices Buffer 指定。
```

其中 mode 为上述解释顶点的模式。
###### 示例:
定义三个顶点坐标，并把它们存放在 FloatBuffer 中:
```
float[] vertexArray = new float[]{
 -0.8f , -0.4f * 1.732f , 0.0f ,
 0.8f , -0.4f * 1.732f , 0.0f ,
 0.0f , 0.4f * 1.732f , 0.0f ,
 };
ByteBuffer vbb
 = ByteBuffer.allocateDirect(vertexArray.length*4);
vbb.order(ByteOrder.nativeOrder());
FloatBuffer vertex = vbb.asFloatBuffer();
vertex.put(vertexArray);
vertex.position(0);
```
了顶点的定义，下面就可以通过打开 OpenGL ES 管道(Pipeline)的相应开关将顶点参数传给 OpenGL 库,
打开顶点开关和关闭顶点开关的方法如下:
```
gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
......
gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
```
在打开顶点开关后，将顶点坐标传给 OpenGL 管道的方法为：glVertexPointer:
```
public void glVertexPointer(int size,int type,int stride,Buffer pointer)
```
size：每个顶点坐标维数，可以为2，3，4。
type：顶点的数据类型，可以为 GL_BYTE, GL_SHORT, GL_FIXED,或 GL_FLOAT，缺省为浮点类型 GL_FLOAT。
stride：每个相邻顶点之间在数组中的间隔（字节数），缺省为 0，表示顶点存储之间无间隔。
pointer：存储顶点的数组。

顶点除了可以为其定义坐标外，还可以指定颜色，材质，法线（用于光照处理）等:
glEnableClientState 和 glDisableClientState 可以控制的 pipeline 开关可以有：
GL_COLOR_ARRAY (颜色），GL_NORMAL_ARRAY (法线)，GL_TEXTURE_COORD_ARRAY (材质)，
GL_VERTEX_ARRAY(顶点)， GL_POINT_SIZE_ARRAY_OES等。

对应的传入颜色，顶点，材质，法线的方法如下：
```
glColorPointer(int size,int type,int stride,Buffer pointer)
glVertexPointer(int size, int type, int stride, Buffer pointer)
glTexCoordPointer(int size, int type, int stride, Buffer pointer)
glNormalPointer(int type, int stride, Buffer pointer)
```

### 三.绘制点Point
参看代码:[DrawPoint](https://github.com/hykruntoahead/OpenGLesDemos/blob/master/app/src/main/java/com/ykhe/openglesdemos/point/DrawPoint.java)

### 四.绘制线段
参看代码:[DrawLine](https://github.com/hykruntoahead/OpenGLesDemos/blob/master/app/src/main/java/com/ykhe/openglesdemos/line/DrawLine.java)

### 五.绘制三角形
参看代码:[DrawTriangle](https://github.com/hykruntoahead/OpenGLesDemos/blob/master/app/src/main/java/com/ykhe/openglesdemos/triangle/Triangle.java)
