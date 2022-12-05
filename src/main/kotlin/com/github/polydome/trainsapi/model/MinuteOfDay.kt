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

    override fun toString(): String {
        val hours = value / 60
        val minutes = value.mod(60)
        return "${hours.formatTime()}:${minutes.formatTime()}"
    }

    private fun Int.formatTime(): String =
        this.toString().padStart(2, '0')
}