package ru.mirea.ghomeleon.testint.game

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.put
import ru.mirea.ghomeleon.rest.game.dto.request.UpdateGameRequest
import ru.mirea.ghomeleon.rest.game.dto.response.GameResponse
import ru.mirea.ghomeleon.testint.BaseIntTest

class UpdateGameTest : BaseIntTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jacksonObjectMapper: ObjectMapper

    @Test
    fun shouldUpdateFirstGameFromMigration() {
        val updateGameRequest = UpdateGameRequest(
            name = "New Super Mario Bros.",
            description = "Cool Game",
        )

        val updatedGameAsString = mockMvc
            .put("/api/game/id") {
                param("id", "1")
                contentType = MediaType.APPLICATION_JSON
                content = jacksonObjectMapper.writeValueAsString(updateGameRequest)
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val updatedGame = jacksonObjectMapper
            .readValue<GameResponse>(updatedGameAsString)

        Assertions.assertAll(
            { Assertions.assertEquals(updateGameRequest.name, updatedGame.name) },
            { Assertions.assertEquals(updateGameRequest.description, updatedGame.description) },
        )
    }

    @Test
    fun shouldMarkFirstGameFromMigrationAsRemoved() {
        val gameAsString = mockMvc
            .get("/api/game/name") {
                param("name", "New Super Mario Bros.")
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val game = jacksonObjectMapper
            .readValue<GameResponse>(gameAsString)

        val removedGameAsString = mockMvc
            .delete("/api/game/id") {
                param("id", "1")
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val removedGame = jacksonObjectMapper
            .readValue<GameResponse>(removedGameAsString)

        Assertions.assertAll(
            { Assertions.assertEquals(game.id, removedGame.id) },
            { Assertions.assertFalse(game.removed) },
            { Assertions.assertTrue(removedGame.removed) },
        )
    }
}
