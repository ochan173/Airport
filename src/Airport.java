import java.util.*;

public class Airport extends Subject{
    private List<Flight> flights;

    public Airport() {
        flights = new LinkedList<Flight>();
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
				 notifyObservers();
        }
    }

    @Override
    public List<Flight> getFlights() {
        return flights;
    }
}