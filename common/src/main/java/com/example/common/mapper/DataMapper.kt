package com.example.common.mapper

interface DataMapper<T, R> {

    fun from(source: T): R
}