# fraudDetect

#Getting Started

Prerequisites:

Java (version>=1.8)

Maven (version>=3.6.3)

#Build

1. Open a command console and cd into the fraudDetect folder.(to make sure you are in the correct folder, please use command "ls" to double check whether you can see these in your current directory: a "src" folder, a "pom.xml" file and a creditCardTransactions.csv file)

2. Run the below command:

mvn clean install

Or

mvn package


It will run all the test cases compile class files and build a jar file in target/ directory (jar name: fraudDetect-0.0.1-SNAPSHOT.jar)


3. If you want to run the rest cases again, they can be run with:

mvn test

4. Running the application

java -jar target/fraudDetect-0.0.1-SNAPSHOT.jar 100 ./creditCardTransactions.csv

If you want to test on another file, just replace the parameters in the tail:

java -jar target/fraudDetect-0.0.1-SNAPSHOT.jar newThreshold newFile




(Note: As my dev env is Mac platform, have not tested the real "CVS" file that exported from a Windows excel, so if some compatibility issues happened with a real CSV file exported from Microsoft Excel please copy the content into an txt file and try to run it again.)