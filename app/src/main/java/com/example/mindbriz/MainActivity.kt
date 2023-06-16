package com.example.mindbriz


import android.R
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
import com.github.mzule.fantasyslide.SideBar
import com.github.mzule.fantasyslide.SimpleFantasyListener
import javax.xml.transform.Transformer


class MainActivity : AppCompatActivity() {
    private var drawerLayout: DrawerLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val indicator = DrawerArrowDrawable(this)
        indicator.color = Color.WHITE
        supportActionBar!!.setHomeAsUpIndicator(indicator)
        setTransformer()
        // setListener();
        drawerLayout = findViewById<View>(R.id.drawerLayout) as DrawerLayout
        drawerLayout!!.setScrimColor(Color.TRANSPARENT)
        drawerLayout!!.addDrawerListener(object : SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                if ((drawerView as ViewGroup).getChildAt(1).id == R.id.leftSideBar) {
                    indicator.progress = slideOffset
                }
            }
        })
    }

    private fun setListener() {
        val tipView = findViewById<View>(R.id.tipView) as TextView
        val leftSideBar: SideBar = findViewById<View>(R.id.leftSideBar) as SideBar
        leftSideBar.setFantasyListener(object : SimpleFantasyListener() {
            fun onHover(@Nullable view: View?, index: Int): Boolean {
                tipView.visibility = View.VISIBLE
                if (view is TextView) {
                    tipView.text =
                        String.format("%s at %d", view.text.toString(), index)
                } else if (view != null && view.id == R.id.userInfo) {
                    tipView.text = String.format("个人中心 at %d", index)
                } else {
                    tipView.text = null
                }
                return false
            }

            fun onSelect(view: View?, index: Int): Boolean {
                tipView.visibility = View.INVISIBLE
                Toast.makeText(
                    this@MainActivity,
                    String.format("%d selected", index),
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }

            fun onCancel() {
                tipView.visibility = View.INVISIBLE
            }
        })
    }

    private fun setTransformer() {
        val spacing = resources.getDimensionPixelSize(R.dimen.spacing).toFloat()
        val rightSideBar: SideBar = findViewById<View>(R.id.rightSideBar) as SideBar
        rightSideBar.setTransformer(object : Transformer() {
            private var lastHoverView: View? = null
            fun apply(
                sideBar: ViewGroup?,
                itemView: View,
                touchY: Float,
                slideOffset: Float,
                isLeft: Boolean
            ) {
                val hovered = itemView.isPressed
                if (hovered && lastHoverView !== itemView) {
                    animateIn(itemView)
                    animateOut(lastHoverView)
                    lastHoverView = itemView
                }
            }

            private fun animateOut(view: View?) {
                if (view == null) {
                    return
                }
                val translationX = ObjectAnimator.ofFloat(view, "translationX", -spacing, 0f)
                translationX.duration = 200
                translationX.start()
            }

            private fun animateIn(view: View) {
                val translationX = ObjectAnimator.ofFloat(view, "translationX", 0f, -spacing)
                translationX.duration = 200
                translationX.start()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
                drawerLayout!!.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout!!.openDrawer(GravityCompat.START)
            }
        }
        return true
    }

    fun onClick(view: View) {
        if (view is TextView) {
            val title = view.text.toString()
            if (title.startsWith("星期")) {
                Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
            } else {
                startActivity(UniversalActivity.newIntent(this, title))
            }
        } else if (view.id == R.id.userInfo) {
            startActivity(UniversalActivity.newIntent(this, "个人中心"))
        }
    }
}