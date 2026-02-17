package com.example.robot

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Top level: Data that's necessary MainActivity
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var redRobotImg: ImageView
    private lateinit var whiteRobotImg: ImageView
    private lateinit var yellowRobotImg: ImageView
    private lateinit var messageBox: TextView
    private lateinit var clockwiseButton: ImageView
    private lateinit var counterClockwiseButton: ImageView
//    turnCount is now in RobotViewModel, so that we can retain memory after device rotation
    //    private var turnCount /*: Int*/ = 0
    private lateinit var robotImages: MutableList<ImageView>

//    private val robotViewModel = RobotViewModel() // This runs but is bad, returns new viewModel
    private val robotViewModel : RobotViewModel by viewModels() // This is good
    private val robots = listOf(
        Robot(
            R.string.red_message_text, false,
            R.drawable.robot_red_large, R.drawable.robot_red_small
        ),
        Robot(
            R.string.white_message_text, false,
            R.drawable.robot_white_large, R.drawable.robot_white_small
        ),
        Robot(
            R.string.yellow_message_text, false,
            R.drawable.robot_yellow_large, R.drawable.robot_yellow_small
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // All data, use package:mine
        Log.d(TAG, "Entered onCreate(savedInstanceState : Bundle?)")
        Log.d(TAG, "Got a viewModel : $robotViewModel")
        redRobotImg = findViewById(R.id.red_robot)
        whiteRobotImg = findViewById(R.id.white_robot)
        yellowRobotImg = findViewById(R.id.yellow_robot)
        messageBox = findViewById(R.id.message_box)
//      clockwiseButton = findViewById(R.id.clockwise_button)
//      counterClockwiseButton = findViewById(R.id.counter_clockwise_button)
        robotImages = mutableListOf(redRobotImg, whiteRobotImg, yellowRobotImg)

//         This was done in class, had to comment out for the homework
        redRobotImg.setOnClickListener {
            // Toast.makeText(this, "Red Robot Clicked", Toast.LENGTH_SHORT).show()
            toggleImage()
        }

        whiteRobotImg.setOnClickListener {
            // Toast.makeText(this, "White Robot Clicked", Toast.LENGTH_SHORT).show()
            toggleImage()
        }

        yellowRobotImg.setOnClickListener {
            // Toast.makeText(this, "Yellow Robot Clicked", Toast.LENGTH_SHORT).show()
            toggleImage()
        }

//        clockwiseButton.setOnClickListener {
//            toggleImageButtonClockwise()
//        }
//
//        counterClockwiseButton.setOnClickListener {
//            toggleImageButtonCounterClockwise()
//        }

        if (robotViewModel.currentTurn > 0) {
            setRobotTurn()
            setRobotImages()
            updateMessageBox()
        }

    } // End of onCreate

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "OnStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "OnDestroy() called")
    }

    private fun toggleImage(){
        robotViewModel.advanceTurn()

        if (robotViewModel.currentTurn == 1) {
            redRobotImg.setImageResource(R.drawable.robot_red_large)
            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
        }else if (robotViewModel.currentTurn == 2) {
            whiteRobotImg.setImageResource(R.drawable.robot_white_large)
            redRobotImg.setImageResource(R.drawable.robot_red_small)
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
        }else {
            yellowRobotImg.setImageResource(R.drawable.robot_yellow_large)
            redRobotImg.setImageResource(R.drawable.robot_red_small)
            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
        }
        updateMessageBox()
        setRobotTurn()
        setRobotImages()
    }

    //    Code for Homework 1
//    private fun toggleImageButtonCounterClockwise() {
//        turnCount++
//        if (turnCount > 3) {
//            turnCount = 1
//        }
//        if (turnCount == 1) {
//            redRobotImg.setImageResource(R.drawable.robot_red_large)
//            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
//            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
//        } else if (turnCount == 2) {
//            whiteRobotImg.setImageResource(R.drawable.robot_white_large)
//            redRobotImg.setImageResource(R.drawable.robot_red_small)
//            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
//        } else {
//            yellowRobotImg.setImageResource(R.drawable.robot_yellow_large)
//            redRobotImg.setImageResource(R.drawable.robot_red_small)
//            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
//        }
//    }
//
//    private fun toggleImageButtonClockwise() {
//        // When we start, we set turnCount to 1 so that red is the first robot to become big
//        if (turnCount == 0) {
//            turnCount = 1
//        } else {
//            turnCount--
//        }
//        // After turnCount is 1, it is ran through the loop and the first if statement decrements it
//        // to 0, and this if statement equals it to 3, looping it back to the yellow robot
//        if (turnCount < 1) {
//            turnCount = 3
//        }
//        if (turnCount == 1) {
//            redRobotImg.setImageResource(R.drawable.robot_red_large)
//            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
//            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
//        } else if (turnCount == 2) {
//            whiteRobotImg.setImageResource(R.drawable.robot_white_large)
//            redRobotImg.setImageResource(R.drawable.robot_red_small)
//            yellowRobotImg.setImageResource(R.drawable.robot_yellow_small)
//        } else {
//            yellowRobotImg.setImageResource(R.drawable.robot_yellow_large)
//            redRobotImg.setImageResource(R.drawable.robot_red_small)
//            whiteRobotImg.setImageResource(R.drawable.robot_white_small)
//        }
//    }




    // Pick it up here
    private fun updateMessageBox() {
        when(robotViewModel.currentTurn) {
            1 -> messageBox.setText(R.string.red_message_text)
            2 -> messageBox.setText(R.string.white_message_text)
            else -> messageBox.setText(R.string.yellow_message_text)
        }
        messageBox.setText(robots[robotViewModel.currentTurn - 1].robotMessageResource)
    }

    private fun setRobotTurn() {
        for (robot in robots) {
            robot.myTurn = false
        }
        robots[robotViewModel.currentTurn - 1].myTurn = true
    }

    private fun setRobotImages() {
        for (indy in 0 .. 2) {
            if (robots[indy].myTurn) {
                robotImages[indy].setImageResource(robots[indy].robotImageLarge)
            } else {
                robotImages[indy].setImageResource(robots[indy].robotImageSmall)

            }
        }
    }
}