import java.util.LinkedList;
import java.util.List;

public class Terminal extends Subject {

    private List<Flight> flights;
    private String name;

    public Terminal(String name) {
        this.name = name;
        flights = new LinkedList<Flight>();
    }

    @Override
    public void addFlight(Flight flight) {
        flights.add(flight);
        notifyObservers();
    }

    @Override
    public void removeFlight(Flight flight) {
        System.out.println("ok");
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