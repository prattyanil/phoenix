package gebpomfiles

import geb.Page
import org.hamcrest.Matcher

import static org.junit.Assert.assertThat


class BorrowCalculatorPage extends Page
{
    static url = "/personal/home-loans/calculators-tools/much-borrow/"
    boolean flag = true

    static at = { title.contains("borrowing") }

    static content = {
        application_type_single(wait: true) { $("label[for='application_type_single']")  }
        application_type_joint(wait: true) { $("label[for='application_type_joint']")  }
        number_of_dependents(wait: true) { $("select[title='Number of dependants']")  }
        home_to_live_in(wait: true) { $("label[for='borrow_type_home']")  }
        residential_investment(wait: true) { $("label[for='borrow_type_investment']")  }

        your_income_textbox(wait: true) {$("input[aria-describedby='q2q1i1 q2q1i2']")}
        your_other_income_textbox(wait: true) {$("input[aria-describedby='q2q2i1 q2q2i2']")}

        living_expenses_textbox(wait: true) {$("input[aria-describedby='q3q1i1 q3q1i2']")}
        current_homeloan_repayments_textbox(wait: true) {$("input[aria-describedby='q3q2i1 q3q2i2']")}
        other_loan_repayments_textbox(wait: true) {$("input[aria-describedby='q3q3i1 q3q3i2']")}
        other_commitments_textbox(wait: true) {$("input[aria-describedby='q3q4i1 q3q4i2']")}
        total_creditcard_limits_textbox(wait: true) {$("input[aria-describedby='q3q5i1']")}

        calculate_borrow_capacity_button(wait: true) {$("button[class='btn btn--action btn--borrow__calculate']")}

        borrow_estimate(wait: true) {$("span[class='borrow__result__text__amount']")}

        start_over(wait: true) {$("button", text:"Start over")}

    }

    def "Populate Estimation Details"(your_income, your_other_income, living_expenses, current_homeloan, other_loan, other_commitments, creditcard_limits)
    {
        application_type_single.click()
        //number_of_dependents.value("0")
        home_to_live_in.click()

        your_income_textbox << your_income
        your_other_income_textbox << your_other_income

        living_expenses_textbox << living_expenses
        current_homeloan_repayments_textbox << current_homeloan
        other_loan_repayments_textbox << other_loan
        other_commitments_textbox << other_commitments
        total_creditcard_limits_textbox << creditcard_limits

        calculate_borrow_capacity_button.click()
        sleep(2000)


    }

    def "Fetch estimation details"(){

        def myList = []
        myList.add(your_income_textbox.text())
        myList.add(your_other_income_textbox.text())
        myList.add(living_expenses_textbox.text())
        myList.add(current_homeloan_repayments_textbox.text())
        myList.add(other_loan_repayments_textbox.text())
        myList.add(other_commitments_textbox.text())
        myList.add(total_creditcard_limits_textbox.text())

        return myList
    }

    def "Fetch borrowing estimate"()
    {
        def borrowing_estimate = borrow_estimate.text()
        return borrowing_estimate
    }

    def "start over test"()
    {
        start_over.click()
        def res = "Fetch estimation details"()
        for (item in res) {
            if (item != "") {
                flag = false
                break
            }
        }
        return flag
    }

    def "Dashboard page title should match"()
    {
        return getBrowser().getTitle().contains("Home loan borrowing power calculator | ANZ")
    }

}
