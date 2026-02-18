package com.example.robot

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "RobotViewModel"

class RobotViewModel : ViewModel() {
    init {
        Log.d(TAG, "ViewModel instance created")
    }

    private var turnCount = 0
    val currentTurn : Int
        get() = turnCount

    override fun onCleared() {
        super.onCleared()
    }

    fun advanceTurn() {
        turnCount++
        if (turnCount > 3) {
            turnCount = 1
        }
    }
}