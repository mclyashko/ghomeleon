package ru.mirea.ghomeleon.usecase.platform.invariant

import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.domain.common.util.metric.MetricLogger
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.domain.platform.declaration.PlatformAlreadyExists
import ru.mirea.ghomeleon.usecase.platform.declaration.acess.PlatformExtractor

@Component
class PlatformAlreadyExistsByExtractor(
    private val platformExtractor: PlatformExtractor,
    private val metricLogger: MetricLogger,
) : PlatformAlreadyExists {
    override fun invoke(name: Platform.Name): Boolean {
        val platform = platformExtractor.getByName(name)
        platform?.let {
            metricLogger.registerCounter(
                name = MetricLogger.PLATFORM_ALREADY_EXISTS_VALIDATION_FAILED
            )
            return true
        }
        return false
    }
}
