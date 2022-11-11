package com.sk3295.temicontroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.robotemi.sdk.Robot
import com.robotemi.sdk.*
import com.robotemi.sdk.Robot.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

private val executorService = Executors.newSingleThreadExecutor()

private lateinit var robot: Robot

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            LocationText.setText("1-1로 이동 중 입니다.");
            robot.goTo("1학년 1반")
        }
    }
}
