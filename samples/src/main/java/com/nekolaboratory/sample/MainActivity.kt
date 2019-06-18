package com.nekolaboratory.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nekolaboratory.Stella.Stella
import com.nekolaboratory.Stella.StellaCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val detectCallback = StellaCallback {
            check_text.text = "detect"
        }
        var stella = Stella()
        stella.initialize(3000, detectCallback)
        button.setOnClickListener {
            stella.start()
        }
    }
}
