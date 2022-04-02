package com.example.parstagram

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.parstagram.fragments.ComposeFragment
import com.example.parstagram.fragments.FeedFragment
import com.example.parstagram.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*

/**
 * Let's user create a post by taking a photo with their camera
 */

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener { item ->

            var fragmentToShow: Fragment? = null
            when (item.itemId) {

                R.id.action_home -> {
                    // Navigate to the Home Screen/ feed fragment
                    fragmentToShow = FeedFragment()
                }

                R.id.action_compose -> {
                    // Navigate to the Compose Screen
                    fragmentToShow = ComposeFragment()
                }

                R.id.action_profile -> {
                    // Navigate to the Profile Screen
                    fragmentToShow = ProfileFragment()
                }
            }
            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow)
                    .commit()
            }

            // returning true signifies that we have handled this user interaction on the item
            true
        }

        // Set default selection
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home


//        findViewById<Button>(R.id.log_out_btn).setOnClickListener {
//            logOut()
//            goToLoginActivity()
//        }
    }


    private fun logOut () {
        ParseUser.logOut()
        val currentUser = ParseUser.getCurrentUser()
    }

    private fun goToLoginActivity () {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        const val TAG = "MainActivity"
    }

}