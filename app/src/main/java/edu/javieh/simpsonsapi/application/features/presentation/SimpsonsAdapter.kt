package edu.javieh.simpsonsapi.application.features.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.javieh.simpsonsapi.application.features.domain.Simpson
import edu.javieh.simpsonsapi.databinding.SimpsonItemLayoutBinding
import coil3.load
import edu.javieh.simpsonsapi.R


class SimpsonsAdapter : RecyclerView.Adapter<SimpsonsAdapter.SimpsonViewHolder>() {

    private var simpsons: List<Simpson> = mutableListOf()

    fun submitList(newSimpsons: List<Simpson>) {
        simpsons = newSimpsons
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpsonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simpson_item_layout, parent, false)
        return SimpsonViewHolder(view)
    }

    override fun onBindViewHolder(holder: SimpsonViewHolder, position: Int) {
        holder.bind(simpsons[position])
    }

    override fun getItemCount(): Int = simpsons.size

    inner class SimpsonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SimpsonItemLayoutBinding.bind(view)

        fun bind(simpson: Simpson) {
            binding.siTvName.text = simpson.name
            binding.siTvOccupation.text = simpson.occupation
            binding.siIvPhoto.load(simpson.imageUrl)
        }
    }
}