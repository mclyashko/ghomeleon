package ru.mirea.ghomeleon.domain.game

import ru.mirea.ghomeleon.domain.common.design.entity.AggregateRoot
import ru.mirea.ghomeleon.domain.common.design.entity.DomainEntity
import ru.mirea.ghomeleon.domain.common.design.entity.ValueObject
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.declaration.GameAlreadyExists
import ru.mirea.ghomeleon.domain.game.declaration.GameIdGenerator
import ru.mirea.ghomeleon.domain.platform.Platform
import java.time.LocalDate

class Game internal constructor(
    id: Id,
    val name: Name,
    val description: Description,
    val reviews: List<Review>,
    val releases: List<Release>,
) : AggregateRoot<Game.Id>(id = id) {

    var removed: Boolean = false
        internal set

    data class Id(
        private val value: Long,
    ) : ValueObject {
        fun toLongValue(): Long = value
    }

    data class Name(
        private val value: String,
    ) : ValueObject {
        fun toStringValue(): String = value
    }

    data class Description(
        private val value: String,
    ) : ValueObject {
        fun toStringValue(): String = value
    }

    class Review(
        id: Id,
        val mark: Mark,
        val text: Text,
    ) : DomainEntity<Review.Id>(id = id) {
        data class Id(
            private val value: Long,
        ) : ValueObject {
            fun toLongValue(): Long = value
        }

        data class Mark(
            private val value: Byte,
        ) : ValueObject {
            fun toByteValue(): Byte = value
        }

        data class Text(
            private val value: String,
        ) : ValueObject {
            fun toStringValue(): String = value
        }
    }

    class Release(
        id: Id,
        val date: Date,
        val platformId: Platform.Id,
    ) : DomainEntity<Release.Id>(id = id) {
        data class Id(
            private val value: Long,
        ) : ValueObject {
            fun toLongValue(): Long = value
        }

        data class Date(
            private val value: LocalDate,
        ) : ValueObject {
            fun toLocalDateValue(): LocalDate = value
        }
    }

    companion object {
        @Throws(DomainException.GameAlreadyExistsException::class)
        fun addGame(
            gameIdGenerator: GameIdGenerator,
            gameAlreadyExists: GameAlreadyExists,
            name: Name,
            description: Description,
        ): Game {
            if (gameAlreadyExists(name)) {
                throw DomainException.GameAlreadyExistsException(
                    "Game with name '${name.toStringValue()}' already exists!"
                )
            }

            val id = gameIdGenerator.generate()

            return Game(
                id = id,
                name = name,
                description = description,
                reviews = emptyList(),
                releases = emptyList(),
            )
        }

        fun restoreGame(
            id: Id,
            name: Name,
            description: Description,
            reviews: List<Review>,
            releases: List<Release>,
            removed: Boolean,
        ): Game {
            return Game(
                id = id,
                name = name,
                description = description,
                reviews = reviews,
                releases = releases
            ).apply {
                this.removed = removed
            }
        }
    }
}
