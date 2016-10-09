package com.ru.waka.planckchallenge

import android.app.Activity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Chronometer


public class MainActivity : Activity() {
    private var isStarted = false
    private var startTime: Long? = null
    private var stopTime: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startAndStopBtn = findViewById(R.id.start_btn) as Button
        val reset = findViewById(R.id.reset_btn)
        val chronometer = findViewById(R.id.chronometer) as Chronometer
        startAndStopBtn.setOnClickListener({ v ->
            if(isStarted) {
                stopTime = SystemClock.elapsedRealtime()
                chronometer.stop()
                isStarted = false
                (v as Button).setText(R.string.action_start_timer)
            } else {
                if (stopTime != null) {
                    val time = SystemClock.elapsedRealtime() - (stopTime as Long) + (startTime as Long)
                    startTime = time
                } else {
                    startTime = SystemClock.elapsedRealtime()
                }
                chronometer.setBase(startTime as Long)
                chronometer.start()
                isStarted = true
                (v as Button).setText(R.string.action_stop_timer)
            }
        })

        reset.setOnClickListener({ v ->
            isStarted = false
            startTime = null
            stopTime = null
            chronometer.stop()
            chronometer.setBase(SystemClock.elapsedRealtime());
        })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
