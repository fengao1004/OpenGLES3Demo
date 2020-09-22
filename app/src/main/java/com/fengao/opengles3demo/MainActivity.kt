package com.fengao.opengles3demo

import android.app.ActivityManager
import android.content.Context
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.fengao.opengles3demo.renderer.*

class MainActivity : AppCompatActivity() {
    lateinit var rootLayout: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootLayout = findViewById(R.id.root)
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val configurationInfo = activityManager.deviceConfigurationInfo
        val supportsEs3 = configurationInfo.reqGlEsVersion >= 0x30000
        val layoutParams: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val glSurfaceView = GLSurfaceView(this)
        if (supportsEs3) {
            glSurfaceView.setEGLContextClientVersion(3)
        }
        rootLayout.addView(glSurfaceView, layoutParams)
        glSurfaceView.setRenderer(ThreePointColorUniformRenderer(this))
    }
}
