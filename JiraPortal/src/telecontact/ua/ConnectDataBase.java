package telecontact.ua;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnectDataBase {

    public HashMap<String,ArrayList> getRows(String user){
        HashMap<String,ArrayList> result = new HashMap<String, ArrayList>();
        ArrayList<String> info = new ArrayList();
        String reporter, issueKey, event, eventIniciator, eventBody;
        int isActual;
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://tc-db0.telecontact.ua:3306/jira_dir","portal_user","Q123456w");
                Statement statement = connection.createStatement();
                String query = "select * from portal_event where reporter='"+user+"'";
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next()){
                    reporter = resultSet.getString(1);
                    issueKey = resultSet.getString(2);
                    event = resultSet.getString(3);
                    eventIniciator = resultSet.getString(4);
                    eventBody = resultSet.getString(5);
                    isActual = resultSet.getInt(6);
                    if(isActual == 1){
                        info.add(issueKey);
                        info.add(event);
                        info.add(eventIniciator);
                        info.add(eventBody);
                        result.put(reporter,info);
                        return result;
                    }
                }
                connection.close();
            }catch(Exception e){
                System.out.println(e);
            }
        return result;
    }
    public void updateRow(String issueKey, String reporter){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://tc-db0.telecontact.ua:3306/battleship","portal_user","Q123456w");
            Statement statement = connection.createStatement();
            String query = "UPDATE portal_event SET is_actual='"+0+"' WHERE issue_key='"+issueKey+"'";
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}