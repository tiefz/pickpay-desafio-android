package com.picpay.desafio.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.User
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
        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        viewModel.contactList.observe(this, Observer<List<User>> { users ->
            users.apply {
                adapter.users = users
            }
        })
    }
}
