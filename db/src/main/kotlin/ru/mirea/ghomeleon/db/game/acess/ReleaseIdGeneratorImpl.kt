package ru.mirea.ghomeleon.db.game.acess

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.game.declaration.ReleaseIdGenerator
import javax.sql.DataSource

@Repository
class ReleaseIdGeneratorImpl(
    dataSource: DataSource,
) : ReleaseIdGenerator {
    private val template = JdbcTemplate(dataSource)

    override fun generate(): Game.Release.Id {
        val id = template
            .queryForObject("select nextval('game_release_id_seq');", Long::class.java)!!
        return Game.Release.Id(id)
    }
}
