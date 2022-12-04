package com.github.polydome.trainsapi.validation

import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class IllegalParameterHandler : ExceptionMapper<IllegalParameterException> {
    override fun toResponse(exception: IllegalParameterException): Response {

        return Response.status(Response.Status.BAD_REQUEST)
            .entity(
                ErrorResponse(
                    error = exception.makeMessage()
                )
            )
            .build()
    }

    private fun IllegalParameterException.makeMessage(): String =
        when (issue) {
            ValidationIssue.MISSING -> "Missing parameter `$parameterName`"
        }
}