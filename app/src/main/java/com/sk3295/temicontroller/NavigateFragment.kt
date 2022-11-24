package com.sk3295.temicontroller;

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.robotemi.sdk.Robot
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener
import com.robotemi.sdk.sequence.OnSequencePlayStatusChangedListener
import kotlinx.android.synthetic.main.navigate_fragment.*
import java.util.concurrent.Executors


private val executorService = Executors.newSingleThreadExecutor()

public class NavigateFragment : OnGoToLocationStatusChangedListener,
    OnSequencePlayStatusChangedListener, Fragment() {
    lateinit var robot: Robot
    @SuppressLint("WrongThread")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        robot = Robot.getInstance()
        robot.addOnSequencePlayStatusChangedListener(this)
        robot.addOnGoToLocationStatusChangedListener(this)
        // 현재 시퀀스 기능 사용 금지
        robot.getAllSequences(listOf("1학년 교실 안내"))

        return inflater.inflate(R.layout.navigate_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            robot.playSequence("1학년 교실 안내")
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
