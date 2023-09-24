package ru.mirea.ghomeleon.domain.game

import ru.mirea.ghomeleon.domain.common.design.entity.AggregateRoot
import ru.mirea.ghomeleon.domain.common.design.entity.DomainEntity
import ru.mirea.ghomeleon.domain.common.design.entity.ValueObject
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.game.declaration.GameAlreadyExists
import ru.mirea.ghomeleon.domain.game.declaration.GameIdGenerator
import ru.mirea.ghomeleon.domain.game.declaration.ReleaseIdGenerator
import ru.mirea.ghomeleon.domain.game.declaration.ReviewIdGenerator
import ru.mirea.ghomeleon.domain.platform.Platform
import java.time.LocalDate

class Game internal constructor(
    id: Id,
    isNew: Boolean,
    val name: Name,
    val description: Description,
    val reviews: List<Review>,
    val releases: List<Release>,
) : AggregateRoot<Game.Id>(id = id, isNew = isNew) {

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

    class Review internal constructor(
        id: Id,
        isNew: Boolean,
        val mark: Mark,
        val text: Text,
    ) : DomainEntity<Review.Id>(id = id, isNew = isNew) {
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

        companion object {
            fun addReview(
                reviewIdGenerator: ReviewIdGenerator,
                mark: Mark,
                text: Text,
            ): Review {
                val id = reviewIdGenerator.generate()

                return Review(
                    id = id,
                    isNew = true,
                    mark = mark,
                    text = text,
                )
            }

            fun restoreReview(
                id: Id,
                mark: Mark,
                text: Text,
            ): Review {
                return Review(
                    id = id,
                    isNew = false,
                    mark = mark,
                    text = text,
                )
            }
        }

        override fun markPersistedCascade() {
            isNew = false
        }
    }

    class Release internal constructor(
        id: Id,
        isNew: Boolean,
        val date: Date,
        val platformId: Platform.Id,
    ) : DomainEntity<Release.Id>(id = id, isNew = isNew) {
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

        companion object {
            fun addRelease(
                releaseIdGenerator: ReleaseIdGenerator,
                date: Date,
                platformId: Platform.Id,
            ): Release {
                val id = releaseIdGenerator.generate()

                return Release(
                    id = id,
                    isNew = true,
                    date = date,
                    platformId = platformId,
                )
            }

            fun restoreRelease(
                id: Id,
                date: Date,
                platformId: Platform.Id,
            ): Release {
                return Release(
                    id = id,
                    isNew = false,
                    date = date,
                    platformId = platformId,
                )
            }
        }

        override fun markPersistedCascade() {
            isNew = false
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
                isNew = true,
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
                isNew = false,
                name = name,
                description = description,
                reviews = reviews,
                releases = releases
            ).apply {
                this.removed = removed
            }
        }
    }

    override fun markPersistedCascade() {
        isNew = false
        reviews.map { review ->
            review.markPersistedCascade()
        }
        releases.map { release ->
            release.markPersistedCascade()
        }
    }
}
