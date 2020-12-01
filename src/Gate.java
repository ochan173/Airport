import java.util.ArrayList;
import java.util.List;

public class Gate extends Subject {

    private List<Flight> flights;
    private String name;

    public Gate(String name) {
        this.name = name;
        flights = new ArrayList<Flight>();
    }

    @Override
    public void addFlight(Flight flight) {
        flights.add(flight);
        notifyObservers();
    }

    @Override
    public void removeFlight(Flight flight) {
        flights.remove(flight);
        notifyObservers();
    }
	
	  public void updateFlights(String identifier, Flight flight) {
       if (flights.size() >= 1) {
            int index = -1;
            for (Flight f: flights) {
                index++;

                if (identifier.equals(f.getCompany() + f.getFlightNumber())) {
                    flights.set(index, flight);
                }
            }
        }
      notifyObservers();
    }
	
	  public boolean isAvailable(String terminal, int gateNumber) {
			String gate = terminal + "-" + Integer.toString(gateNumber);
			
       for (Flight f: flights) {
           if (gate.equals(f.getGate())) {
               return false;
           }
       }
       return true;
    }

    @Override
    public List<Flight> getFlights() {
        return flights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}