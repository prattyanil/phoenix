package gebspockspecfiles

import geb.spock.GebSpec
import gebpomfiles.BorrowCalculatorPage
import spock.lang.Unroll
import org.hamcrest.Matcher
import static org.junit.Assert.assertThat

class TestSpec extends GebSpec
{


    @Unroll
    def "Borrowing Capacity Calculator Test"()
    {

        given: "User is on Borrowing Capacity Calculator"
        to BorrowCalculatorPage

        when: "Estimation details are populated"
        "Populate Estimation Details"(your_income, your_other_income, living_expenses, current_homeloan,
                other_loan, other_commitments, creditcard_limits)

        and: "borrowing capacity is calculated"
        "calculate borrowing capacity"()

        then: "Calculated borrowing estimate should be correct"
        "Fetch borrowing estimate"() == expected_borrowing_estimate

        where:

        ID | your_income| your_other_income| living_expenses| current_homeloan| other_loan| other_commitments| creditcard_limits | expected_borrowing_estimate
        0  | '80000'    | '10000'          |"500"           | "0"             | "100"     | "0"              |"10000"            | "\$470,000"
        1  | '70000'    | '15000'          |"900"           | "0"             | "500"     | "0"              |"20000"            | "\$316,000"

    }

    def "start over test"()
    {
        given: "User is on Borrowing Capacity Calculator"
        to BorrowCalculatorPage

        when: "The estimation details are populated "
        "Populate Estimation Details"("80000", "10000", "500", "0", "100", "0", "10000")
        "calculate borrowing capacity"()

        and: "The start over button is clicked"

        then: "All data fields should be reset"
        "start over test"()== true

    }

}
