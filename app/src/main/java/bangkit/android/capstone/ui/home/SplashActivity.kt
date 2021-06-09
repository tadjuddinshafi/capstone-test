package bangkit.android.capstone.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import bangkit.android.capstone.MainActivity
import bangkit.android.capstone.R

class SplashActivity : AppCompatActivity() {
    private val splashtime: Long = 4000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashtime)
    }
}