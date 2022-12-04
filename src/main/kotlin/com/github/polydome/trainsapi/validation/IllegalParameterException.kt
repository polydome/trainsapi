package com.github.polydome.trainsapi.validation

class IllegalParameterException(
    val parameterName: String,
    val issue: ValidationIssue
) : IllegalArgumentException()