package com.route.moviesapp_task.utils.model

data class ErrorMessage(
    val title:String?=null,
    val message:String?=null,
    val posTitle:String?=null,
    val negaTitle:String?=null,
    val posClick:(()->Unit)?=null,
    val negaClick:(()->Unit)?=null
)
