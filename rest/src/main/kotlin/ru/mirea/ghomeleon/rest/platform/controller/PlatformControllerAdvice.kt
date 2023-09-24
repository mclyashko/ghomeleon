package ru.mirea.ghomeleon.rest.platform.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException

@ControllerAdvice(assignableTypes = [PlatformController::class])
class PlatformControllerAdvice {
    @ExceptionHandler(DomainException::class)
    fun handlePlatformControllerException(
        ex: DomainException,
    ): ResponseEntity<Any> {
        return when (ex) {
            is DomainException.PlatformAlreadyExistsException,
            -> ResponseEntity(HttpStatus.BAD_REQUEST)

            is DomainException.GetPlatformByIdNotFoundException,
            is DomainException.GetPlatformByNameNotFoundException,
            is DomainException.RemovePlatformByIdNotFoundException,
            is DomainException.UpdatePlatformByIdNotFoundException,
            -> ResponseEntity(HttpStatus.NOT_FOUND)

            is DomainException.AddNewGameReleaseByIdNotFoundException,
            is DomainException.AddNewGameReviewByIdNotFoundException,
            is DomainException.GameAlreadyExistsException,
            is DomainException.GetGameByIdNotFoundException,
            is DomainException.GetGameByNameNotFoundException,
            is DomainException.RemoveGameByIdNotFoundException,
            is DomainException.UnknownManufacturerNameException,
            is DomainException.UpdateGameByIdNotFoundException,
            -> ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
