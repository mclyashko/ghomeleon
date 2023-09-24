package ru.mirea.ghomeleon.rest.game.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException

@ControllerAdvice(assignableTypes = [GameController::class])
class GameControllerAdvice {
    @ExceptionHandler(DomainException::class)
    fun handleGameControllerException(
        ex: DomainException,
    ): ResponseEntity<Any> {
        return when (ex) {
            is DomainException.GameAlreadyExistsException,
            -> ResponseEntity(HttpStatus.BAD_REQUEST)

            is DomainException.GetGameByIdNotFoundException,
            is DomainException.GetGameByNameNotFoundException,
            is DomainException.RemoveGameByIdNotFoundException,
            is DomainException.UpdateGameByIdNotFoundException,
            is DomainException.AddNewGameReleaseByIdNotFoundException,
            is DomainException.AddNewGameReviewByIdNotFoundException,
            -> ResponseEntity(HttpStatus.NOT_FOUND)

            is DomainException.UnknownManufacturerNameException,
            is DomainException.PlatformAlreadyExistsException,
            is DomainException.GetPlatformByIdNotFoundException,
            is DomainException.GetPlatformByNameNotFoundException,
            is DomainException.UpdatePlatformByIdNotFoundException,
            is DomainException.RemovePlatformByIdNotFoundException,
            -> ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
