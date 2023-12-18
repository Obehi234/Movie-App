package com.example.streammoviesapplication.presentation.tabViews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.streammoviesapplication.R
import com.example.streammoviesapplication.databinding.FragmentDocumentaryTabBinding
import com.example.streammoviesapplication.presentation.adapter.DocumentaryAdapter
import com.example.streammoviesapplication.presentation.viewmodel.tabViewModel.TabViewModel

class TabDocumentaryFragment : Fragment() {
    private var _binding: FragmentDocumentaryTabBinding? = null
    private val binding get() = _binding!!
    private val vm: TabViewModel by activityViewModels()
    private lateinit var documentaryAdapter: DocumentaryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDocumentaryTabBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        documentaryAdapter = DocumentaryAdapter()
        setUpRV()
    }

    private fun setUpRV() {
        TODO("Not yet implemented")
    }

}