package com.github.polydome.trainsapi.repository

import java.lang.RuntimeException

class NoSuchResourceException : RuntimeException() {
    override val message: String
        get() = "No such resource"
}