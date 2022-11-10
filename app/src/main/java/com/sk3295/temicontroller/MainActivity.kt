package com.sk3295.temicontroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.robotemi.sdk.Robot
import com.robotemi.sdk.navigation.model.SpeedLevel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

private lateinit var robot: Robot

private val executorService = Executors.newSingleThreadExecutor()


class Robot {
    private fun Robot.goTo(
        location: String,
        backwards: Boolean,
        noBypass: Boolean,
        speedLevel: SpeedLevel
    ) {
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        robot.goTo("1학년 1반")

        button.setOnClickListener {
            LocationText.setText("1-1로 이동 중 입니다.");
        }
    }
}
