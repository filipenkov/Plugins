package telecontact.ua;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationPopup{

    public void showDialog(String issueKey, String event, String eventIniciator, String eventBody) {
        String header = "У вас новое уведомление.";
        String message = "Пользователь "+eventIniciator+" "+event+" запрос "+issueKey;
        JDialog jDialog = new JDialog();
        jDialog.setSize(300, 125);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(jDialog.getGraphicsConfiguration());// height of the task bar
        jDialog.setLocation(screenSize.width - jDialog.getWidth()-10, screenSize.height - toolHeight.bottom - jDialog.getHeight()-10);
        jDialog.setUndecorated(true);
        jDialog.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel headingLabel = new JLabel(header);
        //ImageIcon icon = new ImageIcon("src/images/jira.jpg");

        //headingLabel.setIcon(headingIcon); // --- use image icon you want to be as heading image.
        headingLabel.setOpaque(false);
        jDialog.add(headingLabel, constraints);

        constraints.gridx++;
        constraints.weightx = 0f;
        constraints.weighty = 0f;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;

        JButton closeButton = new JButton("X");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialog.dispose();
            }
        });

        closeButton.setMargin(new Insets(1, 4, 1, 4));
        closeButton.setFocusable(false);
        jDialog.add(closeButton, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;
        JLabel messageLabel = new JLabel("<html><font color='black'>" + message+"</font></html>",JLabel.CENTER);
        jDialog.add(messageLabel, constraints);
        jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jDialog.setVisible(true);
        jDialog.setAlwaysOnTop(true);
    }
}