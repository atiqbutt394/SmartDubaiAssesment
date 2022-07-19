package com.example.nytimes.articlelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.nytimes.R
import com.example.nytimes.constant.Constants
import com.example.nytimes.articlelist.adapter.ArticleListAdapter
import com.example.nytimes.databinding.FragmentArticleListBinding
import com.example.nytimes.viewmodel.ArticleFragmentViewModel

class ArticleListFragment : Fragment(),
    ArticleNavigator {
    private lateinit var adapter: ArticleListAdapter
    private val articleFragmentViewModel: ArticleFragmentViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentArticleListBinding>(
            inflater,
            R.layout.fragment_article_list,
            container,
            false
        )

        binding.viewModel = articleFragmentViewModel
        articleFragmentViewModel.setNavigator(this)
        binding.lifecycleOwner = viewLifecycleOwner

        adapter = ArticleListAdapter(clickListener = ArticleListAdapter.ArticleListClickListener {
            articleFragmentViewModel.onArticleClicked(it)
            view?.findNavController()
                ?.navigate(R.id.action_articleListFragment_to_articleDetailFragment)
        })
        binding.adapter = adapter

        binding.rv.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData(){
        articleFragmentViewModel.getMostViewed(Constants.Periods.PERIOD_7.value)
        articleFragmentViewModel.mostViewedLiveData.observe(requireActivity(), Observer {
            it?.let {
                adapter.submitList(it.results)
            }
        })
    }

    override fun onError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


}