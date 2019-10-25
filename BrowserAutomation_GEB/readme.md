Browser Automation Scenario :

Project Snapshot :

Our developers have finished coding a borrowing calculator and would like to make sure that the calculator continues to work as they make other changes
to the page. They have asked you to build automated tests covering various use cases to verify that the calculator is working properly. They intend to run
these tests as part of continuous testing.
The current working production page is here: https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/
Exercise
Develop the following three tests:
1. A person with the following details:
-Single,
-0 dependants,
-buying a home to live in,
-with income of $80,000,
-other income $10,000,
-living expenses $500,
-current home loan repayments $0,
-other loan repayments $100,
-other commitments $0
-and total credit card limits $10,000
-has a borrowing estimate of $479,000.

2. Clicking the ‘start over’ button clears the form

Implementation Requirements
You will need to create a project in either JavaScript or Java with minimal dependencies
The project should use Cucumber (gherkin - BDD) with an open source UI test automation runtime of your choice (e.g. Puppeteer, WebdriverIO)
The tests should target a browser of your choice (e.g. Chrome, Firefox)
The tests should run through Command Line Interface in order to support CI/CD
All tests should pass and produce new results every time they are run
The solution should output results to JSON or XML and be able to generate HTML reports from the output file.


**Solution Design and Framework Structure**

This Maven project has been developed to address the above problem statement using Groovy , Geb and Spock BDD framework

IDE Used : IntelliJ 

- All required gradle dependencies have been specified under build.gradle

- gebspockspecfiles holds the main Test class with individual tests

- gebpomfiles holds the page definitions under the Page Object Model

- Spock Framework has been used to achive Data driven test
  http://spockframework.org/spock/javadoc/1.0/spock/

- This maven project can be integrated with any CI / CD Pipeline and can be run using maven command line commands

- The spock framework generates HTML and JSON reports and can be found under >>build/spock-reports which can be further customized

- NOTE: The browser drivers needs to be placed under C:\PROJECT\DriverExe (part of BrowserAutomation_GEB project)



Scope of Improvements :

- Building a centralized properties file for config and variable definations
- Cleanup functions for deleting test registrations from DB 
- Parametrization and optiization of the helper functions


 




