package com.ivanfilip.airsorsix.utills

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

inline fun <reified T : Any> loggerFor(): Logger = LoggerFactory.getLogger(T::class.java)

fun generateId(): String = UUID.randomUUID().toString()
