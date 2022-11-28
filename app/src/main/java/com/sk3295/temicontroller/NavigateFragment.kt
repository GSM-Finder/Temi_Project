package com.sk3295.temicontroller

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
import kotlinx.android.synthetic.main.navigate_fragment.view.*
import java.util.concurrent.Executors


private val executorService = Executors.newSingleThreadExecutor()

public class NavigateFragment : OnGoToLocationStatusChangedListener,
    OnSequencePlayStatusChangedListener, Fragment() {
    lateinit var robot: Robot
    val ids = Robot.getInstance().getAllSequences().map { it.id }

    @SuppressLint("WrongThread", "CheckResult")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        robot = Robot.getInstance()
        robot.addOnSequencePlayStatusChangedListener(this)
        robot.addOnGoToLocationStatusChangedListener(this)
        // 현재 시퀀스 기능 사용 금지
        robot.getAllSequences(ids)
        Log.d("sequence-info", "SequenceId: $ids")
        val view = inflater.inflate(R.layout.navigate_fragment, container, false)

        view.button1.setOnClickListener{
            LocationText.text = "1-1로 이동 중 입니다."
            robot.goTo("1학년 1반")
        }

        view.button2.setOnClickListener {
            LocationText.text = "1-2로 이동 중 입니다."
            robot.goTo("1학년 2반")
        }

        view.button3.setOnClickListener {
            LocationText.text = "1-3로 이동 중 입니다."
            robot.goTo("1학년 3반")
        }

        view.button4.setOnClickListener {
            LocationText.text = "1-4로 이동 중 입니다."
            robot.goTo("1학년 4반")
            robot.playSequence("1학년 교실 안내")
        }

        return view
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
        var sequenceInfo = ""

        if (status == 0) {
            sequenceInfo = "Finish playing"
        } else if (status == 1) {
            sequenceInfo = "Source preparing"
        } else if (status == 2) {
            sequenceInfo = "Playing"
        } else if (status == -1) {
            sequenceInfo = "Errors occurred while playing"
        }

        Log.d("sequence-info", "status: $sequenceInfo")
    }
}
