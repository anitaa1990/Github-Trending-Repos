package com.an.github.ui.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.an.github.AppConstants.Companion.INTENT_POST

import com.an.github.R
import com.an.github.data.local.entity.GithubEntity
import com.an.github.databinding.GithubDetailActivityBinding
import com.an.github.utils.AppUtils
import com.an.github.utils.NavigatorUtils
import com.an.github.utils.ShareUtils
import com.squareup.picasso.Picasso

class GithubDetailActivity : AppCompatActivity() {

    private lateinit var githubEntity: GithubEntity
    private lateinit var binding: GithubDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseView()
    }

    private fun initialiseView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_detail)
        githubEntity = intent.getParcelableExtra(INTENT_POST)

        Picasso.get().load(githubEntity.owner.avatarUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.itemProfileImg)

        binding.itemTitle.text = githubEntity.fullName
        binding.itemStars.text = githubEntity.starsCount.toString()
        binding.itemWatchers.text = githubEntity.watchers.toString()
        binding.itemForks.text = githubEntity.forks.toString()

        githubEntity.language?.let {
            binding.itemImgLanguage.visibility = View.VISIBLE
            binding.itemLanguage.visibility = View.VISIBLE
            binding.itemLanguage.text = githubEntity.language
            updateColorTheme()
        }

        binding.btnShare.setOnClickListener { ShareUtils.shareUrl(this@GithubDetailActivity, githubEntity.htmlUrl) }
        binding.btnVisit.setOnClickListener { NavigatorUtils.openBrowser(this@GithubDetailActivity, githubEntity.htmlUrl) }
    }


    private fun updateColorTheme() {
        val bgColor = AppUtils.getColorByLanguage(applicationContext, githubEntity.language)

        binding.appBarLayout.setBackgroundColor(bgColor)
        binding.mainToolbar.toolbar.setBackgroundColor(bgColor)
        binding.btnShare.setTextColor(bgColor)
        binding.itemImgLanguage.setImageDrawable(AppUtils.updateGradientDrawableColor(applicationContext, bgColor))
        binding.btnVisit.setBackgroundDrawable(AppUtils.updateStateListDrawableColor(resources.getDrawable(R.drawable.btn_visit), bgColor))
        binding.btnShare.setBackgroundDrawable(AppUtils.updateStateListDrawableStrokeColor(resources.getDrawable(R.drawable.btn_share), bgColor))
        AppUtils.updateStatusBarColor(this, bgColor)
    }
}
