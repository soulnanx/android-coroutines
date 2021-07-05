package com.example.androidcoroutines.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.androidcoroutines.R
import com.example.androidcoroutines.base.extensions.BaseActivity

import com.example.androidcoroutines.databinding.MainActivityBinding
import com.example.androidcoroutines.ui.home.view.HomeFragment

class MainActivity : BaseActivity() {

    private var _binding : MainActivityBinding? = null
    val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null){
            setContainer(HomeFragment.newInstance())
        }

    }

    fun setContainer(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
    }

    override fun onBackPressed() {
        setContainer(HomeFragment.newInstance())
    }
}