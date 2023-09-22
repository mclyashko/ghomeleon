package ru.mirea.ghomeleon.domain.platform

import ru.mirea.ghomeleon.domain.common.design.entity.AggregateRoot
import ru.mirea.ghomeleon.domain.common.design.entity.ValueObject
import ru.mirea.ghomeleon.domain.common.design.exception.DomainException
import ru.mirea.ghomeleon.domain.platform.declaration.PlatformAlreadyExists
import ru.mirea.ghomeleon.domain.platform.declaration.PlatformIdGenerator
import java.time.LocalDate

class Platform internal constructor(
    id: Id,
    val name: Name,
    val releaseDate: ReleaseDate,
    val manufacturer: Manufacturer,
) : AggregateRoot<Platform.Id>(id = id) {
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

    data class ReleaseDate(
        private val value: LocalDate
    ) : ValueObject {
        fun toLocalDateValue(): LocalDate = value
    }

    enum class Manufacturer(
        private val value: String
    ) : ValueObject {
        NINTENDO("Nintendo"),
        SONY("Sony"),
        SEGA("Sega"),
        MICROSOFT("Microsoft"),
        ATARI("Atari"),
        ;

        fun toStringValue(): String = value

        companion object {
            private val mapByValue: Map<String, Manufacturer> =
                Manufacturer.values().associateBy { manufacturer ->
                     manufacturer.toStringValue()
                }

            fun from(name: String): Manufacturer =
                mapByValue[name]
                    ?: throw DomainException.UnknownManufacturerNameException(
                        "Manufacturer with $name is unknown!"
                    )
        }
    }

    companion object {
        fun addPlatform(
            platformIdGenerator: PlatformIdGenerator,
            platformAlreadyExists: PlatformAlreadyExists,
            name: Name,
            releaseDate: ReleaseDate,
            manufacturer: Manufacturer,
        ): Platform {
            if (platformAlreadyExists(name)) {
                throw DomainException.PlatformAlreadyExistsException(
                    "Platform with ${name.toStringValue()} already exists!"
                )
            }

            val id = platformIdGenerator.generate()

            return Platform(
                id = id,
                name = name,
                releaseDate = releaseDate,
                manufacturer = manufacturer,
            )
        }
    }
}