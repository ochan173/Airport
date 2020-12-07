import java.io.Serializable;

public class Flight implements Serializable {
  
  /**
   * Constant string for the ON TIME status
   */
  public static final String ONTIME = "ON TIME";

  /**
   * Constant string for the BOARDING status
   */
  public static final String BOARDING = "BOARDING";

  /**
   * Constant string for the DELAYED status
   */
  public static final String DELAYED = "DELAYED";

  /**
   * Constant string for the CANCELLED status
   */
  public static final String CANCELLED = "CANCELLED";


  private String company;
  private int flightNumber;
  private String destination;
  private int departureTime;
  private String gate;
  private String status;

  public Flight(String company, int flightNumber, String destination, int departureTime, String gate, String status) {
    this.company = company;
    this.flightNumber = flightNumber;
    this.destination = destination;
    this.departureTime = departureTime;
    this.gate = gate;
    this.status = status;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public int getFlightNumber() {
    return flightNumber;
  }

  public void setFlightNumber(int flightNumber) {
    this.flightNumber = flightNumber;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public int getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(int departureTime) {
    this.departureTime = departureTime;
  }

  public String getGate() {
    return gate;
  }

  public void setGate(String gate) {
    this.gate = gate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String toString() {
    return this.company + String.valueOf(this.flightNumber) + " " + this.destination + " " +
            String.valueOf(this.departureTime) + " " + this.gate + " " + this.status;
  }
}
