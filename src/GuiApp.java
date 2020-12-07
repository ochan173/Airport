import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.IIOException;
import javax.swing.*;

public class GuiApp extends JFrame {

    /*
     * Simulation attributes
     * DO NOT EDIT
     */

    private Airport airport = new Airport();
    private Terminal termA = new Terminal("TERMINAL A");
    private Terminal termB = new Terminal("TERMINAL B");
    private Terminal termC = new Terminal("TERMINAL C");
    private Gate[] gatesA = new Gate[3];
    private Gate[] gatesB = new Gate[7];
    private Gate[] gatesC = new Gate[5];

    private ScreenDialog airportScreen1Dialog = new ScreenDialog(this, "AIRPORT (1)", 0);
    private ScreenDialog airportScreen2Dialog = new ScreenDialog(this, "AIRPORT (2)", 0);
    private ScreenDialog termAScreen1Dialog = new ScreenDialog(this, "TERMINAL A (1)", 0);
    private ScreenDialog termAScreen2Dialog = new ScreenDialog(this, "TERMINAL A (2)", 0);
    private ScreenDialog termAScreen3Dialog = new ScreenDialog(this, "TERMINAL A (3)", 0);
    private ScreenDialog termBScreen1Dialog = new ScreenDialog(this, "TERMINAL B (1)", 0);
    private ScreenDialog termBScreen2Dialog = new ScreenDialog(this, "TERMINAL B (2)", 0);
    private ScreenDialog termBScreen3Dialog = new ScreenDialog(this, "TERMINAL B (3)", 0);
    private ScreenDialog termCScreen1Dialog = new ScreenDialog(this, "TERMINAL C (1)", 0);
    private ScreenDialog termCScreen2Dialog = new ScreenDialog(this, "TERMINAL C (2)", 0);
    private ScreenDialog termCScreen3Dialog = new ScreenDialog(this, "TERMINAL C (3)", 0);
    private ScreenDialog[] gatesADialogs = new ScreenDialog[3];
    private ScreenDialog[] gatesBDialogs = new ScreenDialog[7];
    private ScreenDialog[] gatesCDialogs = new ScreenDialog[5];

    /*
     * GUI attributes
     */

    public static final int WIDTH = 400;
    public static final int HEIGHT = 4 * ScreenDialog.HEIGHT;
    private static final int LINE_COUNT = 10;
    private static final int LINE_SIZE = 30; // in characters

    private static final String addFlightText = "Add Flight";
    private static final String delayFlightText = "Delay Flight";
    private static final String changeGateText = "Change Gate";
    private static final String cancelFlightText = "Cancel Flight";
    private static final String notifyBoardfingText = "Notify Boarding";
    private static final String removeFlightText = "Remove Flight";
    private static final String saveText = "Save";
    private static final String loadText = "Load";
    private static final String quitText = "Quit";
    // TODO: Add attributes for other operations

    private JButton addButton, delayButton, changeGateButton, cancelButton, boardingButton,
            removeButton, quitButton, saveButton, loadButton;
    // TODO: Add attributes for other operations

    private JTextArea displayArea;
    private JScrollPane scrollPanel;
    private Container contentPanel;

    /**
     * Creates the observers and attach them.
     * DO NOT EDIT
     */
    public void createObservers() {

        // Airport screens

        AirportScreen airportScreen1 = new AirportScreen(airport, "AIRPORT (1)", airportScreen1Dialog);
        AirportScreen airportScreen2 = new AirportScreen(airport, "AIRPORT (2)", airportScreen2Dialog);
        airport.attach(airportScreen1);
        airport.attach(airportScreen2);

        // Terminal screens (three each)

        TerminalScreen termAScreen1 = new TerminalScreen(termA, "TERMINAL A (1)", termAScreen1Dialog);
        TerminalScreen termAScreen2 = new TerminalScreen(termA, "TERMINAL A (2)", termAScreen2Dialog);
        TerminalScreen termAScreen3 = new TerminalScreen(termA, "TERMINAL A (3)", termAScreen3Dialog);
        termA.attach(termAScreen1);
        termA.attach(termAScreen2);
        termA.attach(termAScreen3);

        TerminalScreen termBScreen1 = new TerminalScreen(termB, "TERMINAL B (1)", termBScreen1Dialog);
        TerminalScreen termBScreen2 = new TerminalScreen(termB, "TERMINAL B (2)", termBScreen2Dialog);
        TerminalScreen termBScreen3 = new TerminalScreen(termB, "TERMINAL B (3)", termBScreen3Dialog);
        termB.attach(termBScreen1);
        termB.attach(termBScreen2);
        termB.attach(termBScreen3);

        TerminalScreen termCScreen1 = new TerminalScreen(termC, "TERMINAL C (1)", termCScreen1Dialog);
        TerminalScreen termCScreen2 = new TerminalScreen(termC, "TERMINAL C (2)", termCScreen2Dialog);
        TerminalScreen termCScreen3 = new TerminalScreen(termC, "TERMINAL C (3)", termCScreen3Dialog);
        termC.attach(termCScreen1);
        termC.attach(termCScreen2);
        termC.attach(termCScreen3);

        // Gates and gate screens

        // Terminal A
        for (int i = 0; i < gatesA.length; ++i) {
            gatesA[i] = new Gate("A-" + (i + 1));
            gatesA[i].attach(new GateScreen(gatesA[i], gatesADialogs[i]));
        }

        // Terminal B
        for (int i = 0; i < gatesB.length; ++i) {
            gatesB[i] = new Gate("B-" + (i + 1));
            gatesB[i].attach(new GateScreen(gatesB[i], gatesBDialogs[i]));
        }

        // Terminal C
        for (int i = 0; i < gatesC.length; ++i) {
            gatesC[i] = new Gate("C-" + (i + 1));
            gatesC[i].attach(new GateScreen(gatesC[i], gatesCDialogs[i]));
        }
    }

    /**
     * Creates the objects for the airport screens in a user-friendly layout
     */
    public void createScreenDialogs() {
        int colNumber, rowNumber = 0;

        colNumber = 0;
        airportScreen1Dialog.setVisible(true);
        airportScreen1Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
        airportScreen2Dialog.setVisible(true);
        airportScreen2Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);

        ++rowNumber;
        colNumber = 0;
        termAScreen1Dialog.setVisible(true);
        termAScreen1Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
        termAScreen2Dialog.setVisible(true);
        termAScreen2Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
        termAScreen3Dialog.setVisible(true);
        termAScreen3Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);

        ++rowNumber;
        colNumber = 0;
        termBScreen1Dialog.setVisible(true);
        termBScreen1Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
        termBScreen2Dialog.setVisible(true);
        termBScreen2Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
        termBScreen3Dialog.setVisible(true);
        termBScreen3Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);

        ++rowNumber;
        colNumber = 0;
        termCScreen1Dialog.setVisible(true);
        termCScreen1Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
        termCScreen2Dialog.setVisible(true);
        termCScreen2Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
        termCScreen3Dialog.setVisible(true);
        termCScreen3Dialog.setLocation(WIDTH + colNumber++ * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);

        ++rowNumber;
        for (int i = 0; i < gatesADialogs.length; ++i) {
            gatesADialogs[i] = new ScreenDialog(this, "GATE A-" + (i + 1), 0);
            gatesADialogs[i].setVisible(true);
            gatesADialogs[i].setLocation(i * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
        }

        ++rowNumber;
        int maxRowLength = 4; // Layout on two rows
        for (int i = 0; i < gatesBDialogs.length; ++i) {
            gatesBDialogs[i] = new ScreenDialog(this, "GATE B-" + (i + 1), 0);
            gatesBDialogs[i].setVisible(true);
            gatesBDialogs[i].setLocation((i % (maxRowLength + 1)) * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
            if (i == maxRowLength) ++rowNumber;
        }

        ++rowNumber;
        for (int i = 0; i < gatesCDialogs.length; ++i) {
            gatesCDialogs[i] = new ScreenDialog(this, "GATE C-" + (i + 1), 0);
            gatesCDialogs[i].setVisible(true);
            gatesCDialogs[i].setLocation(i * ScreenDialog.WIDTH, rowNumber * ScreenDialog.HEIGHT);
        }
    }

    /**
     * Initializes the GUI components
     */
    public void initComponents() {
        addButton = new JButton(addFlightText);
        delayButton = new JButton(delayFlightText);
        cancelButton = new JButton(cancelFlightText);
        changeGateButton = new JButton(changeGateText);
        boardingButton = new JButton(notifyBoardfingText);
        removeButton = new JButton(removeFlightText);
        saveButton = new JButton(saveText);
        loadButton = new JButton(loadText);
        quitButton = new JButton(quitText);
        // TODO: Add button instanciations for other operations

        // Initialize display area
        displayArea = new JTextArea(LINE_COUNT, LINE_SIZE);
        displayArea.setEditable(false);
        scrollPanel = new JScrollPane(displayArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }

    public void initLayout() {
        contentPanel = getContentPane();
        contentPanel.setLayout(new FlowLayout());
    }

    public void initContentPane() {
        contentPanel.add(addButton);
        contentPanel.add(delayButton);
        contentPanel.add(cancelButton);
        contentPanel.add(changeGateButton);
        contentPanel.add(boardingButton);
        contentPanel.add(removeButton);
        contentPanel.add(saveButton);
        contentPanel.add(loadButton);
        // TODO: Add other buttons
        contentPanel.add(quitButton);

        contentPanel.add(displayArea);
    }

    public void initListeners() {

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                displayArea.append("Add Flight Option\n");
                AddFlightDialog addFlightDialog = new AddFlightDialog(GuiApp.this);
                addFlightDialog.setVisible(true);
            }
        });

        delayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                if (airport.getFlights().size() > 0) {
                    displayArea.append("Delay Flight Option\n");
                    ChangeFlightStatusDialog changeFlightStatusDialog = new ChangeFlightStatusDialog(GuiApp.this, Flight.DELAYED);
                    changeFlightStatusDialog.setVisible(true);
                } else {
                    displayArea.append("No flight available\n");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                if (airport.getFlights().size() > 0) {
                    displayArea.append("Remove Flight Option\n");
                    RemoveFlightDialog removeFlightDialog = new RemoveFlightDialog(GuiApp.this);
                    removeFlightDialog.setVisible(true);
                } else {
                    displayArea.append("No flight available\n");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                if (airport.getFlights().size() > 0) {
                    displayArea.append("Cancel Flight Option\n");
                    ChangeFlightStatusDialog changeFlightStatusDialog = new ChangeFlightStatusDialog(GuiApp.this, Flight.CANCELLED);
                    changeFlightStatusDialog.setVisible(true);
                } else {
                    displayArea.append("No flight available\n");
                }
            }
        });

        boardingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                if (airport.getFlights().size() > 0) {
                    displayArea.append("Cancel Flight Option\n");
                    ChangeFlightStatusDialog changeFlightStatusDialog = new ChangeFlightStatusDialog(GuiApp.this, Flight.BOARDING);
                    changeFlightStatusDialog.setVisible(true);
                } else {
                    displayArea.append("No flight available\n");
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayArea.append("Quit");
                System.out.println("Quit");

                // Yes/no pop-up
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Do you want to quit?",
                        "Quit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        changeGateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");
                if (airport.getFlights().size() > 0) {
                    displayArea.append("Change gate Option\n");
                    ChangeGateDialog changeGateDialog = new ChangeGateDialog(GuiApp.this);
                    changeGateDialog.setVisible(true);
                } else {
                    displayArea.append("No flight available\n");
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //https://www.codejava.net/java-se/swing/show-simple-open-file-dialog-using-jfilechooser
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    try {
                        saveFlights(selectedFile.getAbsolutePath());
                    }
                    catch (IOException exception) {
                        displayArea.append("File not found\n");
                    }
                }

            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //https://www.codejava.net/java-se/swing/show-simple-open-file-dialog-using-jfilechooser
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    try {
                        loadFlights(selectedFile.getAbsolutePath());
                    }
                    catch (IOException exception) {
                        displayArea.append("File not found\n");
                    }
                }
            }
        });


    }

    public void addFlightToAirport(Flight flight) {
        airport.addFlight(flight);
    }

    public void addFlightToTerminal(Flight flight, String terminal) {
        switch (terminal) {
            case "A":
                termA.addFlight(flight);
                break;
            case "B":
                termB.addFlight(flight);
                break;
            case "C":
                termC.addFlight(flight);
                break;
        }
    }

    public void addFlightToGate(Flight flight, String terminal, int gateNumber) {
        switch (terminal) {
            case "A":
                gatesA[gateNumber - 1].addFlight(flight);
                break;
            case "B":
                gatesB[gateNumber - 1].addFlight(flight);
                break;
            case "C":
                gatesC[gateNumber - 1].addFlight(flight);
                break;
        }

    }

    /*
     * Changes the status of a flight
     * @param status The new status of the flight
     */
    public void changeFlightStatus(String company, int flightNumber, String status) {
        String identifier = company + Integer.toString(flightNumber);

        Flight flight = getFlight(identifier);

        if (flight != null) {

            flight.setStatus(status);

            airport.updateFlights(identifier, flight);
            updateTerminalGate(identifier, flight);
        }

    }

    /*
     * Removes a flight
     */
    public void removeFlight(String company, int flightNumber) {

        String identifier = company + Integer.toString(flightNumber);

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

    public void changeGate(String company, int fn, String newTerminal, int newGateNumber) {

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

    private Flight getFlight(String identifier) {
        List<Flight> flights = airport.getFlights();

        for (Flight f : flights) {
            if (identifier.equals(f.getCompany() + f.getFlightNumber())) {
                return f;
            }
        }
        return null;
    }

    private void saveFlights(String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(airport.getFlights());
        oos.close();

        displayArea.append("");
        displayArea.append("Flights saved!");
    }

    private void loadFlights(String path) throws IOException {
        FileInputStream fin = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fin);
        List<Flight> flights = new LinkedList<Flight>();
        try {
            flights = (List<Flight>) ois.readObject();
            System.out.println(flights.size());
        }
        catch (ClassNotFoundException ce) {
            displayArea.append("Error : " + ce.getMessage());
        }

        ois.close();

        for (Flight f : flights) {
            String terminal = f.getGate().split("-", 0)[0];
            int gateNumber = Integer.parseInt(f.getGate().split("-", 0)[1]);

            addFlightToAirport(f);
            addFlightToTerminal(f, terminal);
            addFlightToGate(f, terminal, gateNumber);
        }
        displayArea.append("");
        displayArea.append("Flights loaded!");
    }

    public void appendToDisplayArea(String text) {
        displayArea.append(text);
    }

    public void init() {
        initComponents();
        initLayout();
        initContentPane();
        initListeners();
    }

    public GuiApp(String windowTitle) {
        super(windowTitle);

        frameInit();
        init();
        createScreenDialogs();
        createObservers();

        // Add listener to quit
        this.addWindowListener(
                new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });

        pack(); // Start to change frame dimensions
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setResizable(true);
        validate(); // Update and validation
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp("Airport Simulation");
            }
        });
    }

}
