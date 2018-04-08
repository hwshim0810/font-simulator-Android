package xyz.laziness.fontsimulator.main

import android.support.v7.app.AppCompatActivity
import android.widget.Toast


const val BACK_EVENT_GAP_TIME = 2000

open class MyActivity : AppCompatActivity() {

    var onPressTime : Long = 0L

    override fun onBackPressed() {
        val now = System.currentTimeMillis()

        if (now > onPressTime + BACK_EVENT_GAP_TIME) {
            onPressTime = now
            Toast.makeText(applicationContext,
                    getString(R.string.app_finish_msg), Toast.LENGTH_SHORT).show()
        } else {
            super.onBackPressed()
        }
    }
}