package bangkit.android.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import bangkit.android.capstone.ml.SkinModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnyes: Button = findViewById(R.id.btn_yes)
        btnyes.setOnClickListener(this)
        val btnno: Button = findViewById(R.id.btn_no)
        btnno.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_yes -> {
                val moveIntent = Intent(this@MainActivity, YesActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_no -> {
                val moveIntent = Intent(this@MainActivity, NoActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}