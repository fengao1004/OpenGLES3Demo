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

class ThreePointRenderer(var context: Context) : GLSurfaceView.Renderer {
    var pointProgram = -1
    var vertexBuffer: FloatBuffer
    var avPosition = -1
    private val POSITION_VERTEX = floatArrayOf(
        0.0f, 0.5f, 0.0f,
        -0.25f, -0.25f, 0.0f,
        0.25f, -0.25f, 0.0f
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
        GLES30.glEnableVertexAttribArray(avPosition)
        GLES30.glVertexAttribPointer(avPosition, 3, GLES30.GL_FLOAT, false, 0, vertexBuffer)
        GLES30.glDrawArrays(GLES30.GL_POINTS, 0, 3)
        GLES30.glDisableVertexAttribArray(avPosition)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        pointProgram = ShaderUtil.loadProgramFromAssets(
            "vertex_point.glsl",
            "frag_point.glsl",
            context.resources
        )
        avPosition = GLES30.glGetAttribLocation(pointProgram, "av_Position")
    }
}