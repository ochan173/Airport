import java.util.LinkedList;
import java.util.List;

public class AirportScreen extends Observer {

    private Airport airport;
    private String name;
    private ScreenDialog screenDialog;

    public AirportScreen(Airport airport, String name, ScreenDialog screenDialog) {
        this.airport = airport;
        this.name = name;
        this.screenDialog = screenDialog;
    }

    @Override
    public void update() {
        screenDialog.setScreenText("");
        StringBuilder text = new StringBuilder();
        for (Flight f : airport.getFlights()) {
            text.append(f.toString()).append("\n");
        }
        screenDialog.setScreenText(text.toString());
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}