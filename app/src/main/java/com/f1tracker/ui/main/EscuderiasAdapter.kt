package com.f1tracker.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.f1tracker.data.model.Escuderia
import com.f1tracker.databinding.ItemEscuderiaBinding
import com.f1tracker.utils.AnimationUtils

class EscuderiasAdapter(
    private val onEscuderiaClick: (Escuderia) -> Unit
) : ListAdapter<Escuderia, EscuderiasAdapter.EscuderiaViewHolder>(EscuderiaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EscuderiaViewHolder {
        val binding = ItemEscuderiaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EscuderiaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EscuderiaViewHolder, position: Int) {
        val escuderia = getItem(position)
        holder.bind(escuderia)

        // Animación de entrada escalonada para cada item
        AnimationUtils.fadeIn(holder.itemView, 300 + (position * 100L))
    }

    /**
     * ViewHolder para cada item de escudería
     */
    inner class EscuderiaViewHolder(
        private val binding: ItemEscuderiaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(escuderia: Escuderia) {
            binding.apply {
                // Configurar textos
                textViewNombreEscuderia.text = escuderia.nombre
                textViewPais.text = escuderia.pais

                // Cargar imagen del logo con Coil
                imageViewLogo.load(escuderia.logoResId) {
                    crossfade(true)
                    placeholder(android.R.drawable.ic_menu_gallery)
                }

                // Aplicar color de la escudería al fondo de la tarjeta
                try {
                    cardViewEscuderia.setCardBackgroundColor(Color.parseColor(escuderia.colorPrimario))
                } catch (e: Exception) {
                    // Color por defecto si hay error
                    cardViewEscuderia.setCardBackgroundColor(Color.GRAY)
                }

                // Configurar click listener con animación
                root.setOnClickListener {
                    AnimationUtils.pulse(root, 1.05f, 150)

                    // Delayed click para permitir que la animación se complete
                    root.postDelayed({
                        onEscuderiaClick(escuderia)
                    }, 200)
                }

                // Efecto hover con animación sutil
                root.setOnTouchListener { view, event ->
                    when (event.action) {
                        android.view.MotionEvent.ACTION_DOWN -> {
                            view.animate()
                                .scaleX(0.98f)
                                .scaleY(0.98f)
                                .setDuration(100)
                                .start()
                        }
                        android.view.MotionEvent.ACTION_UP,
                        android.view.MotionEvent.ACTION_CANCEL -> {
                            view.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                                .start()
                        }
                    }
                    false
                }
            }
        }
    }

    /**
     * DiffCallback para optimizar las actualizaciones del RecyclerView
     */
    private class EscuderiaDiffCallback : DiffUtil.ItemCallback<Escuderia>() {
        override fun areItemsTheSame(oldItem: Escuderia, newItem: Escuderia): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Escuderia, newItem: Escuderia): Boolean {
            return oldItem == newItem
        }
    }
}