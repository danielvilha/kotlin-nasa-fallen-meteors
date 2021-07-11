package com.danielvilha.fallenmeteors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

/**
 * This is a single activity application that uses the Navigation library. Content is displayed
 * by Fragments.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Open the splash screen
        setTheme(R.style.Theme_FallenMeteors_NoActionBar)

        // My content view
        setContentView(R.layout.activity_main)

        // Showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitleTextColor(getColor(R.color.white))

        setSupportActionBar(toolbar)
    }
}