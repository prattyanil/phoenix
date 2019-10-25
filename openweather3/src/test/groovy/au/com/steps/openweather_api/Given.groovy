package au.com.steps.openweather_api

import com.tngtech.jgiven.Stage
import groovy.util.logging.Slf4j

/**
 * Created by Anil Pratty on 21/10/2019
 *
 */
@Slf4j
class Given extends Stage<Given> {


    Given I_have_sent_a_request_to_the_openweather_API () {

        return self()
    }

}