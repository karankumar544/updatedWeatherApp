package com.mitit.weathe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class RetryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retry)
        var retry: TextView = findViewById(R.id.retry);
        retry.setOnClickListener{
            val nextInt:Intent = Intent(this,MainActivity::class.java);
            startActivity(nextInt);
        }


    }
}