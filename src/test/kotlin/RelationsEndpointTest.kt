import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.response.Response
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class RelationsEndpointTest {
    @Test
    fun `creates relation`() {
        createRelation()
            .then()
            .statusCode(204)

        getAllRelations()
            .then()
            .statusCode(200)
            .body(
                "$.size()", `is`(1),
                "[0].name", `is`("Hello")
            )
    }

    @Test
    internal fun `deletes relation`() {
        createRelation()

        val relationId = getAllRelations().body.jsonPath()
            .getString("[0].id")

        deleteRelation(relationId)
            .then()
            .statusCode(204)

        getAllRelations()
            .then()
            .statusCode(200)
            .body("$.size()", `is`(0))
    }

    @Test
    internal fun `does not delete relation twice`() {
        createRelation()

        val relationId = getAllRelations().body.jsonPath()
            .getString("[0].id")

        deleteRelation(relationId)
            .then()
            .statusCode(204)

        deleteRelation(relationId)
            .then()
            .statusCode(404)
    }

    fun createRelation(): Response =
        given()
            .formParam("name", "Hello")
            .post("/relations")

    fun getAllRelations(): Response =
        given()
            .get("/relations")

    fun deleteRelation(relationId: String): Response =
        given()
            .delete("/relations/$relationId")
}