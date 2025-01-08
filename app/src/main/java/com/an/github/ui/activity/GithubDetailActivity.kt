package com.an.github.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.an.github.data.local.entity.GithubEntity

class GithubDetailActivity : AppCompatActivity() {

    private lateinit var githubEntity: GithubEntity
//    private lateinit var binding: GithubDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseView()
    }

    private fun initialiseView() {
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_detail)
//        githubEntity = intent.getParcelableExtra(INTENT_POST)
//
//        Picasso.get().load(githubEntity.owner.avatarUrl)
//                .placeholder(R.drawable.ic_placeholder)
//                .into(binding.itemProfileImg)
//
//        binding.itemTitle.text = githubEntity.fullName
//        binding.itemStars.text = githubEntity.starsCount.toString()
//        binding.itemWatchers.text = githubEntity.watchers.toString()
//        binding.itemForks.text = githubEntity.forks.toString()
//
//        githubEntity.language?.let {
//            binding.itemImgLanguage.visibility = View.VISIBLE
//            binding.itemLanguage.visibility = View.VISIBLE
//            binding.itemLanguage.text = githubEntity.language
//            updateColorTheme()
//        }
//
//        binding.btnShare.setOnClickListener { ShareUtils.shareUrl(this@GithubDetailActivity, githubEntity.htmlUrl) }
//        binding.btnVisit.setOnClickListener { NavigatorUtils.openBrowser(this@GithubDetailActivity, githubEntity.htmlUrl) }
    }


    private fun updateColorTheme() {
//        val bgColor = AppUtils.getColorByLanguage(applicationContext, githubEntity.language)
//
//        binding.appBarLayout.setBackgroundColor(bgColor)
//        binding.mainToolbar.toolbar.setBackgroundColor(bgColor)
//        binding.btnShare.setTextColor(bgColor)
//        binding.itemImgLanguage.setImageDrawable(AppUtils.updateGradientDrawableColor(applicationContext, bgColor))
//        binding.btnVisit.setBackgroundDrawable(AppUtils.updateStateListDrawableColor(resources.getDrawable(R.drawable.btn_visit), bgColor))
//        binding.btnShare.setBackgroundDrawable(AppUtils.updateStateListDrawableStrokeColor(resources.getDrawable(R.drawable.btn_share), bgColor))
//        AppUtils.updateStatusBarColor(this, bgColor)
    }
}
