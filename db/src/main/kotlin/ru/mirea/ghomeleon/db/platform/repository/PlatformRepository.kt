package ru.mirea.ghomeleon.db.platform.repository

import org.springframework.data.repository.CrudRepository
import ru.mirea.ghomeleon.db.platform.entity.PlatformEntity
import java.util.Optional

interface PlatformRepository : CrudRepository<PlatformEntity, Long> {
    fun findByName(name: String): Optional<PlatformEntity>
}
