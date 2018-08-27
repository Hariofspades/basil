package com.hariofspades.data.model

data class RecipesEntity (
        val id: String,
        val publisher: String,
        val siteUrl: String,
        val title: String,
        val sourceUrl: String,
        val imageUrl: String,
        val rating: Int,
        val publisherUrl: String,
        var isBookmarked: Boolean,
        var category: String
)