# To run the Console
You need Java 11 on your machine.

If you have novidea-1.0-SNAPSHOT.jar in {YOUR_PATH}/pop_push-master/target, then:
- java -cp {YOUR_PATH}/pop_push-master/target/novidea-1.0-SNAPSHOT.jar eu.heisenbug.Console

If you don't have the jar, then:
- mvn clean package
- java -cp {YOUR_PATH}/pop_push-master/target/novidea-1.0-SNAPSHOT.jar eu.heisenbug.Console

Then you can go through the process of:
- selecting element type (Integer or String)
- choosing order strategy (i.e. pop min or max)
- pick product (QuickPop or QuickPush)
- infinitely pop or push elements.

# To run the tests
You need Maven in order to run the tests on your machine.

Go to the folder containing pom.xml:
- cd pop_push-master

Now you have various options, i.e.

Run all tests:
- mvn clean test

Or run a particular test:
- mvn clean test -Dtest=your.package.TestClassName
- mvn clean test -Dtest=your.package.TestClassName#particularMethod

Examples:
- mvn clean test -Dtest=eu.heisenbug.product.QuickPopMultiThreadingTest
- mvn clean test -Dtest=eu.heisenbug.product.QuickPushLogicTest#GIVEN_quickPushStringAtoZ_WHEN_popFromMultipleElementList_THEN_maxIsPopped


NB: Developed and tested on Ubuntu 20.04 with Java 11.0.16 and Apache Maven 3.6.3.
