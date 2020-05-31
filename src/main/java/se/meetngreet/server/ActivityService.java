package se.meetngreet.server;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

@Service
public class ActivityService {


    private static String databaseUrl = "jdbc:mysql://mysql.dsv.su.se:3306/malj8519";
    private static String databaseUsername = "malj8519";
    private static String databasePassword = "gie1Noa7eam8";

    public static void addActivity(Activity activity) throws SQLException {

        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);

        // Set query string. ? is replaces by User parameters.
        String query = "INSERT INTO activity (activity_id, name, time, description, category_of_activity, min_age, max_age, group_size, allowed_gender, location, coordinates) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.
        pStatement.setInt(1, activity.getActivityId());
        pStatement.setString(2, activity.getName());
        pStatement.setString(3, activity.getTime());
        pStatement.setString(4, activity.getDescription());
        pStatement.setString(5, activity.getCategoryOfActivity().toString());
        pStatement.setInt(6, activity.getMinAge());
        pStatement.setInt(7, activity.getMaxAge());
        pStatement.setInt(8, activity.getGroupSize());
        pStatement.setString(9, activity.getAllowedGender().toString());
        pStatement.setString(10, activity.getLocation());
        pStatement.setString(11, activity.getCoordinates());

        // Executes the query and saves number of rows effected in resultSet.
        pStatement.executeUpdate();

        // Closes connection.
        pStatement.close();
        connection.close();
    }

    public static void updateActivity(int userID, ActivityPayload payload) throws SQLException {
        String type;
        switch (payload.getType()){
            case "name": type = "name"; break;
            case "time": type = "time"; break;
            case "description": type = "description"; break;
            case "categoryOfActivity": type = "category_of_activity"; break; //Vid enum ligger omvandling i set-metod från String till enum i Userklassen.
            case "minAge": type = "min_age"; break;
            case "maxAge": type = "max_age"; break;
            case "groupSize": type = "group_size"; break;
            case "allowedGender": type = "allowed_gender"; break;
            case "location": type = "location"; break;
            case "coordinates": type = "coordinates"; break;
            default: type = null; break;
        }

        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);

        // Set query string. ? is replaces by User parameters.
        String query = "UPDATE activity SET " + type + " = ? WHERE activity_id=?;";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.
        //pStatement.setString(1, payload.getType());

        pStatement.setString(1, payload.getData());
        pStatement.setInt(2, userID);
        // Executes the query and saves response in resultSet.
        pStatement.executeUpdate();
        // Closes connection.
        pStatement.close();
        connection.close();
    }

    public static void deleteActivity(int activityID) throws SQLException {
        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);

        // Set query string. ? is replaces by User parameters.
        String query = "DELETE FROM activity WHERE activity_id=?;";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.
        pStatement.setInt(1, activityID);

        // Executes the query and saves number of rows effected in resultSet.
        pStatement.executeUpdate();


        // Closes connection.
        pStatement.close();
        connection.close();
    }

    public static Activity getActivity(int activityID) throws SQLException {
        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);

        // Set query string. ? is replaces by User parameters.
        String query = "SELECT * FROM activity WHERE activity_id=?;";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.
        pStatement.setInt(1, activityID);

        // Executes the query and saves response in resultSet.
        ResultSet resultSet = pStatement.executeQuery();

        // Closes connection.
        pStatement.close();
        connection.close();

        // Recreate activity from database data.
        Activity activity = reassembleActivity(resultSet);

        // Returns the recreated activity.
        return activity;
    }

    public static Activity reassembleActivity(ResultSet resultSet) throws SQLException {
        int activityId = 0, minAge = 0, maxAge = 0, groupSize = 0;
        String name = null, time = null, description = null, categoryOfActivity = null, allowedGender = null, location = null, coordinates = null;
        while (resultSet.next()) {
            activityId = resultSet.getInt("activity_id");
            name = resultSet.getString("name");
            time = resultSet.getString("time");
            description = resultSet.getString("description");
            categoryOfActivity = resultSet.getString("category_of_activity");
            minAge = resultSet.getInt("min_age");
            maxAge = resultSet.getInt("max_age");
            groupSize = resultSet.getInt("group_size");
            allowedGender = resultSet.getString("allowed_gender");
            location = resultSet.getString("location");
            coordinates = resultSet.getString("coordinates");
        }
        Activity activity = new Activity(name, time, description, categoryOfActivity, minAge, maxAge, groupSize, allowedGender, location, coordinates);
        activity.setActivityId(activityId);

        return activity;
    }

    public static ArrayList<Activity> getAllActivities() throws SQLException {
        ArrayList<Activity> allActivities = new ArrayList<>();

        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);

        // Set query string. ? is replaces by User parameters.
        String query = "SELECT * FROM activity;";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.


        // Executes the query and saves response in resultSet.
        ResultSet resultSet = pStatement.executeQuery();

        int activityId = 0, minAge = 0, maxAge = 0, groupSize = 0;
        String name = null, time = null, description = null, categoryOfActivity = null, allowedGender = null, location = null, coordinates = null;
        while (resultSet.next()) {
            activityId = resultSet.getInt("activity_id");
            name = resultSet.getString("name");
            time = resultSet.getString("time");
            description = resultSet.getString("description");
            categoryOfActivity = resultSet.getString("category_of_activity");
            minAge = resultSet.getInt("min_age");
            maxAge = resultSet.getInt("max_age");
            groupSize = resultSet.getInt("group_size");
            allowedGender = resultSet.getString("allowed_gender");
            location = resultSet.getString("location");
            coordinates = resultSet.getString("coordinates");
            Activity activity = new Activity(name, time, description, categoryOfActivity, minAge, maxAge, groupSize, allowedGender, location, coordinates);
            activity.setActivityId(activityId);

            allActivities.add(activity);
        }
        // Closes connection.

        pStatement.close();
        connection.close();


        return allActivities;
    }

    public static void participateActivity(int userID, int activityID) throws SQLException {
        // ArrayList to add temporary values to.
        //ArrayList<String> inDB = new ArrayList<>();

        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);
        //String query = "SELECT * FROM activity;";
        String queryParticipate = "INSERT INTO partisipant (user_id, activity_id) VALUES (?, ?);";

        // Prepared statement from query String.
        //PreparedStatement pStatement = connection.prepareStatement(query);
        //PreparedStatement pStatementAdd = connection.prepareStatement(queryAdd);
        PreparedStatement pStatementParticipate = connection.prepareStatement(queryParticipate);

        // Parameters replacing ? in query string.
        //pStatementAdd.setString(1, interest.getName());
        pStatementParticipate.setInt(1, userID);
        pStatementParticipate.setInt(2, activityID);

        // Executes the query and saves response in resultSet.
        //ResultSet resultSet = pStatement.executeQuery();

        // Adds result from query in inDB and adds name if it exists.
        /*while (resultSet.next()) {
            inDB.add(resultSet.getString("name"));
        }*/
        // If interest doesn't exist in inDB, add interest.
        /*if (!inDB.contains(interest.getName()))   //!inDB.contains(interest)
            pStatementAdd.executeUpdate();*/

        // Add userID and interest name to table has_interest to connect user with interest.
        pStatementParticipate.executeUpdate();

        // Closes connection.
        pStatementParticipate.close();
        connection.close();
    }

    public static void unParticipateActivity(int userID, int activityID) throws SQLException {
        // ArrayList to add temporary values to.
        //ArrayList<String> inDB = new ArrayList<>();

        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);
        //String query = "SELECT * FROM activity;";
        String queryParticipate = "DELETE FROM partisipant WHERE user_id=? AND activity_id=?;";

        // Prepared statement from query String.
        //PreparedStatement pStatement = connection.prepareStatement(query);
        //PreparedStatement pStatementAdd = connection.prepareStatement(queryAdd);
        PreparedStatement pStatementParticipate = connection.prepareStatement(queryParticipate);

        // Parameters replacing ? in query string.
        //pStatementAdd.setString(1, interest.getName());
        pStatementParticipate.setInt(1, userID);
        pStatementParticipate.setInt(2, activityID);

        // Executes the query and saves response in resultSet.
        //ResultSet resultSet = pStatement.executeQuery();

        // Adds result from query in inDB and adds name if it exists.
        /*while (resultSet.next()) {
            inDB.add(resultSet.getString("name"));
        }*/
        // If interest doesn't exist in inDB, add interest.
        /*if (!inDB.contains(interest.getName()))   //!inDB.contains(interest)
            pStatementAdd.executeUpdate();*/

        // Add userID and interest name to table has_interest to connect user with interest.
        pStatementParticipate.executeUpdate();

        // Closes connection.
        pStatementParticipate.close();
        connection.close();
    }

    public static ArrayList<Integer> getParticipants(int ActivityID) throws SQLException {
        ArrayList<Integer> allParticipants = new ArrayList<>();

        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);

        // Set query string. ? is replaces by User parameters.
        String query = "SELECT user_id FROM partisipant WHERE activity_id=?;";
        // Prepared statement from query String.

        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.
        pStatement.setInt(1, ActivityID);

        // Executes the query and saves response in resultSet.
        ResultSet resultSet = pStatement.executeQuery();

        while (resultSet.next()) {
            allParticipants.add(resultSet.getInt("user_id"));
        }
        // Closes connection.

        pStatement.close();
        connection.close();


        return allParticipants;
    }

}