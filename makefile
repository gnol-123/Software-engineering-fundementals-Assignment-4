all: compile run

compile:
	javac flight/FlightSearch.java
	javac flight/FlightSearchTestRunner.java

run:
	java flight.FlightSearchTestRunner

clean:
	rm -f flight/*.class