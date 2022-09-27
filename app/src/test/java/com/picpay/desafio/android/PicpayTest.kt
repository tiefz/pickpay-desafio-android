package com.picpay.desafio.android

import com.picpay.desafio.android.database.DatabaseUser
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.network.PicPayService
import com.picpay.desafio.android.repository.PicPayRepository
import com.picpay.desafio.android.viewmodel.MainViewModel
import io.mockk.mockk
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PicpayTest : CoroutineTest() {

    private val dao = FakeDao()
    private val network = mockk<PicPayService>()
    private val user = User(img = "img", name = "name", id = 1, username = "username")
    private val usersList = listOf(user)
    private val userResponse = CompletableDeferred<List<User>>().apply { complete(listOf(user)) }
    private val databaseUser = DatabaseUser(user.img, user.name, user.id, user.username)
    private val repository = PicPayRepository(dao, network)
    private val viewModel by lazy { MainViewModel(repository) }

    @Before
    fun setupDao(): Unit = runBlocking {
        dao.insertAll(databaseUser)
        viewModel.updateView()
    }

    @Test
    fun return_user_list_from_api(): Unit = runBlocking {


        viewModel.contactList.observeForever { users ->
            assertEquals(users, usersList)
        }
    }


}