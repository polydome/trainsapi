package com.github.polydome.trainsapi.validation

import com.github.polydome.trainsapi.repository.NoSuchResourceException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ResourceExceptionHandler: ExceptionMapper<NoSuchResourceException> {
    override fun toResponse(exception: NoSuchResourceException): Response {
        return Response.status(404).build()
    }
}