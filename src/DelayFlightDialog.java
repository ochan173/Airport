import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DelayFlightDialog extends JDialog {

    public static final int WIDTH = 150;
    public static final int HEIGHT = 200;

    private GuiApp app;
    private String currentState = null;

    private JButton okButton, cancelButton;
    private JPanel controlPanel;
    private JTextField companyField, flightNumberField;

    public DelayFlightDialog(GuiApp app) {

        super(app, "Delay Flight");
        this.app = app;

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                delayFlight();
                setVisible(false);
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        DelayFlightDialog.this,
                        "Do you want to cancel?",
                        "Cancel",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    setVisible(false);
                }
            }
        });


        JPanel companyPanel = new JPanel();
        companyField = new JTextField(10);
        companyPanel.add(new JLabel("Company: "));
        companyPanel.add(companyField);

        JPanel flightNumberPanel = new JPanel();
        flightNumberField = new JTextField(10);
        flightNumberPanel.add(new JLabel("Flight Number: "));
        flightNumberPanel.add(flightNumberField);

        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.add(okButton);
        controlPanel.add(cancelButton);

        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.add(companyPanel);
        c.add(flightNumberPanel);
        c.add(controlPanel);

        setSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(app);

    }

    public void delayFlight() {
        String company = companyField.getText();
        int flightNumber = Integer.parseInt(flightNumberField.getText());

        app.changeFlightStatus(company, flightNumber, Flight.DELAYED);

        app.appendToDisplayArea("Flight " + company + flightNumber + " delayed\n");

    }

}
