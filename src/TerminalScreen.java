public class TerminalScreen extends Observer {

    private Terminal terminal;
    private String name;
		private ScreenDialog screenDialog;

    public TerminalScreen(Terminal terminal, String name, ScreenDialog screenDialog) {
        this.terminal = terminal;
        this.name = name;
        this.screenDialog = screenDialog;
    }

    @Override
    public void update() {
        screenDialog.setScreenText("");
        StringBuilder text = new StringBuilder();
        for (Flight f : terminal.getFlights()) {
            text.append(f.toString()).append("\n");
        }
        screenDialog.setScreenText(text.toString());
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}