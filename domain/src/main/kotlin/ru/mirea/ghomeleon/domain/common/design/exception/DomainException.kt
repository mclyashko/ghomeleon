package ru.mirea.ghomeleon.domain.common.design.exception

sealed class DomainException(
    message: String?,
    cause: Throwable?,
): RuntimeException(message = message, cause = cause) {
    class GameAlreadyExistsException(
        message: String? = null,
        cause: Throwable? = null,
    ): DomainException(message = message, cause = cause)

    class UnknownManufacturerNameException(
        message: String? = null,
        cause: Throwable? = null,
    ): DomainException(message = message, cause = cause)

    class PlatformAlreadyExistsException(
        message: String? = null,
        cause: Throwable? = null,
    ): DomainException(message = message, cause = cause)
}