package au.com.steps.openweather_api

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.Hidden
import com.tngtech.jgiven.annotation.ProvidedScenarioState
import groovy.util.logging.Slf4j
import io.restassured.RestAssured
import io.restassured.response.Response
/**
 * Created by Anil Pratty on 21/10/2019
 *
 */
@Slf4j
class When extends Stage<When>{

    @ProvidedScenarioState
    Response response

    @Hidden
    When get_Response(){

        return self()
    }

    When I_POST_a_JSON_Request_with_$(@Hidden String baseUrl, @Hidden String uri, Object payload) {

        response = RestAssured.given()
                .baseUri(baseUrl)
                .relaxedHTTPSValidation()
                .log().all(true).contentType("application/json")
                .body(payload)
                .with().post(uri)

        return  self()

    }

    When I_send_a_GET_Request_with(@Hidden String baseUrl, @Hidden String uri) {

        response = RestAssured.given()
                .baseUri(baseUrl)
                .relaxedHTTPSValidation()
                .log().all(true).contentType("application/json")
                .with().get(uri)

        return  self()
    }

    When I_send_a_DELETE_Request_with(@Hidden String baseUrl, @Hidden String uri) {

        response = RestAssured.given()
                .baseUri(baseUrl)
                .relaxedHTTPSValidation()
                .log().all(true).contentType("application/json")
                .with().delete(uri)

        return  self()
    }

    When I_print_the_JSON_Response() {

       response.body().prettyPrint()

        self()

    }

}