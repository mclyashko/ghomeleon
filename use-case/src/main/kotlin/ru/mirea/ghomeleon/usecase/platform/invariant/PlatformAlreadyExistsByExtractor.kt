package ru.mirea.ghomeleon.usecase.platform.invariant

import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.domain.platform.declaration.PlatformAlreadyExists
import ru.mirea.ghomeleon.usecase.platform.declaration.acess.PlatformExtractor

@Component
class PlatformAlreadyExistsByExtractor(
    private val platformExtractor: PlatformExtractor,
) : PlatformAlreadyExists {
    override fun invoke(name: Platform.Name): Boolean {
        val platform = platformExtractor.getByName(name)
        return platform != null
    }
}
