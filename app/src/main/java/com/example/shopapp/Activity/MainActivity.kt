    package com.example.shopapp.Activity

    import android.os.Build
    import android.os.Bundle
    import android.view.View
    import androidx.activity.enableEdgeToEdge
    import androidx.annotation.RequiresApi
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import androidx.lifecycle.Observer
    import androidx.recyclerview.widget.GridLayoutManager
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import androidx.viewpager2.widget.CompositePageTransformer
    import androidx.viewpager2.widget.MarginPageTransformer
    import com.example.shopapp.Adapter.BrandAdapter
    import com.example.shopapp.Adapter.PopularAdapter
    import com.example.shopapp.Adapter.SliderAdapter
    import com.example.shopapp.Model.SliderModel
    import com.example.shopapp.ViewModel.MainViewModel
    import com.example.shopapp.databinding.ActivityMainBinding

    class MainActivity<BrandModel> : AppCompatActivity() {
        private val viewModel = MainViewModel()
        private lateinit var binding: ActivityMainBinding

        @RequiresApi(Build.VERSION_CODES.R)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            // Enable edge-to-edge display but handle insets
            enableEdgeToEdge()

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            // Apply WindowInsetsListener to handle padding dynamically
            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
                val systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())

                // Adjust padding to avoid overlapping system bars
                view.setPadding(
                    view.paddingLeft,
                    systemInsets.top,   // Padding for the status bar
                    view.paddingRight,
                    systemInsets.bottom // Padding for the navigation bar
                )
                insets // Return insets to allow system handling
            }

            // Initialize banners or other content
            initBanner()
            initBrand()
            initPopular()
        }

        private fun initBanner() {
            binding.bannerProgressBar.visibility = View.VISIBLE
            viewModel.banners.observe(this) { items ->
                banners(items)
                binding.bannerProgressBar.visibility = View.GONE
            }
            viewModel.loadBanners()
        }

        private fun banners(images: List<SliderModel>) {
            binding.viewPagerSlider.adapter = SliderAdapter(images, binding.viewPagerSlider)
            binding.viewPagerSlider.clipToPadding = false
            binding.viewPagerSlider.clipChildren = false
            binding.viewPagerSlider.offscreenPageLimit = 3
            binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
            }
            binding.viewPagerSlider.setPageTransformer(compositePageTransformer)

            if (images.size > 1) {
                binding.dotsIndicator.visibility = View.VISIBLE
                binding.dotsIndicator.attachTo(binding.viewPagerSlider)
            }
        }
        private fun initBrand() {
            binding.progressBarBrand. visibility = View.VISIBLE
            viewModel.brands.observe (  this, Observer {
                binding.Viewbrand.layoutManager =
                    LinearLayoutManager( this@MainActivity, LinearLayoutManager.HORIZONTAL,  false)
                binding.Viewbrand.adapter = BrandAdapter(it)
                binding.progressBarBrand.visibility = View.GONE
            })
            viewModel.loadBrand()
        }
        private fun initPopular() {
            binding.progressBarPopular.visibility = View.VISIBLE
            viewModel.popular.observe (  this, Observer {
                binding.ViewPopular.layoutManager =GridLayoutManager(this@MainActivity,2)
                binding.ViewPopular.adapter = PopularAdapter(it)
                binding.progressBarPopular.visibility = View.GONE
            })
            viewModel.loadPopular()
        }

    }
