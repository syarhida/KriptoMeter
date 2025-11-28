package com.syarhida.kriptometer

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.syarhida.kriptometer.adapter.CryptoAdapter
import com.syarhida.kriptometer.databinding.ActivityMainBinding
import com.syarhida.kriptometer.viewmodel.CryptoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CryptoViewModel
    private lateinit var adapter: CryptoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupViewModel()
        setupSwipeRefresh()
        
        // Load data
        viewModel.fetchCryptoData()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupRecyclerView() {
        adapter = CryptoAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            setHasFixedSize(true)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[CryptoViewModel::class.java]

        // Observe crypto list
        viewModel.cryptoList.observe(this) { cryptoList ->
            if (cryptoList.isNullOrEmpty()) {
                showEmptyState()
            } else {
                hideEmptyState()
                adapter.submitList(cryptoList)
            }
        }

        // Observe loading state
        viewModel.isLoading.observe(this) { isLoading ->
            binding.swipeRefreshLayout.isRefreshing = isLoading
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.textLoading.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.textLoading.visibility = View.GONE
            }
        }

        // Observe error messages
        viewModel.errorMessage.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.primary)
            setProgressBackgroundColorSchemeResource(R.color.card_surface)
            setOnRefreshListener {
                viewModel.fetchCryptoData()
            }
        }
    }

    private fun showEmptyState() {
        binding.textEmpty.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
    }

    private fun hideEmptyState() {
        binding.textEmpty.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
    }
}
