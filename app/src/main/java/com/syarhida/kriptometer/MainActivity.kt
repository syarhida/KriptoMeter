package com.syarhida.kriptometer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.syarhida.kriptometer.adapter.CryptoAdapter
import com.syarhida.kriptometer.databinding.ActivityMainBinding
import com.syarhida.kriptometer.viewmodel.CryptoViewModel

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CryptoViewModel
    private lateinit var adapter: CryptoAdapter
    private lateinit var drawerToggle: ActionBarDrawerToggle

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
        // Custom centered title with Silkscreen font already set in XML
        supportActionBar?.setDisplayShowTitleEnabled(false)
        
        // Setup drawer toggle
        drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.app_name,
            R.string.app_name
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        
        // Set drawer toggle icon color
        drawerToggle.drawerArrowDrawable.color = getColor(R.color.text_primary)
        
        // Setup navigation view
        binding.navigationView.setNavigationItemSelectedListener(this)
        
        // Inflate menu for toolbar (refresh icon)
        binding.toolbar.inflateMenu(R.menu.toolbar_menu)
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_refresh -> {
                    viewModel.fetchCryptoData()
                    true
                }
                else -> false
            }
        }
        
        // Set toolbar menu icons tint
        binding.toolbar.overflowIcon?.setTint(getColor(R.color.text_primary))
    }

    private fun setupRecyclerView() {
        adapter = CryptoAdapter {
            // Handle load more click
            viewModel.loadMoreData()
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
            setHasFixedSize(true)
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[CryptoViewModel::class.java]

        // Observe display list
        viewModel.displayList.observe(this) { displayList ->
            if (displayList.isNullOrEmpty()) {
                showEmptyState()
            } else {
                hideEmptyState()
                adapter.submitList(displayList)
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
    
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_github -> {
                // Open GitHub link
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/syarhida/KriptoMeter"))
                startActivity(intent)
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
