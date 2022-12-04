package com.github.polydome.trainsapi.validation

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun <T> requireParamNotNull(parameterName: String, value: T?) {
    contract {
        returns() implies (value != null)
    }

    if (value == null) {
        throw IllegalParameterException(parameterName, ValidationIssue.MISSING)
    }
}