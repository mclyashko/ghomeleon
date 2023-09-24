package ru.mirea.ghomeleon.db.platform.acess

import org.springframework.stereotype.Repository
import ru.mirea.ghomeleon.db.platform.entity.PlatformEntity
import ru.mirea.ghomeleon.db.platform.repository.PlatformRepository
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.usecase.platform.declaration.acess.PlatformExtractor
import kotlin.jvm.optionals.getOrNull

@Repository
class PlatformExtractorImpl(
    private val platformRepository: PlatformRepository,
) : PlatformExtractor {
    override fun getById(id: Platform.Id): Platform? {
        val platformEntity = platformRepository
            .findById(id.toLongValue())
            .getOrNull()

        return platformEntity?.let {
            constructDomain(it)
        }
    }

    override fun getByName(name: Platform.Name): Platform? {
        val platformEntity = platformRepository
            .findByName(name.toStringValue())
            .getOrNull()

        return platformEntity?.let {
            constructDomain(it)
        }
    }

    override fun getAll(): List<Platform> {
        val platformEntities = platformRepository
            .findAll()

        return platformEntities.map {
            constructDomain(it)
        }
    }

    private fun constructDomain(platformEntity: PlatformEntity): Platform {
        return Platform.restorePlatform(
            id = Platform.Id(platformEntity.id),
            name = Platform.Name(platformEntity.name),
            releaseDate = Platform.ReleaseDate(platformEntity.releaseDate),
            manufacturer = Platform.Manufacturer.from(platformEntity.manufacturer),
            removed = platformEntity.removed,
        )
    }
}
