package com.cncom.app.opengl.mesh;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by bestjoy on 16/12/27.
 */

public class Mesh {
    // 顶点缓冲
    private FloatBuffer verticesBuffer = null;

    // 渲染顺序缓冲
    private ShortBuffer indicesBuffer = null;

    // 顺序缓冲的数量
    private int numOfIndices = -1;

    // 纯色
    private float[] rgba = new float[]{1.0f, 1.0f, 1.0f, 1.0f};

    // 渐变色
    private FloatBuffer colorBuffer = null;

    // 平移参数
    public float x = 0;
    public float y = 0;
    public float z = 0;

    // 旋转参数
    public float rx = 0;
    public float ry = 0;
    public float rz = 0;

    // Our UV texture buffer.
    private FloatBuffer mTextureBuffer; // New variable.

    // Our texture id.
    private int mTextureId = -1; // New variable.

    // The bitmap we want to load as a texture.
    private Bitmap mBitmap; // New variable.

    // Indicates if we need to load the texture.
    private boolean mShouldLoadTexture = false; // New variable.


    public void draw(GL10 gl) {
        // 逆时针
        gl.glFrontFace(GL10.GL_CCW);
        // 开启裁剪
        gl.glEnable(GL10.GL_CULL_FACE);
        // 背面裁剪
        gl.glCullFace(GL10.GL_BACK);
        // 、开启渲染中使用的顶点缓冲
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // 指定顶点缓冲的位置和格式

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verticesBuffer);
        // 设置纯色
        gl.glColor4f(rgba[0], rgba[1], rgba[2], rgba[3]);
        //渐变色
        if ( colorBuffer != null ) {
            // 开启颜色缓冲
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            // 指定颜色缓冲的位置
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
        }

        // New part...
        if (mShouldLoadTexture) {
            loadGLTexture(gl);
            mShouldLoadTexture = false;
        }
        if (mTextureId != -1 && mTextureBuffer != null) {
            gl.glEnable(GL10.GL_TEXTURE_2D);
            // Enable the texture state
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

            // Point to our buffers
            gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
            gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);
        }
        // ... end new part.

        gl.glTranslatef(x, y, z);
        gl.glRotatef(rx, 1, 0, 0);
        gl.glRotatef(ry, 0, 1, 0);
        gl.glRotatef(rz, 0, 0, 1);

        gl.glDrawElements(GL10.GL_TRIANGLES, numOfIndices,
                GL10.GL_UNSIGNED_SHORT, indicesBuffer);
        // 禁用顶点缓冲
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        // New part...
        if (mTextureId != -1 && mTextureBuffer != null) {
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        }
        // ... end new part.

        // 禁用面裁剪
        gl.glDisable(GL10.GL_CULL_FACE);
    }

    protected void setVertices(float[] vertices) {
        // float为4字节，所以乘以4
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        verticesBuffer = vbb.asFloatBuffer();
        verticesBuffer.put(vertices);
        verticesBuffer.position(0);
    }

    protected void setIndices(short[] indices) {
        // short为2字节，所以长度乘以2
        ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
        ibb.order(ByteOrder.nativeOrder());
        indicesBuffer = ibb.asShortBuffer();
        indicesBuffer.put(indices);
        indicesBuffer.position(0);
        numOfIndices = indices.length;
    }

    protected void setColor(float red, float green, float blue, float alpha) {
        // 设置纯色
        rgba[0] = red;
        rgba[1] = green;
        rgba[2] = blue;
        rgba[3] = alpha;
    }

    protected void setColors(float[] colors) {

        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);
    }

    /**
     * Set the texture coordinates.
     *
     * @param textureCoords
     */
    protected void setTextureCoordinates(float[] textureCoords) { // New
        // function.
        // float is 4 bytes, therefore we multiply the number if
        // vertices with 4.
        ByteBuffer byteBuf = ByteBuffer
                .allocateDirect(textureCoords.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        mTextureBuffer = byteBuf.asFloatBuffer();
        mTextureBuffer.put(textureCoords);
        mTextureBuffer.position(0);
    }

    /**
     * Set the bitmap to load into a texture.
     *
     * @param bitmap
     */
    public void loadBitmap(Bitmap bitmap) { // New function.
        this.mBitmap = bitmap;
        mShouldLoadTexture = true;
    }

    /**
     * Loads the texture.
     *
     * @param gl
     */
    private void loadGLTexture(GL10 gl) { // New function
        // Generate one texture pointer...
        int[] textures = new int[1];
        gl.glGenTextures(1, textures, 0);
        mTextureId = textures[0];

        // ...and bind it to our array
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);

        // Create Nearest Filtered Texture
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);

        // Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
                GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
                GL10.GL_REPEAT);

        // Use the Android GLUtils to specify a two-dimensional texture image
        // from our bitmap
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap, 0);
    }
}
