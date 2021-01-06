package com.example.imageslide

import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(), View.OnTouchListener {

    val imageList = arrayListOf<String>(
            "https://image.freepik.com/free-photo/cool-geometric-triangular-figure-neon-laser-light-great-backgrounds-wallpapers_181624-9331.jpg",
            "https://image.freepik.com/free-photo/blue-purple-neon-light-spectrum-futuristic-hi-tech-abstract-background_24070-1107.jpg",
            "https://image.freepik.com/free-vector/abstract-neon-lights-background-design_52683-44643.jpg",
            "https://image.freepik.com/free-vector/colorful-abstract-background_23-2148807053.jpg",
            "https://image.freepik.com/free-vector/abstract-colorful-shapes-background_23-2148769634.jpg",
            "https://image.freepik.com/free-vector/abstract-colorful-shapes-background_23-2148769632.jpg",
            "https://image.freepik.com/free-vector/abstract-colorful-shapes-background_23-2148769630.jpg",
            "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)

        val viewFlipper: ViewFlipper = findViewById(R.id.view_fliper)
        viewFlipper.setOnTouchListener(this)
        createImageSlider(viewFlipper)
    }

    // create image slider with viewflipper
    private fun createImageSlider(viewFlipper: ViewFlipper) {
        for (i in 0 until imageList.size) {
            addImageToViewFlipper(viewFlipper, i);
        }
        viewFlipper.inAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        viewFlipper.outAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);

        viewFlipper.isAutoStart = true;
        viewFlipper.flipInterval = 8000;
    }

    //add image to viewflipper
    private fun addImageToViewFlipper(viewFlipper: ViewFlipper, position: Int) {
        val imageView = ImageView(this)
        imageView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        imageView.adjustViewBounds = true
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(this).load(imageList[position]).into(imageView)
        viewFlipper.addView(imageView)
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        when(p0?.id) {
            R.id.view_fliper -> {
                Toast.makeText(this,"Clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }
}