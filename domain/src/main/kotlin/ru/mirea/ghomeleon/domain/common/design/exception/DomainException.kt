package ru.mirea.ghomeleon.domain.common.design.exception

sealed class DomainException(
    message: String?,
    cause: Throwable?,
) : RuntimeException(message, cause) {

    // Domain exceptions

    class GameAlreadyExistsException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class UnknownManufacturerNameException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class PlatformAlreadyExistsException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    // Use-case exceptions

    class GetGameByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class GetGameByNameNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class AddNewGameReviewByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class AddNewGameReleaseByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class UpdateGameByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class RemoveGameByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class GetPlatformByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class GetPlatformByNameNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class UpdatePlatformByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)

    class RemovePlatformByIdNotFoundException(
        message: String? = null,
        cause: Throwable? = null,
    ) : DomainException(message = message, cause = cause)
}
