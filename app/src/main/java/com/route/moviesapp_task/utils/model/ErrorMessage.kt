package com.route.moviesapp_task.utils.model

data class ErrorMessage(
    val title:String="",
    val message:String="",
    val posTitle:String="",
    val negaTitle:String="",
    val posClick:(()->Unit)?=null,
    val negaClick:(()->Unit)?=null
)
