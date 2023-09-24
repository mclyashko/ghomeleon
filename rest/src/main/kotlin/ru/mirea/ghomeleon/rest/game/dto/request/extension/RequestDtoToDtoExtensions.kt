package ru.mirea.ghomeleon.rest.game.dto.request.extension

import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameReleaseRequest
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameRequest
import ru.mirea.ghomeleon.rest.game.dto.request.AddGameReviewRequest
import ru.mirea.ghomeleon.rest.game.dto.request.UpdateGameRequest
import ru.mirea.ghomeleon.usecase.game.dto.AddGameInfo
import ru.mirea.ghomeleon.usecase.game.dto.AddGameReleaseInfo
import ru.mirea.ghomeleon.usecase.game.dto.AddGameReviewInfo
import ru.mirea.ghomeleon.usecase.game.dto.UpdateGameInfo

fun AddGameRequest.toAddGameInfo(): AddGameInfo = AddGameInfo(
    name = Game.Name(name),
    description = Game.Description(description),
)

fun AddGameReviewRequest.toAddGameReviewInfo(): AddGameReviewInfo = AddGameReviewInfo(
    mark = Game.Review.Mark(mark),
    text = Game.Review.Text(text),
)

fun AddGameReleaseRequest.toAddGameReleaseInfo(): AddGameReleaseInfo = AddGameReleaseInfo(
    date = Game.Release.Date(date),
    platformId = Platform.Id(platformId),
)

fun UpdateGameRequest.toUpdateGameInfo(): UpdateGameInfo = UpdateGameInfo(
    name = Game.Name(name),
    description = Game.Description(description),
)
