package com.example.robot

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.cos

class RobotPurchase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Find this linking line
        setContentView(R.layout.activity_robot_purchase)
    }

    private fun makePurchase(cosOfPurchase : Int) {
        val rewards = listOf(R.string.reward_a_text, R.string.reward_b_text, R.string.reward_c_text)
        if(robot_energy >= cosOfPurchase) {
            robot_energy -= cosOfPurchase
            energyBox.setText(robot_energy.toString())
            val s1 = getString(rewards[cosOfPurchase - 1])
            val s2 = getString(R.string.purchased)
            val s3 = s1 + " " + s2
            Toast.makeText(this, s3, Toast.LENGTH_SHORT).show()
        }
    }
}