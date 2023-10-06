package ru.mirea.ghomeleon.testint.platform

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import ru.mirea.ghomeleon.rest.platform.dto.response.PlatformResponse
import ru.mirea.ghomeleon.testint.BaseIntTest

class GetPlatformTest : BaseIntTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jacksonObjectMapper: ObjectMapper

    @Test
    fun shouldReturnAllPlatformsFromMigration() {
        val platformListAsString = mockMvc
            .get("/api/platform") {
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val platformList = jacksonObjectMapper
            .readValue<List<PlatformResponse>>(platformListAsString)

        Assertions.assertAll(
            { Assertions.assertEquals(6, platformList.size) },
            { Assertions.assertEquals("Nintendo DS", platformList[0].name) },
            { Assertions.assertEquals("Nintendo Switch", platformList[1].name) },
            { Assertions.assertEquals("PlayStation 5", platformList[2].name) },
            { Assertions.assertEquals("Dreamcast", platformList[3].name) },
            { Assertions.assertEquals("Xbox Series X", platformList[4].name) },
            { Assertions.assertEquals("Atari Jaguar", platformList[5].name) },
        )
    }

    @Test
    fun shouldReturnFirstPlatformFromMigrationById() {
        val platformAsString = mockMvc
            .get("/api/platform/id") {
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

        val platform = jacksonObjectMapper
            .readValue<PlatformResponse>(platformAsString)

        Assertions.assertEquals("Nintendo DS", platform.name)
    }

    @Test
    fun shouldReturnFirstPlatformFromMigrationByName() {
        val platformAsString = mockMvc
            .get("/api/platform/name") {
                param("name", "Nintendo DS")
                accept = MediaType.APPLICATION_JSON
            }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }
            .andReturn()
            .response
            .contentAsString

        val platform = jacksonObjectMapper
            .readValue<PlatformResponse>(platformAsString)

        Assertions.assertEquals("Nintendo DS", platform.name)
    }
}
