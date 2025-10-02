package com.f1tracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.f1tracker.data.model.Escuderia
import com.f1tracker.databinding.FragmentMainBinding
import com.f1tracker.utils.AnimationUtils

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: EscuderiasAdapter

    // Callback para comunicarse con la Activity
    var onEscuderiaSelected: ((Escuderia) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()

        // Animación de entrada del RecyclerView
        AnimationUtils.slideInFromRight(binding.recyclerViewEscuderias, 800)
    }

    /**
     * Configura el RecyclerView con su adapter
     */
    private fun setupRecyclerView() {
        adapter = EscuderiasAdapter { escuderia ->
            // Animación de pulso antes de navegar
            AnimationUtils.pulse(binding.recyclerViewEscuderias)
            onEscuderiaSelected?.invoke(escuderia)
        }

        binding.recyclerViewEscuderias.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@MainFragment.adapter
        }
    }

    /**
     * Observa los datos del ViewModel
     */
    private fun observeViewModel() {
        viewModel.escuderias.observe(viewLifecycleOwner) { escuderias ->
            adapter.submitList(escuderias)

        }

        // Cargar datos
        viewModel.loadEscuderias()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}