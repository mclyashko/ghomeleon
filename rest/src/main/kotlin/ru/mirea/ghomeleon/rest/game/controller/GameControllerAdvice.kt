package ru.mirea.ghomeleon.rest.game.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException

@ControllerAdvice(assignableTypes = [GameController::class])
class GameControllerAdvice {
    @ExceptionHandler(DomainException::class)
    fun handleGameControllerException(
        ex: DomainException, request: WebRequest
    ): ResponseEntity<Any> {
        return when (ex) {
            is DomainException.GameAlreadyExistsException,
            -> ResponseEntity(HttpStatus.BAD_REQUEST)

            is DomainException.GetGameByIdNotFoundException,
            -> ResponseEntity(HttpStatus.NOT_FOUND)

            is DomainException.UnknownManufacturerNameException,
            is DomainException.PlatformAlreadyExistsException,
            is DomainException.GetPlatformByIdNotFoundException
            -> ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}