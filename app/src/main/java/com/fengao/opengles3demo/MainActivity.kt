package com.fengao.opengles3demo

import android.opengl.GLSurfaceView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {
    lateinit var rootLayout: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootLayout = findViewById(R.id.root)
        var layoutParams: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val glSurfaceView = GLSurfaceView(this)



        rootLayout.addView(glSurfaceView, layoutParams)
    }
}
