package com.an.github.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.an.github.databinding.FragmentGithubListBinding

class GitHubListFragment: Fragment() {

    private lateinit var binding: FragmentGithubListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGithubListBinding.inflate(inflater, container, false)
        return binding.root
    }
}
