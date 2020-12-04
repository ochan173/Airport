public class GateScreen extends Observer {

    private Gate gate;
		private ScreenDialog screenDialog;

    public GateScreen(Gate gate, ScreenDialog screenDialog) {
        this.gate = gate;
		    this.screenDialog = screenDialog;
    }

    @Override
    public void update() {
        screenDialog.setScreenText("");
        for (Flight f : gate.getFlights()) {
            screenDialog.setScreenText(f.toString());
        }
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }
}