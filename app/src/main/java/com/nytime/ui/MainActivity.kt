package com.nytime.ui


import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nytime.common.MostViewItemAdapter
import com.nytime.nytimes.databinding.ActivityMainBinding
import com.nytime.ui.model.Result
import dagger.hilt.android.AndroidEntryPoint


/**
 * <p>
 * @author zuhaib ahmed
 */
@AndroidEntryPoint
class MainActivity : BaseActivity(), MostViewItemAdapter.AccountsViewHolder.ItemClickListener{

    private lateinit var binding: ActivityMainBinding

    private val dashboardItemList = mutableListOf<Result>()
    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    override val viewModel by viewModels<MostViewActivityViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchMostViewArticle();

        binding.rvMostViewArticle.layoutManager =
          LinearLayoutManager(this)
        binding.rvMostViewArticle.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val accountsAdapter = MostViewItemAdapter(dashboardItemList,this)
        binding.rvMostViewArticle.adapter = accountsAdapter

    }

    override fun setUpObservers() {

        viewModel.mostViewLiveData.observe(this, {
            dashboardItemList.clear()
            dashboardItemList.addAll(it.results!!)
            binding.rvMostViewArticle.adapter?.notifyDataSetChanged()
        })
    }

    override fun onItemClick(position: Int, url: String) {
        var intent  = Intent(this,MovtViewDetailScreen::class.java)
        intent.putExtra("url",url)
        startActivity(intent)
    }


}
