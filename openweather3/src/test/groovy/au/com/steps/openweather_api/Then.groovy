package au.com.steps.openweather_api

import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.Hidden
import com.tngtech.jgiven.annotation.ProvidedScenarioState
import groovy.util.logging.Slf4j
import org.hamcrest.Matcher

import static org.junit.Assert.assertThat
/**
 * Created by Anil Pratty on 21/10/2019
 *
 */
@Slf4j
class Then extends Stage<Then> {

    @ProvidedScenarioState
    int success = 201


    @Hidden
    Then API_Response_should_be_$(Matcher respCode) {

        assertThat(success, respCode)

        return self()

    }


        Then The_Assertion_Should_Pass_$(String response, @Hidden Object actual, @Hidden Matcher expected) {
        assertThat(response, actual, expected)
        return self()
    }


}