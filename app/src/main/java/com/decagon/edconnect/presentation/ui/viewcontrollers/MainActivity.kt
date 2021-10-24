package com.decagon.edconnect.presentation.ui.viewcontrollers

import android.content.Context
import android.net.ConnectivityManager
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.decagon.edconnect.R
import com.decagon.edconnect.databinding.ActivityMainBinding
import com.decagon.edconnect.utils.NetworkLiveData
import com.decagon.edconnect.utils.NetworkLiveData.isNetworkAvailable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_EdConnectMain)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

//        NetworkLiveData.observe(this, {
//            if (it) {
//                //connected
//                Log.d("AAA", "connected")
//            } else {
//                //connection gone
//                Log.d("AAA", "not connected")
//            }
//        })
//
//        if (isNetworkAvailable()){
//            // do something
//            Log.d("AAA", "connected")
//        }

    }


//    private fun setupSearchViewListener() {
//        val searchView = binding.searchWidget.search     searchView.setOnQueryTextListener(
//                object : SearchView.OnQueryTextListener { override fun onQueryTextSubmit(
//                    query: String?           ): Boolean {             viewModel.onEvent(
//                    SearchEvent.QueryInput(query.orEmpty())  // 1             )
//                            searchView.clearFocus()
//                    return true
//                }
//                    override fun onQueryTextChange(
//                        newText: String?           ): Boolean {             viewModel.onEvent(
//                        SearchEvent.QueryInput(newText.orEmpty()) // 2             )
//                        return true
//                    }         }     )
//    }
//// ...
//}
}