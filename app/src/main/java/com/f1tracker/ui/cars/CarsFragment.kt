package com.f1tracker.ui.cars

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.f1tracker.data.model.Auto
import com.f1tracker.data.model.Escuderia
import com.f1tracker.databinding.FragmentCarsBinding
import com.f1tracker.utils.AnimationUtils

/**
 * Fragment que muestra el auto de la escudería seleccionada
 * Permite la interacción para navegar a los pilotos
 */
class CarsFragment : Fragment() {

    private var _binding: FragmentCarsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CarsViewModel by viewModels()

    // Interface para comunicarse con la Activity
    interface OnAutoSelectedListener {
        fun onAutoSelected(auto: Auto, escuderia: Escuderia)
    }

    private var listener: OnAutoSelectedListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("🔗 DEBUG: CarsFragment.onAttach() llamado")

        // La Activity debe implementar OnAutoSelectedListener
        if (context is OnAutoSelectedListener) {
            listener = context
            println("✅ DEBUG: Listener configurado correctamente")
        } else {
            println("❌ DEBUG: Activity no implementa OnAutoSelectedListener")
            throw RuntimeException("$context must implement OnAutoSelectedListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        println("🔗 DEBUG: CarsFragment.onDetach() llamado")
        listener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println("🔧 DEBUG: CarsFragment.onCreateView() llamado")
        _binding = FragmentCarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("🔧 DEBUG: CarsFragment.onViewCreated() llamado")

        val escuderiaId = arguments?.getInt("escuderia_id", -1) ?: -1
        println("📋 DEBUG: EscuderiaId recibido: $escuderiaId")

        // Verificar si el listener está configurado
        println("🔗 DEBUG: Listener es null? ${listener == null}")

        setupObservers()
        setupClickListener()

        // Cargar datos del auto
        viewModel.loadAutoData(escuderiaId)
    }

    /**
     * Configura los observadores del ViewModel
     */
    private fun setupObservers() {
        println("👀 DEBUG: Configurando observadores")

        viewModel.auto.observe(viewLifecycleOwner) { auto ->
            println("🚗 DEBUG: Observer auto activado - Auto: ${auto?.modelo}")
            auto?.let {
                displayAuto(it)
            }
        }

        viewModel.escuderia.observe(viewLifecycleOwner) { escuderia ->
            println("🏎️ DEBUG: Observer escuderia activado - Escuderia: ${escuderia?.nombre}")
            escuderia?.let {
                displayEscuderiaInfo(it)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            println("⏳ DEBUG: Observer isLoading: $isLoading")
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    /**
     * Muestra la información del auto
     */
    private fun displayAuto(auto: Auto) {
        println("🎨 DEBUG: Mostrando auto: ${auto.modelo}")

        binding.apply {
            // Cargar imagen del auto con animación
            imageViewAuto.load(auto.imagenResId) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_gallery)
                listener(
                    onStart = { println("🖼️ DEBUG: Iniciando carga de imagen") },
                    onSuccess = { _, _ -> println("✅ DEBUG: Imagen cargada exitosamente") },
                    onError = { _, throwable -> println("❌ DEBUG: Error cargando imagen: ${throwable.throwable}") }
                )
            }

            // Configurar textos
            textViewModelo.text = auto.modelo
            textViewMotor.text = auto.motor
            textViewYear.text = "Año: ${auto.year}"

            // Animaciones escalonadas
            AnimationUtils.zoomIn(imageViewAuto, 600)
            AnimationUtils.slideInFromRight(textViewModelo, 400)
            AnimationUtils.slideInFromRight(textViewMotor, 500)
            AnimationUtils.slideInFromRight(textViewYear, 600)
        }
    }

    /**
     * Muestra información adicional de la escudería
     */
    private fun displayEscuderiaInfo(escuderia: Escuderia) {
        println("🏆 DEBUG: Mostrando info de escudería: ${escuderia.nombre}")

        binding.apply {
            textViewEscuderiaInfo.text = "Equipo: ${escuderia.nombre}"
            textViewPaisInfo.text = "País: ${escuderia.pais}"

            // Animación de entrada para la información
            AnimationUtils.fadeIn(textViewEscuderiaInfo, 700)
            AnimationUtils.fadeIn(textViewPaisInfo, 800)
        }
    }

    /**
     * Configura el listener para el click en el auto
     */
    private fun setupClickListener() {
        println("🎯 DEBUG: Configurando click listener")

        binding.cardViewAuto.setOnClickListener { view ->
            println("🎯 DEBUG: ¡Click detectado en cardViewAuto!")

            // Verificar el listener
            if (listener == null) {
                println("❌ DEBUG: listener es NULL! No se puede navegar")
                return@setOnClickListener
            } else {
                println("✅ DEBUG: listener está configurado correctamente")
            }

            val auto = viewModel.auto.value
            val escuderia = viewModel.escuderia.value

            println("🚗 DEBUG: Auto actual = ${auto?.modelo}")
            println("🏎️ DEBUG: Escudería actual = ${escuderia?.nombre}")

            if (auto != null && escuderia != null) {
                println("✅ DEBUG: Datos válidos, ejecutando callback...")

                // Animación antes de navegar
                AnimationUtils.pulse(binding.cardViewAuto, 1.1f, 200)
                AnimationUtils.rotate360(binding.imageViewAuto, 800)

                // Delayed navigation para permitir animación
                view.postDelayed({
                    println("🚀 DEBUG: Llamando listener.onAutoSelected()")
                    try {
                        listener?.onAutoSelected(auto, escuderia)
                        println("✅ DEBUG: Listener ejecutado exitosamente")
                    } catch (e: Exception) {
                        println("❌ DEBUG: Error ejecutando listener: ${e.message}")
                        e.printStackTrace()
                    }
                }, 400)
            } else {
                println("❌ DEBUG: Datos inválidos - Auto: $auto, Escudería: $escuderia")
                if (auto == null) println("   - Auto es NULL")
                if (escuderia == null) println("   - Escudería es NULL")
            }
        }

        // Agregar texto indicativo
        binding.textViewClickHint.apply {
            text = "👆 Toca el auto para ver los pilotos"
            AnimationUtils.fadeIn(this, 1000)
        }

        println("🎯 DEBUG: Click listener configurado completamente")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("🔧 DEBUG: CarsFragment.onDestroyView() llamado")
        _binding = null
    }
}