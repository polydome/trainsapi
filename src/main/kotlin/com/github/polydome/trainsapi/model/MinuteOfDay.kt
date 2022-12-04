package com.github.polydome.trainsapi.model

@JvmInline
value class MinuteOfDay(val value: Int) {
    companion object {
        fun parse(input: String): MinuteOfDay {
            val hourAndMinutes = input
                .split(":")
                .map { it.toInt() }
            val hour = hourAndMinutes.first()
            val minute = hourAndMinutes.last()
            require(hour in 0..23)
            require(minute in 0 .. 59)
            return MinuteOfDay(hour * 60 + minute)
        }
    }
}