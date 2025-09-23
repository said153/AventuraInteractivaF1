package com.f1tracker.ui.drivers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import com.f1tracker.databinding.FragmentDriversBinding
import com.f1tracker.utils.AnimationUtils

/**
 * Fragment que muestra los pilotos de la escudería
 * Presenta información detallada de cada piloto
 */
class DriversFragment : Fragment() {

    private var _binding: FragmentDriversBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DriversViewModel by viewModels()
    private lateinit var adapter: DriversAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println("🔧 DEBUG: DriversFragment.onCreateView() llamado")
        _binding = FragmentDriversBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("🔧 DEBUG: DriversFragment.onViewCreated() llamado")

        val escuderiaId = arguments?.getInt("escuderia_id", -1) ?: -1
        println("🔍 DEBUG: DriversFragment recibido con ID: $escuderiaId")

        if (escuderiaId == -1) {
            println("❌ DEBUG: ID de escudería inválido!")
            showError("ID de escudería no válido")
            return
        }

        setupRecyclerView()
        setupObservers()

        // Cargar datos de los pilotos
        println("🔄 DEBUG: Cargando pilotos para escudería: $escuderiaId")
        viewModel.loadPilotos(escuderiaId)
    }

    /**
     * Configura el RecyclerView
     */
    private fun setupRecyclerView() {
        println("🔧 DEBUG: Configurando RecyclerView")

        adapter = DriversAdapter { piloto ->
            println("👤 DEBUG: Piloto seleccionado: ${piloto.nombre}")
            viewModel.selectPiloto(piloto)
        }

        binding.recyclerViewPilotos.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@DriversFragment.adapter

            // Agregar separador entre ítems
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

            // Mejorar el rendimiento
            setHasFixedSize(true)
        }

        println("✅ DEBUG: RecyclerView configurado")
    }

    /**
     * Observa cambios en los datos del ViewModel
     */
    private fun setupObservers() {
        println("👀 DEBUG: Configurando observadores")

        viewModel.pilotos.observe(viewLifecycleOwner) { pilotos ->
            println("🔍 DEBUG: Observer de pilotos notificado. Lista recibida con ${pilotos.size} pilotos.")

            if (pilotos.isEmpty()) {
                println("⚠️ DEBUG: La lista de pilotos está vacía. ¡No se mostrará nada!")
                showEmptyState()
            } else {
                println("✅ DEBUG: Pilotos recibidos: ${pilotos.joinToString { "${it.nombre}(#${it.numero})" }}")
                hideEmptyState()
                adapter.submitList(pilotos)

                // Animar la aparición del RecyclerView
                AnimationUtils.fadeIn(binding.recyclerViewPilotos, 600)
            }
        }

        viewModel.selectedPiloto.observe(viewLifecycleOwner) { piloto ->
            piloto?.let {
                println("👤 DEBUG: Piloto seleccionado: ${it.nombre}")
                showPilotoDetails(it)
            }
        }

        viewModel.escuderia.observe(viewLifecycleOwner) { escuderia ->
            escuderia?.let {
                println("🏎️ DEBUG: Escudería cargada: ${it.nombre}")
                binding.textViewEscuderiaName.text = it.nombre
                binding.textViewEscuderiaPais.text = "País: ${it.pais}"

                // Animaciones para la información de la escudería
                AnimationUtils.fadeIn(binding.textViewEscuderiaName, 600)
                AnimationUtils.fadeIn(binding.textViewEscuderiaPais, 700)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            println("⏳ DEBUG: isLoading: $isLoading")
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

            if (isLoading) {
                binding.recyclerViewPilotos.visibility = View.GONE
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                println("❌ DEBUG: Error: $it")
                showError(it)
            }
        }
    }

    /**
     * Muestra estado vacío cuando no hay pilotos
     */
    private fun showEmptyState() {
        binding.apply {
            recyclerViewPilotos.visibility = View.GONE
            textViewEmptyState.visibility = View.VISIBLE
            textViewEmptyState.text = "No se encontraron pilotos para esta escudería"
        }
    }

    /**
     * Oculta el estado vacío
     */
    private fun hideEmptyState() {
        binding.apply {
            textViewEmptyState.visibility = View.GONE
            recyclerViewPilotos.visibility = View.VISIBLE
        }
    }

    /**
     * Muestra un mensaje de error
     */
    private fun showError(message: String) {
        binding.apply {
            recyclerViewPilotos.visibility = View.GONE
            textViewEmptyState.visibility = View.VISIBLE
            textViewEmptyState.text = "Error: $message"
        }
    }

    /**
     * Muestra detalles adicionales del piloto seleccionado
     */
    private fun showPilotoDetails(piloto: com.f1tracker.data.model.Piloto) {
        binding.apply {
            textViewSelectedPiloto.text = "Piloto seleccionado: ${piloto.nombre}"
            textViewPilotoDetails.text = """
                Número: #${piloto.numero}
                Nacionalidad: ${piloto.nacionalidad}
                Edad: ${piloto.edad} años
            """.trimIndent()

            // Mostrar información adicional con animación
            AnimationUtils.fadeIn(textViewSelectedPiloto, 300)
            AnimationUtils.slideInFromRight(textViewPilotoDetails, 400)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("🔧 DEBUG: DriversFragment.onDestroyView() llamado")
        _binding = null
    }
}