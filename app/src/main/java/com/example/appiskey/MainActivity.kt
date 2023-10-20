package com.example.appiskey

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.appiskey.databinding.ActivityMainBinding
import com.example.appiskey.utils.ResUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostContainer) as? NavHostFragment
        navController = navHostFragment?.navController
    }

    private fun countWords() {
        val input = getString(R.string.string_to_count)
        val result = ResUtil.charCount(input)
        Log.d("MainActivity", "Total Words: ${result["total_words"]}\nCount / Word:")
        for (str in result.keys)
            Log.d("MainActivity", "$str ${result[str]}")
    }
}