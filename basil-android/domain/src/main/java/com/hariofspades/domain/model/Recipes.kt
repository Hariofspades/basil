package com.hariofspades.domain.model

data class Recipes(
        val id: String,
        val publisher: String,
        val siteUrl: String,
        val title: String,
        val sourceUrl: String,
        val imageUrl: String,
        val rating: Int,
        val publisherUrl: String
)