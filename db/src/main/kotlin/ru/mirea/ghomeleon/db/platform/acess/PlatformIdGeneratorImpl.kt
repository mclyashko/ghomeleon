package ru.mirea.ghomeleon.db.platform.acess

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mirea.ghomeleon.domain.platform.Platform
import ru.mirea.ghomeleon.domain.platform.declaration.PlatformIdGenerator
import javax.sql.DataSource

@Repository
class PlatformIdGeneratorImpl(
    dataSource: DataSource,
) : PlatformIdGenerator {
    private val template = JdbcTemplate(dataSource)

    override fun generate(): Platform.Id {
        val id = template.queryForObject("select nextval('platform_id_seq');", Long::class.java)!!
        return Platform.Id(id)
    }
}
