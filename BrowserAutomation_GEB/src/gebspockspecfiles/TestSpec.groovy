package gebspockspecfiles

import geb.spock.GebSpec
import gebpomfiles.BorrowCalculatorPage
import spock.lang.Unroll

class TestSpec extends GebSpec
{


    @Unroll
    def "Borrowing Capacity Calculator Test"()
    {

        given: "User is on Borrowing Capacity Calculator Page"
        to BorrowCalculatorPage

        when: "Estimation details are populated"
        "Populate Estimation Details"(app_type, num_dependants, your_income, your_other_income, living_expenses, current_homeloan,
                other_loan, other_commitments, creditcard_limits)

        and: "Borrowing capacity is calculated"
        "calculate borrowing capacity"()

        then: "Calculated borrowing estimate should be correct"
        assert "Fetch borrowing estimate"() == expected_borrowing_estimate

        where:

        ID | app_type| num_dependants| your_income| your_other_income| living_expenses| current_homeloan| other_loan| other_commitments| creditcard_limits | expected_borrowing_estimate
        0  | "Single"| "0"           |'80000'     | '10000'          |"500"           | "0"             | "100"     | "0"              |"10000"            | "\$470,000"
        1  | "Joint" | "2"           |'70000'     | '15000'          |"900"           | "0"             | "500"     | "0"              |"20000"            | "\$91,000"

    }

    def "Start over test"()
    {
        given: "User is on Borrowing Capacity Calculator Page"
        to BorrowCalculatorPage

        when: "The estimation details are populated "
        "Populate Estimation Details"("Joint", "1","80000", "10000", "500", "0", "100", "0", "10000")
        "calculate borrowing capacity"()

        and: "The start over button is clicked"

        then: "All data fields should be reset"
        assert "start over test"()== true

    }

}

















