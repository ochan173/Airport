import java.util.Scanner;
import java.util.*;


public class ConsoleApp {

    /*
     * Simulation attributes
     * DO NOT EDIT
     */

    private Scanner scan = new Scanner(System.in);

    private Airport airport = new Airport();
    private Terminal termA = new Terminal("TERMINAL A");
    private Terminal termB = new Terminal("TERMINAL B");
    private Terminal termC = new Terminal("TERMINAL C");
    private Gate[] gatesA = new Gate[3];
    private Gate[] gatesB = new Gate[7];
    private Gate[] gatesC = new Gate[5];

    /**
     * Creates the objects used for the simulation.
     * DO NOT EDIT
     */
    public void createObjects() {

        // Airport screens

//        AirportScreen airportScreen1 = new AirportScreen(airport, "AIRPORT (1)");
//        AirportScreen airportScreen2 = new AirportScreen(airport, "AIRPORT (2)");
//        airport.attach(airportScreen1);
//        airport.attach(airportScreen2);
//
//        // Terminal screens (three each)
//
//        TerminalScreen termAScreen1 = new TerminalScreen(termA, "TERMINAL A (1)");
//        TerminalScreen termAScreen2 = new TerminalScreen(termA, "TERMINAL A (2)");
//        TerminalScreen termAScreen3 = new TerminalScreen(termA, "TERMINAL A (3)");
//        termA.attach(termAScreen1);
//        termA.attach(termAScreen2);
//        termA.attach(termAScreen3);
//
//        TerminalScreen termBScreen1 = new TerminalScreen(termB, "TERMINAL B (1)");
//        TerminalScreen termBScreen2 = new TerminalScreen(termB, "TERMINAL B (2)");
//        TerminalScreen termBScreen3 = new TerminalScreen(termB, "TERMINAL B (3)");
//        termB.attach(termBScreen1);
//        termB.attach(termBScreen2);
//        termB.attach(termBScreen3);
//
//        TerminalScreen termCScreen1 = new TerminalScreen(termC, "TERMINAL C (1)");
//        TerminalScreen termCScreen2 = new TerminalScreen(termC, "TERMINAL C (2)");
//        TerminalScreen termCScreen3 = new TerminalScreen(termC, "TERMINAL C (3)");
//        termC.attach(termCScreen1);
//        termC.attach(termCScreen2);
//        termC.attach(termCScreen3);

        // Gates and gate screens

        // Terminal A
//        for (int i = 0; i < gatesA.length; ++i) {
//            gatesA[i] = new Gate("A-" + (i + 1));
//            gatesA[i].attach(new GateScreen(gatesA[i]));
//        }
//
//        // Terminal B
//        for (int i = 0; i < gatesB.length; ++i) {
//            gatesB[i] = new Gate("B-" + (i + 1));
//            gatesB[i].attach(new GateScreen(gatesB[i]));
//        }
//
//        // Terminal C
//        for (int i = 0; i < gatesC.length; ++i) {
//            gatesC[i] = new Gate("C-" + (i + 1));
//            gatesC[i].attach(new GateScreen(gatesC[i]));
//        }
    }

    /**
     * Adds a new flight based on user-provided information.
     */
    public void addFlight() {

        // Collect flight information from the console

        System.out.print("Company: ");
        String company = scan.next();

        System.out.print("Flight Number: ");
        int flightNumber = scan.nextInt();

        System.out.print("Destination: ");
        String destination = scan.next();

        System.out.print("Departure Time (0000): ");
        int departureTime = scan.nextInt();

        System.out.print("Terminal (A, B, C): ");
        String terminal = scan.next();

        System.out.print("Gate Number: ");
        int gateNumber = scan.nextInt();

        System.out.println("Status:");
        System.out.println("1 - " + Flight.ONTIME);
        System.out.println("2 - " + Flight.CANCELLED);
        System.out.println("3 - " + Flight.BOARDING);
        System.out.println("4 - " + Flight.DELAYED);
        int statusInt = scan.nextInt();
        String statusStr = "";
        switch (statusInt) {
            case 1:
                statusStr = Flight.ONTIME;
                break;
            case 2:
                statusStr = Flight.CANCELLED;
                break;
            case 3:
                statusStr = Flight.BOARDING;
                break;
            case 4:
                statusStr = Flight.DELAYED;
                break;
        }

        // Create an instance of Flight
        Flight flight = new Flight(company, flightNumber, destination,
                departureTime, terminal + "-" + gateNumber, statusStr);

        // Add it to the airport's list of flights
        airport.addFlight(flight);

        // Add it to the appropriate terminal and gate
        switch (terminal) {
            case "A":
                termA.addFlight(flight);
                gatesA[gateNumber - 1].addFlight(flight);
                break;
            case "B":
                termB.addFlight(flight);
                gatesB[gateNumber - 1].addFlight(flight);
                break;
            case "C":
                termC.addFlight(flight);
                gatesC[gateNumber - 1].addFlight(flight);
                break;
        }
    }

    /*
     * TODO: Implement the following methods. You may add other methods as well.
     */

    /*
     * Changes the status of a flight to DELAYED
     */
    public void delayFlight() {
        changeFlightStatus(Flight.DELAYED);
    }

    public void changeGate() {
        System.out.print("Company: ");
        String company = scan.next();

        System.out.print("Flight Number: ");
        int fn = scan.nextInt();

        System.out.print("Terminal (A, B, C): ");
        String newTerminal = scan.next();

        System.out.print("Gate Number: ");
        int newGateNumber = scan.nextInt();


        String identifier = company + Integer.toString(fn);

        Flight flight = getFlight(identifier);

        String oldTerminal = flight.getGate().split("-", 0)[0];
        int oldGate = Integer.parseInt(flight.getGate().split("-", 0)[1]);


        boolean gateAvailable = true;

        String newGate = newTerminal + "-" + Integer.toString(newGateNumber);

        if (!gateIsAvailable(newTerminal, newGateNumber)) {
            System.out.println("New gate " + newGate + " is not available");
        } else {
            if (oldTerminal.equals(newTerminal)) {
                removeOldFlight(flight, oldGate, oldTerminal, true);
            } else {
                removeOldFlight(flight, oldGate, oldTerminal, false);
            }
            flight.setGate(newGate);
            airport.updateFlights(identifier, flight);

            if (oldTerminal.equals(newTerminal)) {
                addNewFlight(flight, newGateNumber, newTerminal, true);
            } else {
                addNewFlight(flight, newGateNumber, newTerminal, false);
            }
        }
    }

    /*
     * Changes the status of a flight to CANCELLED
     */
    public void cancelFlight() {
        changeFlightStatus(Flight.CANCELLED);
    }

    /*
     * Changes the status of a flight to BOARDING
     */
    public void notifyBoarding() {
        changeFlightStatus(Flight.BOARDING);
    }

    /*
     * Removes a flight
     */
    public void removeFlight() {
        System.out.print("Company: ");
        String company = scan.next();

        System.out.print("Flight Number: ");
        int fn = scan.nextInt();

        String identifier = company + Integer.toString(fn);

        Flight flight = getFlight(identifier);

        airport.removeFlight(flight);

        String terminal = flight.getGate().split("-", 0)[0];
        int gateNumber = Integer.parseInt(flight.getGate().split("-", 0)[1]);

        switch (terminal) {
            case "A":
                termA.removeFlight(flight);
                gatesA[gateNumber - 1].removeFlight(flight);
                break;
            case "B":
                termB.removeFlight(flight);
                gatesB[gateNumber - 1].removeFlight(flight);
                break;
            case "C":
                termC.removeFlight(flight);
                gatesC[gateNumber - 1].removeFlight(flight);
                break;
        }
    }

    /*
     * Retreives the information of a flight from its identifier
     * @param identifier The identifier of the flight (company + flight number)
     * @return The flight
     */
    private Flight getFlight(String identifier) {
        List<Flight> flights = airport.getFlights();

        for (Flight f : flights) {
            if (identifier.equals(f.getCompany() + f.getFlightNumber())) {
                return f;
            }
        }
        return null;
    }

    /*
     * Updates a flight in the concerned terminals and gates
     * @param identifier The identifier of the flight (company + flight number)
     * @param flight The new informations of the flight
     */
    private void updateTerminalGate(String identifier, Flight flight) {
        String terminal = flight.getGate().split("-", 0)[0];
        int gateNumber = Integer.parseInt(flight.getGate().split("-", 0)[1]);

        switch (terminal) {
            case "A":
                termA.updateFlights(identifier, flight);
                gatesA[gateNumber - 1].updateFlights(identifier, flight);
                break;
            case "B":
                termB.updateFlights(identifier, flight);
                gatesB[gateNumber - 1].updateFlights(identifier, flight);
                break;
            case "C":
                termC.updateFlights(identifier, flight);
                gatesC[gateNumber - 1].updateFlights(identifier, flight);
                break;
        }
    }

    /*
     * Changes the status of a flight
     * @param status The new status of the flight
     */
    private void changeFlightStatus(String status) {
        System.out.print("Company: ");
        String company = scan.next();

        System.out.print("Flight Number: ");
        int fn = scan.nextInt();

        String identifier = company + Integer.toString(fn);

        if (identifier != null) {

            Flight flight = getFlight(identifier);

            if (flight != null) {

                flight.setStatus(status);

                airport.updateFlights(identifier, flight);
                updateTerminalGate(identifier, flight);
            }
        }
    }

    /*
     * Adds a new flight to the concerned terminal and gate
     * @param flight The new flight to add
     * @param gateNumber The new gate number of the flight
     * @param terminal The terminal of the flight
     * @param sameTerm True if the flight stays in the same terminal, else False
     */
    private void addNewFlight(Flight flight, int gateNumber, String terminal, boolean sameTerm) {
        String identifier = flight.getCompany() + Integer.toString(flight.getFlightNumber());
        switch (terminal) {
            case "A":
                if (!sameTerm) {
                    termA.addFlight(flight);
                } else {
                    termA.updateFlights(identifier, flight);
                }
                gatesA[gateNumber - 1].addFlight(flight);
                break;
            case "B":
                if (!sameTerm) {
                    termB.addFlight(flight);
                } else {
                    termB.updateFlights(identifier, flight);
                }
                gatesB[gateNumber - 1].addFlight(flight);
                break;
            case "C":
                if (!sameTerm) {
                    termC.addFlight(flight);
                } else {
                    termB.updateFlights(identifier, flight);
                }
                gatesC[gateNumber - 1].addFlight(flight);
                break;
        }
    }

    /*
     * Removes a flight from the concerned terminal and gate
     * @param flight The new flight to remove
     * @param gateNumber The old gate number of the flight
     * @param terminal The old terminal of the flight
     * @param sameTerm True if the flight stays in the same terminal, esle False
     */
    private void removeOldFlight(Flight flight, int gateNumber, String terminal, boolean sameTerm) {
        switch (terminal) {
            case "A":
                if (!sameTerm) {
                    termA.removeFlight(flight);
                }
                gatesA[gateNumber - 1].removeFlight(flight);
                break;
            case "B":
                if (!sameTerm) {
                    termB.removeFlight(flight);
                }
                gatesB[gateNumber - 1].removeFlight(flight);
                break;
            case "C":
                if (!sameTerm) {
                    termC.removeFlight(flight);
                }
                gatesC[gateNumber - 1].removeFlight(flight);
                break;
        }
    }

    /*
     * Checks if a gate is already occupied by a flight
     * @param terminal Terminal of the flight
     * @param gateNumber Gate number of the flight
     * @return True if the gate is free, else false
     */
    private boolean gateIsAvailable(String terminal, int gateNumber) {
        switch (terminal) {
            case "A":
                if (gatesA.length == 0) {
                    return true;
                } else if (gateNumber > gatesA.length) {
                    return false;
                } else if (!gatesA[gateNumber - 1].isAvailable(terminal, gateNumber)) {
                    return false;
                }
            case "B":
                if (gatesB.length == 0) {
                    return true;
                } else if (gateNumber > gatesB.length) {
                    return false;
                } else if (!gatesB[gateNumber - 1].isAvailable(terminal, gateNumber)) {
                    return false;
                }
            case "C":
                if (gatesC.length != 0) {
                    return true;
                } else if (gateNumber > gatesC.length) {
                    return false;
                } else if (!gatesC[gateNumber - 1].isAvailable(terminal, gateNumber)) {
                    return false;
                }
        }
        return true;
    }

    /**
     * Displays the options available in the console.
     * DO NOT EDIT
     */
    public void displayMenu() {
        System.out.println("********************");
        System.out.println("1 - Add Flight");
        System.out.println("2 - Delay Flight");
        System.out.println("3 - Change Gate");
        System.out.println("4 - Cancel Flight");
        System.out.println("5 - Notify Boarding");
        System.out.println("6 - Remove Flight");
        System.out.println("0 - Quit");
        System.out.println("********************");
    }

    /**
     * Displays the choice menu, waits for the user input, and calls the appropriate method.
     * DO NOT EDIT
     */
    public void displayPrompt() {
        int option = 0;

        do {
            displayMenu();
            System.out.print("Select Option: ");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    addFlight();
                    break;
                case 2:
                    delayFlight();
                    break;
                case 3:
                    changeGate();
                    break;
                case 4:
                    cancelFlight();
                    break;
                case 5:
                    notifyBoarding();
                    break;
                case 6:
                    removeFlight();
                    break;
                case 0:
                    scan.close();
                    break;
                default:
                    System.out.println("Error - stop");
                    scan.close();
                    return;
            }
        } while (option != 0); // While the option is not Quit
    }

    /**
     * Creates all the objects and starts the simulation.
     * DO NOT EDIT
     */
    public static void main(String[] args) {
        ConsoleApp app = new ConsoleApp();

        // Object setup
        app.createObjects();

        // Start the simulation
        app.displayPrompt();
    }

}
