package ru.mirea.ghomeleon.db.platform.acess

import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ru.mirea.ghomeleon.db.platform.entity.extension.toPlatformEntity
import ru.mirea.ghomeleon.db.platform.repository.PlatformRepository
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.usecase.platform.declaration.acess.PlatformPersister

@Repository
class PlatformPersisterImpl(
    private val platformRepository: PlatformRepository,
) : PlatformPersister {

    @Transactional
    override fun save(platform: Platform) {
        (platform to platform.toPlatformEntity())
            .let { (platform, platformEntity) ->
                if (platform.isNew()) {
                    platformEntity.apply { isNew = true }
                }
                platformRepository.save(platformEntity)
            }

        platform.markPersistedCascade()
    }
}
