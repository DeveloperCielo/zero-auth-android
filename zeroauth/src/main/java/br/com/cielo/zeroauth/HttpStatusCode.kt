package br.com.cielo.zeroauth

enum class HttpStatusCode(val code: Int) {
    Ok(200),
    Created(201),
    Accepted(202),
    NoContent(204),
    NotModified(304),
    BadRequest(400),
    Unauthorized(401),
    Forbidden(403),
    NotFound(404),
    RequestTimeout(408),
    Conflict(409),
    PreconditionFailure(412),
    EntityTooLarge(413),
    TooManyRequests(429),
    RetryWith(449),
    InternalServerError(500),
    ServiceUnavailable(503),
    Unknown(-1)
}

fun Int.toStatusCode(): HttpStatusCode = when (this) {
    200 -> HttpStatusCode.Ok
    201 -> HttpStatusCode.Created
    202 -> HttpStatusCode.Accepted
    204 -> HttpStatusCode.NoContent
    304 -> HttpStatusCode.NotModified
    400 -> HttpStatusCode.BadRequest
    401 -> HttpStatusCode.Unauthorized
    403 -> HttpStatusCode.Forbidden
    404 -> HttpStatusCode.NotFound
    408 -> HttpStatusCode.RequestTimeout
    409 -> HttpStatusCode.Conflict
    412 -> HttpStatusCode.PreconditionFailure
    413 -> HttpStatusCode.EntityTooLarge
    429 -> HttpStatusCode.TooManyRequests
    449 -> HttpStatusCode.RetryWith
    500 -> HttpStatusCode.InternalServerError
    503 -> HttpStatusCode.ServiceUnavailable
    else -> HttpStatusCode.Unknown
}