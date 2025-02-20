package com.mangabible.ui

import com.mangabible.data.repository.MainRepository
import com.mangabible.ui.util.MainViewModelFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mangabible.data.api.RetrofitBuilder
//import com.mangabible.databinding.ActivityMainBinding
import com.mangabible.ui.viewmodel.MainState
import com.mangabible.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        val helper = RetrofitBuilder.apiHelper
        val repository = MainRepository(helper)
        val factory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.fetchMangaList()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is MainState.Loading -> {
                            println("Estado: Loading")
                            Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
                        }

                        is MainState.Success -> {
                            val mangaList = state.mangaList
                            println("Lista de mangás: $mangaList")
                            Toast.makeText(this@MainActivity, state.mangaList, Toast.LENGTH_SHORT).show()
                        }

                        is MainState.Error -> {
                            Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
