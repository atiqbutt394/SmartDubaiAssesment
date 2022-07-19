package com.example.nytimes.ui.activity.fragment.articledetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.nytimes.R
import com.example.nytimes.databinding.FragmentArticleDetailBinding
import com.example.nytimes.viewmodel.ArticleFragmentViewModel


class ArticleDetailFragment : Fragment() {
    private val articleFragmentViewModel: ArticleFragmentViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentArticleDetailBinding>(
            inflater,
            R.layout.fragment_article_detail,
            container,
            false
        )
        articleFragmentViewModel.navigateToArticleDetailLiveData.observe(requireActivity(), Observer { result ->
            result?.let {
                val webSetting: WebSettings = binding.webView.getSettings()
                webSetting.javaScriptEnabled = true
                webSetting.displayZoomControls = true
                binding.webView.setWebChromeClient(WebChromeClient())
                binding.webView.loadUrl(result.url)
            }
        })

        return binding.root
    }

}