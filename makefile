all: compile run

compile:
	javac flight/FlightSearch.java
	javac flight/FlightSearchTest.java

run:
	java flight.FlightSearchTest

clean:
	rm -f flight/*.class 