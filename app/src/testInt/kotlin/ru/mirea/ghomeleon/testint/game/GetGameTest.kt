package ru.mirea.ghomeleon.testint.game

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import ru.mirea.ghomeleon.rest.game.dto.response.GameResponse
import ru.mirea.ghomeleon.testint.BaseIntTest

class GetGameTest : BaseIntTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jacksonObjectMapper: ObjectMapper

    @Test
    fun shouldReturnAllGamesFromMigration() {
        val gameListAsString = mockMvc
            .get("/api/game") {
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val gameList = jacksonObjectMapper
            .readValue<List<GameResponse>>(gameListAsString)

        Assertions.assertAll(
            { Assertions.assertEquals(5, gameList.size) },
            { Assertions.assertEquals("New Super Mario Bros.", gameList.component1().name) },
            { Assertions.assertEquals("Disco Elysium", gameList.component2().name) },
            { Assertions.assertEquals("Atomic Heart", gameList.component3().name) },
            { Assertions.assertEquals("Demolition Racer: No Exit", gameList.component4().name) },
            { Assertions.assertEquals("Cybermorph", gameList.component5().name) },
        )
    }

    @Test
    fun shouldReturnFirstGameFromMigrationById() {
        val gameAsString = mockMvc
            .get("/api/game/id") {
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

        val game = jacksonObjectMapper
            .readValue<GameResponse>(gameAsString)

        Assertions.assertEquals("New Super Mario Bros.", game.name)
    }

    @Test
    fun shouldReturnFirstGameFromMigrationByName() {
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

        Assertions.assertEquals("New Super Mario Bros.", game.name)
    }
}
