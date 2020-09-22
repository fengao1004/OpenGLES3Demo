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

class ThreePointColorUniformRenderer(var context: Context) : GLSurfaceView.Renderer {
    var pointProgram = -1
    var vertexBuffer: FloatBuffer
    var fragPointColorIndex = -1
    val POSITION_SIZE = 3
    val COLOR_SIZE = 3
    val VERTEX_ATTRIBUTES_SIZE = 4 * (POSITION_SIZE + COLOR_SIZE)

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
        GLES30.glEnableVertexAttribArray(0)
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 0, vertexBuffer)
        GLES30.glDrawArrays(GLES30.GL_POINTS, 0, 3)
        GLES30.glDisableVertexAttribArray(0)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES30.glViewport(0, 0, width, height)
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        pointProgram = ShaderUtil.loadProgramFromAssets(
            "vertex_point_2.glsl",
            "frag_point_2.glsl",
            context.resources
        )
        GLES30.glUseProgram(pointProgram)
        fragPointColorIndex = GLES30.glGetUniformLocation(pointProgram, "frag_point_Color")
        GLES30.glUniform4fv(fragPointColorIndex, 1, floatArrayOf(1.0f, 0.5f, 0.1f, 1.0f), 0)
        GLES30.glVertexAttrib1f(1, 20.0f)
    }
}