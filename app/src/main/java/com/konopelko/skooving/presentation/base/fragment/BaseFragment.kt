package com.konopelko.skooving.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

	protected lateinit var binding: T

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = DataBindingUtil.inflate(inflater, layout, container, false)
		binding.lifecycleOwner = viewLifecycleOwner
		setupViewModelBinding()
		return binding.root
	}

	abstract val layout: Int

	abstract fun setupViewModelBinding()
}