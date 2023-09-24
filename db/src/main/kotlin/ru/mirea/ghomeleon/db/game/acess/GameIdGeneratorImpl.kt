package ru.mirea.ghomeleon.db.game.acess

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.game.declaration.GameIdGenerator
import javax.sql.DataSource

@Repository
class GameIdGeneratorImpl(
    dataSource: DataSource,
) : GameIdGenerator {
    private val template = JdbcTemplate(dataSource)

    override fun generate(): Game.Id {
        val id = template.queryForObject("select nextval('game_id_seq');", Long::class.java)!!
        return Game.Id(id)
    }
}
