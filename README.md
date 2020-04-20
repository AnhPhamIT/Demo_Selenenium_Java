How to run test
1. Import the project to Eclipse which installed Maven and TestNG
2. Import Run Configurations
	2.1 File -> Import -> Run/Debug -> Launch Configurations
	2.2 Select file "Test1.launch" in github repo
	2.3 on "Run Configurations" window, if JRE tab have Red mark, please select jdk 1.8.x
3. Go to "Run Configurations" -> Maven Build -> Test1 -> Run
4. Report is under target/surefile-reports/index.html

Details
- Framework: TestNG.
- Build Tool: Maven.
- All xPath: goBear/Selectors
- All action: goBear/Pages
- Testscript: goBear/Tests
- Test Suite: suites/DEMO.xml

Note: Please update chromderiver under /selenium_driver/chromedriver.exe according to your Chrome version

