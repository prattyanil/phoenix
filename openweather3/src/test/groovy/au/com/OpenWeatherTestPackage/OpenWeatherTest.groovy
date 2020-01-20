package au.com.OpenWeatherTestPackage

import au.com.steps.openweather_api.Given
import au.com.steps.openweather_api.Then
import au.com.steps.openweather_api.When
import com.tngtech.jgiven.spock.ScenarioSpec
import groovy.util.logging.Slf4j
import spock.lang.Shared
import spock.lang.Unroll
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.is

/**
 * Created by Anil Pratty on 21/10/2019
 *
 * Purpose: To test basic API endpoints of openweather app
 *
 */
@Slf4j
class OpenWeatherAPITest extends ScenarioSpec<Given, When, Then> {

    @Shared DEMO_TEST001_ID , DEMO_TEST002_ID, myStationList = []
    @Shared APPID = "446653a2d7058513f24b4a74fc95660f"
    @Shared baseURL = "http://api.openweathermap.org/data/3.0"

    @Unroll("Running openweather #external_id")
    def "open weather API Test 01 - invalid APPID"(){

        given().I_have_sent_a_request_to_the_openweather_API()

        def req  = [
                external_id : external_id,
                name: name,
                latitude: latitude,
                longitude: longitude,
                altitude: altitude
        ]

        when().I_POST_a_JSON_Request_with_$(baseURL, uriPath, req)
                .and()
                .I_print_the_JSON_Response()

        def cod = when().response.jsonPath().get("cod")
        def message = when().response.jsonPath().get("message")

        then().The_Assertion_Should_Pass_$(cod.toString(), cod, is(equalTo(expectedAPIresponse)))
        then().The_Assertion_Should_Pass_$(message.toString(), message, is(equalTo("Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.")))

        where:

        ID | external_id   |  name                           | latitude | longitude| altitude| uriPath    | expectedAPIresponse
        0  | 'DEMO_Test'   | 'Station Registration key test' | 22.22    |  -122.42 | 333     | "/stations"| 401
    }

    @Unroll()
    def "open weather API Test 02 - New Station Registration"(){

        given().I_have_sent_a_request_to_the_openweather_API()

        def req  = [
                external_id : external_id,
                name: name,
                latitude: latitude,
                longitude: longitude,
                altitude: altitude
        ]

        when().I_POST_a_JSON_Request_with_$(baseURL, uriPath, req)
                .and()
                .I_print_the_JSON_Response()

        def res_external_id = when().response.jsonPath().get("external_id")

        if (res_external_id == 'DEMO_TEST001') {DEMO_TEST001_ID = when().response.jsonPath().get("ID")
        }else if(res_external_id == 'DEMO_TEST002'){DEMO_TEST002_ID = when().response.jsonPath().get("ID")}


        then().API_Response_should_be_$(is(equalTo(expectedAPIresponse)))

        where:

        ID | external_id   |  name                           | latitude | longitude| altitude| uriPath                    | expectedAPIresponse
        2  | 'DEMO_TEST001'| 'Team Demo Test Station 001'    | 33.33    |  -122.43 | 222     | "/stations?APPID=" + APPID | 201
        1  | 'DEMO_TEST002'| 'Team Demo Test Station 002'    | 44.44    |  -122.44 | 111     | "/stations?APPID=" + APPID | 201
    }


    @Unroll()
    def "open weather API Test 03 -Fetch Registered Stations"(){

        given().I_have_sent_a_request_to_the_openweather_API()

        when().I_send_a_GET_Request_with(baseURL, uriPath)
                .and()
                .I_print_the_JSON_Response()

        def res_external_id = when().response.jsonPath().get("external_id")
        def res_name = when().response.jsonPath().get("name")
        def res_latitude = when().response.jsonPath().get("latitude")
        def res_longitude = when().response.jsonPath().get("longitude")
        def res_altitude = when().response.jsonPath().get("altitude")

        then().The_Assertion_Should_Pass_$(res_external_id.toString(), res_external_id, is(equalTo(external_id)))
        then().The_Assertion_Should_Pass_$(res_name.toString(), res_name, is(equalTo(name)))
        then().The_Assertion_Should_Pass_$(res_latitude.toString(), res_latitude.toString(), is(equalTo(latitude.toString())))
        then().The_Assertion_Should_Pass_$(res_longitude.toString(), res_longitude.toString(), is(equalTo(longitude.toString())))
        then().The_Assertion_Should_Pass_$(res_altitude.toString(), res_altitude.toString(), is(equalTo(altitude.toString())))

        where:

        ID |external_id   |  name                           | latitude | longitude| altitude| uriPath
        2  |'DEMO_TEST001'| 'Team Demo Test Station 001'    | 33.33    |  -122.43 | 222     | "/stations/"+ DEMO_TEST001_ID + "?APPID=" + APPID
        1  |'DEMO_TEST002'| 'Team Demo Test Station 002'    | 44.44    |  -122.44 | 111     | "/stations/"+ DEMO_TEST002_ID + "?APPID=" + APPID
    }

    @Unroll()
    def "clean up"() {

        given().I_have_sent_a_request_to_the_openweather_API()

        when().I_send_a_DELETE_Request_with(baseURL,uriPath)

        def resp = when().response.getStatusCode()
        then().The_Assertion_Should_Pass_$(resp.toString(), resp.toString() , is(equalTo("204")))

        where:

        ID |  name                           | uriPath
        2  | 'Team Demo Test Station 001'    | "/stations/"+ DEMO_TEST001_ID + "?APPID=" + APPID
        1  | 'Team Demo Test Station 002'    | "/stations/"+ DEMO_TEST002_ID + "?APPID=" + APPID
      }
}
