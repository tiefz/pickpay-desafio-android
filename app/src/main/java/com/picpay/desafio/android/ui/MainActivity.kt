package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.adapters.UserListAdapter
import com.picpay.desafio.android.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserListAdapter

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModel.Factory(this.application)
        )[MainViewModel::class.java]

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.viewModel = viewModel
        recyclerViewSetup()
    }

    private fun recyclerViewSetup() {
        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.contactList.observe(this) { users ->
            users.apply {
                adapter.users = users
            }
        }
        viewModel.hasNetworkData.observe(this) {
            if (!it) {
                val message = getString(R.string.error)
                binding.userListProgressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
