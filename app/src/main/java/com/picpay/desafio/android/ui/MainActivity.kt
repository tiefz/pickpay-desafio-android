package com.picpay.desafio.android.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.databinding.DataBindingUtil
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.adapters.UserListAdapter
import com.picpay.desafio.android.ui.compose.TestString
import com.picpay.desafio.android.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserListAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.apply {
            viewModel = viewModel
            composeView.setContent {
                MaterialTheme {
                    TestString()
                }
            }
        }
        //recyclerViewSetup()
    }

//    private fun recyclerViewSetup() {
//        adapter = UserListAdapter()
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        viewModel.contactList.observe(this) { users ->
//            users.apply {
//                adapter.users = users
//            }
//            viewModel.updateView()
//        }
//    }
}
