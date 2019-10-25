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

        when: "Populate Estimation Details"
        "Populate Estimation Details"(your_income, your_other_income, living_expenses, current_homeloan,
                other_loan, other_commitments, creditcard_limits)

        then: "Dashboard page title should contain 'Providers' "
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

        when: "click start over button"
        "Populate Estimation Details"("80000", "10000", "500", "0", "100", "0", "10000")

        then:
        "start over test"()== true


    }

}
