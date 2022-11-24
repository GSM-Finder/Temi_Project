package com.sk3295.temicontroller

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tabs
import java.util.concurrent.Executors


private val executorService = Executors.newSingleThreadExecutor()

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NavigateFragment(), "길 찾기")
        adapter.addFragment(FloorIntroduceFragment(), "층 소개")
        adapter.addFragment(SchoolIntroduceFragment(), "학교 소개")
        adapter.addFragment(OptionFragment(), "설정")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}
