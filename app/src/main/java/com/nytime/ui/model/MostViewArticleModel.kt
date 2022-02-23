package com.nytime.ui.model
/**
 * <p>
 * @author zuhaib ahmed
 */
data class MostViewArticleModel(
        val status: String,
        val copyright: String,
        val results: List<Result>? = null
)


