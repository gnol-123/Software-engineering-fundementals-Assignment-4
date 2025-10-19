package flight;

public class FlightSearchTestRunner {
    
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    public static void main(String[] args) {
        System.out.println("=== Flight Search Test Results ===\n");
        
        testValidInput1();
        testValidInput2();
        testValidInput3();
        testValidInput4();
        testZeroPassengers();
        testTenPassengers();
        testChildrenInEmergencyRow();
        testChildrenInFirstClass();
        testInfantsInEmergencyRow();
        testInfantsInBusinessClass();
        testTwoChildrenPerAdultValid();
        testThreeChildrenPerAdultInvalid();
        testOneInfantPerAdultValid();
        testTwoInfantsPerAdultInvalid();
        testDepartureDateYesterday();
        testDepartureDateInPast();
        testValidLeapYear();
        testInvalidLeapYear();
        testReturnSameDateAsDepatureValid();
        testReturnBeforeDeparture();
        testValidSeatingClass();
        testInvalidSeatingClass();
        testEmergencyRowInFirstInvalid();
        testEmergencyRowInBusinessInvalid();
        testValidDifferentAirports();
        testSameAirportInvalid();
        
        System.out.println("\nTest Summary:");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Total Tests: " + (testsPassed + testsFailed));
    }
    
    private static void assertTrue(String testName, boolean condition) {
        if (condition) {
            System.out.println("PASS: " + testName);
            testsPassed++;
        } else {
            System.out.println("FAIL: " + testName);
            testsFailed++;
        }
    }
    
    private static void assertFalse(String testName, boolean condition) {
        if (!condition) {
            System.out.println("PASS: " + testName);
            testsPassed++;
        } else {
            System.out.println("FAIL: " + testName);
            testsFailed++;
        }
    }
    
    public static void testValidInput1() {
        FlightSearch search = new FlightSearch();
        assertTrue("testValidInput1", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "economy", 2, 3, 1));
    }

    public static void testValidInput2() {
        FlightSearch search = new FlightSearch();
        assertTrue("testValidInput2", search.runFlightSearch("20/12/2025", "lax", false, "25/12/2025", "cdg", "premium economy", 3, 4, 2));
    }

    public static void testValidInput3() {
        FlightSearch search = new FlightSearch();
        assertTrue("testValidInput3", search.runFlightSearch("15/11/2025", "del", false, "20/11/2025", "pvg", "economy", 4, 0, 3));
    }

    public static void testValidInput4() {
        FlightSearch search = new FlightSearch();
        assertTrue("testValidInput4", search.runFlightSearch("01/01/2026", "mel", true, "10/01/2026", "doh", "economy", 5, 0, 0));
    }

    public static void testZeroPassengers() {
        FlightSearch search = new FlightSearch();
        assertFalse("testZeroPassengers", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "economy", 0, 0, 0));
    }

    public static void testTenPassengers() {
        FlightSearch search = new FlightSearch();
        assertFalse("testTenPassengers", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "economy", 5, 3, 2));
    }

    public static void testChildrenInEmergencyRow() {
        FlightSearch search = new FlightSearch();
        assertFalse("testChildrenInEmergencyRow", search.runFlightSearch("25/10/2025", "syd", true, "05/11/2025", "mel", "economy", 2, 1, 0));
    }

    public static void testChildrenInFirstClass() {
        FlightSearch search = new FlightSearch();
        assertFalse("testChildrenInFirstClass", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "first", 2, 1, 0));
    }

    public static void testInfantsInEmergencyRow() {
        FlightSearch search = new FlightSearch();
        assertFalse("testInfantsInEmergencyRow", search.runFlightSearch("25/10/2025", "syd", true, "05/11/2025", "mel", "economy", 2, 0, 1));
    }

    public static void testInfantsInBusinessClass() {
        FlightSearch search = new FlightSearch();
        assertFalse("testInfantsInBusinessClass", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "business", 2, 0, 1));
    }

    public static void testTwoChildrenPerAdultValid() {
        FlightSearch search = new FlightSearch();
        assertTrue("testTwoChildrenPerAdultValid", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "economy", 1, 2, 0));
    }

    public static void testThreeChildrenPerAdultInvalid() {
        FlightSearch search = new FlightSearch();
        assertFalse("testThreeChildrenPerAdultInvalid", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "economy", 1, 3, 0));
    }

    public static void testOneInfantPerAdultValid() {
        FlightSearch search = new FlightSearch();
        assertTrue("testOneInfantPerAdultValid", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "economy", 1, 0, 1));
    }

    public static void testTwoInfantsPerAdultInvalid() {
        FlightSearch search = new FlightSearch();
        assertFalse("testTwoInfantsPerAdultInvalid", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "economy", 1, 0, 2));
    }

    public static void testDepartureDateYesterday() {
        FlightSearch search = new FlightSearch();
        assertFalse("testDepartureDateYesterday", search.runFlightSearch("18/10/2025", "syd", false, "20/10/2025", "mel", "economy", 2, 0, 0));
    }

    public static void testDepartureDateInPast() {
        FlightSearch search = new FlightSearch();
        assertFalse("testDepartureDateInPast", search.runFlightSearch("01/01/2025", "syd", false, "05/01/2025", "mel", "economy", 2, 0, 0));
    }

    public static void testValidLeapYear() {
        FlightSearch search = new FlightSearch();
        assertTrue("testValidLeapYear", search.runFlightSearch("29/02/2028", "syd", false, "05/03/2028", "mel", "economy", 2, 0, 0));
    }

    public static void testInvalidLeapYear() {
        FlightSearch search = new FlightSearch();
        assertFalse("testInvalidLeapYear", search.runFlightSearch("29/02/2025", "syd", false, "05/03/2025", "mel", "economy", 2, 0, 0));
    }

    public static void testReturnSameDateAsDepatureValid() {
        FlightSearch search = new FlightSearch();
        assertTrue("testReturnSameDateAsDepatureValid", search.runFlightSearch("25/10/2025", "syd", false, "25/10/2025", "mel", "economy", 2, 0, 0));
    }

    public static void testReturnBeforeDeparture() {
        FlightSearch search = new FlightSearch();
        assertFalse("testReturnBeforeDeparture", search.runFlightSearch("25/10/2025", "syd", false, "24/10/2025", "mel", "economy", 2, 0, 0));
    }

    public static void testValidSeatingClass() {
        FlightSearch search = new FlightSearch();
        assertTrue("testValidSeatingClass", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "business", 2, 0, 0));
    }

    public static void testInvalidSeatingClass() {
        FlightSearch search = new FlightSearch();
        assertFalse("testInvalidSeatingClass", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "executive", 2, 0, 0));
    }

    public static void testEmergencyRowInFirstInvalid() {
        FlightSearch search = new FlightSearch();
        assertFalse("testEmergencyRowInFirstInvalid", search.runFlightSearch("25/10/2025", "syd", true, "05/11/2025", "mel", "first", 2, 0, 0));
    }

    public static void testEmergencyRowInBusinessInvalid() {
        FlightSearch search = new FlightSearch();
        assertFalse("testEmergencyRowInBusinessInvalid", search.runFlightSearch("25/10/2025", "syd", true, "05/11/2025", "mel", "business", 2, 0, 0));
    }

    public static void testValidDifferentAirports() {
        FlightSearch search = new FlightSearch();
        assertTrue("testValidDifferentAirports", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "economy", 2, 0, 0));
    }

    public static void testSameAirportInvalid() {
        FlightSearch search = new FlightSearch();
        assertFalse("testSameAirportInvalid", search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "syd", "economy", 2, 0, 0));
    }
}