package flight;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.List;

public class FlightSearch {
   private String  departureDate;
   private String  departureAirportCode;
   private boolean emergencyRowSeating;
   private String  returnDate;
   private String  destinationAirportCode; 
   private String  seatingClass;
   private int     adultPassengerCount;
   private int     childPassengerCount;
   private int     infantPassengerCount;
   private LocalDate date = LocalDate.now();

   private LocalDate validateDateFormat(String dateStr) {
      if (dateStr == null || dateStr.isEmpty()) {
         return null;
      }
      try {
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
         return LocalDate.parse(dateStr, formatter);
      } catch (DateTimeParseException e) {
         return null;
      }
   }


   public boolean runFlightSearch(String departureDate,    String departureAirportCode,   boolean emergencyRowSeating, 
                                  String returnDate,       String destinationAirportCode, String seatingClass, 
                                  int adultPassengerCount, int childPassengerCount,       int infantPassengerCount) {
      boolean valid = true;


      // Validate atleast 1 - 9 Passengers condition 1
      int totalPassengers = (adultPassengerCount + childPassengerCount + infantPassengerCount); 
      if (totalPassengers < 1 || totalPassengers > 9){
         System.out.println("Bad search: Invalid number of passengers\n");
         return false;
      }

      //Validate Seating Type Contition 9
      if (!seatingClass.equals("economy") && !seatingClass.equals("premium economy") && !seatingClass.equals("business") && !seatingClass.equals("first")) {
         System.out.println("Bad search: Invalid seat type\n");
         return false;
      }

      //Children Cannot be seated emergency row or first class condition 2 
      if (childPassengerCount > 0) {
         if (emergencyRowSeating || seatingClass.equals("first")) {
            System.out.println("Bad search: No children in emergency row or first class\n");
            return false;
         }
      }

      // No infants in emergency row or business class 
      
      if (infantPassengerCount > 0) {
         if (emergencyRowSeating || seatingClass.equals("business")) {
            System.out.println("Bad search: No infant in emergency row or business class\n");
            return false;
         }
      }

      //2 children per adult condition 4
      if (childPassengerCount > 2 * adultPassengerCount ){
         System.out.println("Bad search: Atleast one adult required for every two children\n");
         return false;
      }

      //only one infant per adult condition 5
      if (infantPassengerCount > adultPassengerCount){
         System.out.println("Atleast one adult required per infant.\n");
         return false;
      }

      // Only economy class have emergency row condition contition 10
      if (emergencyRowSeating && !seatingClass.equals("economy")) {
         System.out.println("Emergency seating only in economy class\n");
         return false;
      }

      //Valid airport code condition 11
      List<String> validAirports = Arrays.asList("syd", "mel", "lax", "cdg", "del", "pvg", "doh");
      if (!validAirports.contains(departureAirportCode) || !validAirports.contains(destinationAirportCode)) {
         System.out.println("invalid airportcode\n");
         return false;
      }
      //Airport cannot be the same condition 11
      if (departureAirportCode.equals(destinationAirportCode)) {
         System.out.println("Trip cannot be to the same airport\n");
         return false;
      }

      // Flights are always 2 way condition 8

      //Validate date format condition 7
      LocalDate departureDateVal = validateDateFormat(departureDate);
      LocalDate returnDateVal = validateDateFormat(returnDate);

      if (departureDateVal == null || returnDateVal == null) {
         System.out.println("Invalid date format\n");
         return false;
      }

      //departure date cannot be before today condtion 6
      if (departureDateVal.isBefore(this.date)) {
         System.out.print("No departure date before ");
         System.out.println(this.date);
         return false;

      }

      // Return Date cannot be before departure date condition 8
      if (returnDateVal.isBefore(departureDateVal)) {
         System.out.println("Return date cannot be before departure date\n");
         return false;
      }




      this.departureDate = departureDate;
      this.departureAirportCode = departureAirportCode;
      this.emergencyRowSeating = emergencyRowSeating;
      this.returnDate = returnDate;
      this.destinationAirportCode = destinationAirportCode; 
      this.seatingClass = seatingClass;
      this.adultPassengerCount = adultPassengerCount;
      this.childPassengerCount = childPassengerCount;
      this.infantPassengerCount = infantPassengerCount;



      return valid;
   }


   public static void main(String[] args){
      FlightSearch search = new FlightSearch();
      boolean validSearch = search.runFlightSearch("25/10/2025", "syd", false, "05/11/2025", "mel", "business", 2, 3, 1);
      if(validSearch){
         System.out.println("Flight search is valid!");
      } else {
         System.out.println("Flight search is invalid.");
      }
   }
}