package com.f1tracker.ui.drivers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.f1tracker.data.model.Piloto
import com.f1tracker.databinding.ItemDriverBinding
import com.f1tracker.utils.AnimationUtils

/**
 * Adapter para mostrar la lista de pilotos en el RecyclerView
 * Versión simplificada para debugging
 */
class DriversAdapter(
    private val onPilotoClick: (Piloto) -> Unit
) : ListAdapter<Piloto, DriversAdapter.PilotoViewHolder>(PilotoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PilotoViewHolder {
        println("👥 DEBUG: DriversAdapter.onCreateViewHolder() llamado")
        val binding = ItemDriverBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PilotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PilotoViewHolder, position: Int) {
        val piloto = getItem(position)
        println("👤 DEBUG: Binding piloto: ${piloto.nombre} en posición $position")
        holder.bind(piloto)

        // Animación de entrada escalonada
        AnimationUtils.slideInFromRight(holder.itemView, 200 + (position * 50).toLong())
    }

    /**
     * ViewHolder para cada ítem de la lista de pilotos
     */
    inner class PilotoViewHolder(private val binding: ItemDriverBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(piloto: Piloto) {
            println("🔗 DEBUG: Binding piloto ${piloto.nombre}")

            binding.apply {
                // Configurar información del piloto
                textViewNombrePiloto.text = piloto.nombre
                textViewNumero.text = "#${piloto.numero}"
                textViewNacionalidad.text = piloto.nacionalidad
                textViewEdad.text = "${piloto.edad} años"

                // Cargar foto del piloto con Coil
                // La función load() de Coil ya está diseñada para manejar Int
                // Si aún no funciona, el problema es que la imagen no es encontrada por el ID en el `R.drawable`
                // La solución más simple y robusta es pasar el ID del recurso directamente.
                // Asegúrate de que los IDs en el F1Repository.kt son correctos.
                imageViewPiloto.load(piloto.imagenResId) {
                    crossfade(true)
                    placeholder(android.R.drawable.ic_menu_gallery)
                    error(android.R.drawable.ic_menu_close_clear_cancel)
                }

                // Click listener simplificado
                root.setOnClickListener {
                    println("👤 DEBUG: Click en piloto ${piloto.nombre}")
                    AnimationUtils.pulse(root, 1.02f, 150)
                    onPilotoClick(piloto)
                }
            }
        }
    }

    /**
     * DiffCallback para optimizar las actualizaciones del RecyclerView
     */
    private class PilotoDiffCallback : DiffUtil.ItemCallback<Piloto>() {
        override fun areItemsTheSame(oldItem: Piloto, newItem: Piloto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Piloto, newItem: Piloto): Boolean {
            return oldItem == newItem
        }
    }
}
