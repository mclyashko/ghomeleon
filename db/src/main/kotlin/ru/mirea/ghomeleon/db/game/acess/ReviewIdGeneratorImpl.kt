package ru.mirea.ghomeleon.db.game.acess

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.mirea.ghomeleon.domain.game.Game
import ru.mirea.ghomeleon.domain.game.declaration.ReviewIdGenerator
import javax.sql.DataSource

@Repository
class ReviewIdGeneratorImpl(
    dataSource: DataSource,
) : ReviewIdGenerator {
    private val template = JdbcTemplate(dataSource)

    override fun generate(): Game.Review.Id {
        val id = template.queryForObject("select nextval('game_review_id_seq');", Long::class.java)!!
        return Game.Review.Id(id)
    }
}
