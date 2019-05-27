package telecontact.ua;

import telecontact.ua.ConnectDataBase;
import telecontact.ua.NotificationPopup;
import java.net.BindException;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{

    private static String currentUser = System.getProperty("user.name");

    public static void openWebpage(String url) {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            URI oURL = new URI(url);
            desktop.browse(oURL);
        }catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Image image = Toolkit.getDefaultToolkit().getImage("src/images/trayIcon.png");
        SystemTray systemTray = SystemTray.getSystemTray();

        PopupMenu trayPopupMenu = new PopupMenu();
        trayPopupMenu.setFont(new Font("Times new roman",0,14));

        /*MenuItem action = new MenuItem("Action");
        action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NotificationPopup notificationPopup = new NotificationPopup();

                ConnectDataBase connectDataBase = new ConnectDataBase();
                HashMap<String,ArrayList> rows = connectDataBase.getRows(currentUser);
                while(true){
                    if(rows.size()>0){
                        String issueKey = rows.get(currentUser).get(0).toString();
                        String event = rows.get(currentUser).get(1).toString();
                        String eventIninciator = rows.get(currentUser).get(2).toString();
                        String eventBody = rows.get(currentUser).get(3).toString();
                        notificationPopup.showDialog(issueKey,event,eventIninciator,eventBody);
                    }
                    try{
                        Thread.sleep(10000);
                    }catch(Exception er){
                        er.printStackTrace();
                    }
                }
            }
        });
        trayPopupMenu.add(action);*/

        MenuItem open = new MenuItem("Открыть портал");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                openWebpage("http://tctestjira.telecontact.ua/secure/HelpDeskLoginAction!default.jspa");
            }
        });
        trayPopupMenu.add(open);

        MenuItem close = new MenuItem("Выход");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        trayPopupMenu.add(close);

        TrayIcon trayIcon = new TrayIcon(image, "JiraPortal", trayPopupMenu);
        trayIcon.setImageAutoSize(true);
        try{
            systemTray.add(trayIcon);
        }catch(AWTException awtException){
            awtException.printStackTrace();
        }

        NotificationPopup notificationPopup = new NotificationPopup();
        ConnectDataBase connectDataBase = new ConnectDataBase();

        while(true){
            HashMap<String,ArrayList> rows = connectDataBase.getRows(currentUser);
            if(rows.size()>0){
                String issueKey = rows.get(currentUser).get(0).toString();
                String event = rows.get(currentUser).get(1).toString();
                String eventIninciator = rows.get(currentUser).get(2).toString();
                String eventBody = rows.get(currentUser).get(3).toString();
                notificationPopup.showDialog(issueKey,event,eventIninciator,eventBody);
            }
            try{
                Thread.sleep(10000);
            }catch(Exception er){
                er.printStackTrace();
            }
        }

        //launch(args);
    }
}