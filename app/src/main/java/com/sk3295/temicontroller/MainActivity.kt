package com.sk3295.temicontroller

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.robotemi.sdk.Robot
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener
import com.robotemi.sdk.sequence.OnSequencePlayStatusChangedListener
import com.robotemi.sdk.sequence.SequenceModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors


private val executorService = Executors.newSingleThreadExecutor()

class MainActivity : AppCompatActivity(), OnGoToLocationStatusChangedListener,
    OnSequencePlayStatusChangedListener {
    lateinit var robot: Robot
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        robot = Robot.getInstance()
        robot.addOnGoToLocationStatusChangedListener(this)

        button1.setOnClickListener {
            LocationText.setText("1-1로 이동 중 입니다.");
            robot.goTo("1학년 1반")
        }

        button2.setOnClickListener {
            LocationText.setText("1-2로 이동 중 입니다.");
            robot.goTo("1학년 2반")
        }

        button3.setOnClickListener {
            LocationText.setText("1-3로 이동 중 입니다.");
            robot.goTo("1학년 3반")
        }

        button4.setOnClickListener {
            LocationText.setText("1-4로 이동 중 입니다.");
            robot.goTo("1학년 4반")
        }
    }

    override fun onGoToLocationStatusChanged(
        location: String,
        status: String,
        descriptionId: Int,
        description: String
    ) {
        Log.d("location-info", "location: $location, status: $status")

        if (status == "complete") {
            LocationText.setText("도착 했습니다.");
            Handler().postDelayed(Runnable {
                if (location == "1학년 1반") {
                    LocationText.setText("현재 위치 : 1학년 1반");
                } else if (location == "1학년 2반") {
                    LocationText.setText("현재 위치 : 1학년 2반");
                } else if (location == "1학년 3반") {
                    LocationText.setText("현재 위치 : 1학년 3반");
                } else if (location == "1학년 4반") {
                    LocationText.setText("현재 위치 : 1학년 4반");
                }
            }, 5000)
        }
    }

    override fun onSequencePlayStatusChanged(
        status: Int
    ) {
        var SequenceInfo = ""

        if (status == 0) {
            SequenceInfo = "Finish playing"
        } else if (status == 1) {
            SequenceInfo = "Source preparing"
        } else if (status == 2) {
            SequenceInfo = "Playing"
        } else if (status == -1) {
            SequenceInfo = "Errors occurred while playing"
        }

        Log.d("sequence-info", "status: $SequenceInfo")
    }
}