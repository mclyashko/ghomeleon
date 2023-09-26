package ru.mirea.ghomeleon.metricslogger

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Component
import ru.mirea.ghomeleon.domain.common.util.metric.DomainMetricLogger

@Component
class DomainMetricLoggerImpl(
    private val meterRegistry: MeterRegistry
) : DomainMetricLogger {
    override fun registerCounter(name: String) {
        meterRegistry
            .counter(name)
            .increment()
    }
}
