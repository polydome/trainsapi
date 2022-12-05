import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.junit.jupiter.api.Test

@QuarkusTest
class DestinationsEndpointTest {
    @Test
    internal fun `adds destination to existing relation`() {
        val relationId = Given {
            formParam("name", "Hello")
        } When {
            post("relations")
        } Extract {
            body().jsonPath().getString("$[0].id")
            print(asPrettyString())
        }

        Given {
            formParam("station", "Warszawa Wschodnia")
            formParam("platform", 1)
            formParam("track", 1)
            formParam("arrivalTime", 40)
        } When {
            post("relations/$relationId/destinations")
        } Then {
            statusCode(204)
        }

//        addDestination(
//            relationId = relationId,
//            station = "Warszawa Wschodnia",
//            platform = 1,
//            track = 4,
//            arrivalTime = 40
//        ).then()
//            .statusCode(204)

        When {
            get("relations")
//        } Then {
//            body("[0].destinations.stationName", equalTo("Warszawa Wschodnia"))
        } Extract {
            print(asPrettyString())
        }

//        getAllRelations()
//            .then()
//            .body("[0].destinations.stationName", equalTo("Warszawa Wschodnia"))
//            .body("[0].destinations.platform", equalTo(1))
//            .body("[0].destinations.track", equalTo(4))
//            .body("[0].destinations.arrivalTime", equalTo(40))

    }
}

fun addDestination(
    relationId: String,
    station: String,
    platform: Int,
    track: Int,
    arrivalTime: Int? = null,
    departureTime: Int? = null
): Response {
    var builder = given()
        .formParam("station", station)
        .formParam("platform", platform)
        .formParam("track", track)

    arrivalTime?.let {
        builder = builder.formParam("arrivalTime", it)
    }

    departureTime?.let {
        builder = builder.formParam("departureTime", it)
    }

    return builder
        .post("relations/$relationId/destinations")
}