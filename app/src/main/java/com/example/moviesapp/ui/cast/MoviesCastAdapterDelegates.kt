package com.example.moviesapp.ui.cast

import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.ListItemCastBinding
import com.example.moviesapp.databinding.ListItemHeaderBinding
import com.example.moviesapp.ui.RVItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun movieCastHeaderDelegate() =
    adapterDelegateViewBinding<MoviesCastRVItem.HeaderItem, RVItem, ListItemHeaderBinding>(
        { layoutInflater, root -> ListItemHeaderBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.headerTextView.text = item.headerText
        }
    }

fun movieCastPersonDelegate() =
    adapterDelegateViewBinding<MoviesCastRVItem.PersonItem, RVItem, ListItemCastBinding>(
        { layoutInflater, root -> ListItemCastBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            if (item.data.image == null) {
                binding.actorImageView.isVisible = false
            } else {
                Glide.with(itemView)
                    .load(item.data.image)
                    .into(binding.actorImageView)
                binding.actorImageView.isVisible = true
            }

            binding.actorNameTextView.text = item.data.name
            binding.actorDescriptionTextView.text = item.data.description
        }
    }