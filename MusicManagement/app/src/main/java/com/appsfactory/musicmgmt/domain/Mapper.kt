package com.appsfactory.musicmgmt.domain

interface Mapper<in In, out Out> {
    fun convert(data: In): Out
}