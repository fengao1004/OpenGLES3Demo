package com.fengao.opengles3demo.renderer

import android.content.Context
import android.opengl.GLES30
import android.opengl.GLSurfaceView
import com.fengao.opengles3demo.util.ShaderUtil
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class ThreePointColorVBORenderer(var context: Context) : GLSurfaceView.Renderer {
    var pointProgram = -1
    var vertexBuffer: FloatBuffer
    val POSITION_SIZE = 3
    val LENGTH = 6
    val COLOR_SIZE = 3
    private var vboIds: IntArray = IntArray(2)
    private val POSITION_VERTEX = floatArrayOf(
        0.0f, 0.5f, 0.0f, 1.0f, 1.1f, 0.0f,
        -0.25f, -0.25f, 0.0f, 1.0f, 0.5f, 0.0f,
        0.25f, -0.25f, 0.0f, 0.0f, 1.1f, 0.0f
    )

    init {
        vertexBuffer = ByteBuffer.allocateDirect(POSITION_VERTEX.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
            .put(POSITION_VERTEX)
        vertexBuffer.position(0)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT)
        GLES30.glUseProgram(pointProgram)
        GLES30.glDrawArrays(GLES30.GL_POINTS, 0, 3);
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        pointProgram = ShaderUtil.loadProgramFromAssets(
            "vertex_point_1.glsl",
            "frag_point_1.glsl",
            context.resources
        )
        GLES30.glGenBuffers(1, vboIds, 0);
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vboIds[0])
        GLES30.glBufferData(
            GLES30.GL_ARRAY_BUFFER,
            POSITION_VERTEX.size * 4,
            vertexBuffer,
            GLES30.GL_STATIC_DRAW
        );
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glVertexAttribPointer(0, POSITION_SIZE, GLES30.GL_FLOAT, false, LENGTH * 4, 0);
        GLES30.glEnableVertexAttribArray(1);
        GLES30.glVertexAttribPointer(1, POSITION_SIZE, GLES30.GL_FLOAT, false, LENGTH * 4, POSITION_SIZE*4);
    }
}