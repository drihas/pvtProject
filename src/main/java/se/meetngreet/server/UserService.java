package se.meetngreet.server;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import static java.sql.DriverManager.*;
import static java.sql.DriverManager.registerDriver;

@Service
public class UserService {

    //private static ArrayList<User> users = new ArrayList<>();
    //private HashSet<User> users2 = new HashSet<>();

    private static String databaseUrl = "jdbc:mysql://mysql.dsv.su.se:3306/malj8519";
    private static String databaseUsername = "malj8519";
    private static String databasePassword = "gie1Noa7eam8";

    // Users
    public static void newUser(User user) throws SQLException {
        //Date date = new Date(year, month, day);
        /*users.add(new User(user.getFirstName(), user.getLastName(), user.getDateOfBirth(),
                user.getGender().toString(), user.getEmail(), user.getRelationshipStatus().toString(), user.getOccupation().toString(),
                user.getPlaceOfBirth(), user.getPlaceOfResidence(), user.getDescription()));
*/
        // Add new user to DB.
        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);


        // Set query string. ? is replaces by User parameters.
        String query = "INSERT INTO user (user_id, first_name, last_name, date_of_birth, gender, email, relationship_status, occupation, place_of_birth, place_of_residence, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.
        pStatement.setInt(1, user.getUserID());
        pStatement.setString(2, user.getFirstName());
        pStatement.setString(3, user.getLastName());
        pStatement.setString(4, user.getDateOfBirth());
        pStatement.setString(5, user.getGender().toString());
        pStatement.setString(6, user.getEmail());
        pStatement.setString(7, user.getRelationshipStatus().toString());
        pStatement.setString(8, user.getOccupation().toString());
        pStatement.setString(9, user.getPlaceOfBirth());
        pStatement.setString(10, user.getPlaceOfResidence());
        pStatement.setString(11, user.getDescription());

        // Executes the query and saves number of rows effected in resultSet.
        int resultSet = pStatement.executeUpdate();

        // Closes connection.
        pStatement.close();
        connection.close();

    }

    public static void updateUser(int userID, UserPayload payload) throws SQLException { //int userID, Payload payload) {

        String type;
        switch (payload.getType()){
            case "firstName": type = "first_name"; break;
            case "lastName": type = "last_name"; break;
            case "dateOfBirth": type = "date_of_birth"; break;
            case "gender": type = "gender"; break; //Vid enum ligger omvandling i set-metod från String till enum i Userklassen.
            case "email": type = "email"; break;
            case "relationshipStatus": type = "relationship_status"; break;
            case "occupation": type = "occupation"; break;
            case "placeOfBirth": type = "place_of_birth"; break;
            case "placeOfResidence": type = "place_of_residence"; break;
            case "description": type = "description"; break;
            default: type = null; break;
        }

        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);

        // Set query string. ? is replaces by User parameters.
        String query = "UPDATE user SET " + type + " = ? WHERE user_id=?;";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.
        //pStatement.setString(1, payload.getType());


        pStatement.setString(1, payload.getData());
        pStatement.setInt(2, userID);

        // Executes the query and saves response in resultSet.
        int resultSet = pStatement.executeUpdate();


        // Closes connection.
        pStatement.close();
        connection.close();
    }

    public static void deleteUser(int userId) throws SQLException {
        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);

        // Set query string. ? is replaces by User parameters.
        String query = "DELETE FROM user WHERE user_id=?;";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.
        pStatement.setInt(1, userId);

        // Executes the query and saves number of rows effected in resultSet.
        int resultSet = pStatement.executeUpdate();


        // Closes connection.
        pStatement.close();
        connection.close();


    }

    public static User getUser(int userId) throws SQLException {
        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);

        // Set query string. ? is replaces by User parameters.
        String query = "SELECT * FROM user WHERE user_id=?;";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.
        pStatement.setInt(1, userId);

        // Executes the query and saves response in resultSet.
        ResultSet resultSet = pStatement.executeQuery();

        /*int userID = 0;
        String firstName = null, lastName = null, dateOfBirh = null, gender = null, email = null, relationshipStatus = null, occupation = null, placeOfBirth = null, placeOfResidence = null, description = null;
        while (resultSet.next()) {
            userID = resultSet.getInt("user_id");
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            dateOfBirh = resultSet.getString("date_of_birth");
            gender = resultSet.getString("gender");
            email = resultSet.getString("email");
            relationshipStatus = resultSet.getString("relationship_status");
            occupation = resultSet.getString("occupation");
            placeOfBirth = resultSet.getString("place_of_birth");
            placeOfResidence = resultSet.getString("place_of_residence");
            description = resultSet.getString("description");
        }*/

        User user = reassembleUser(resultSet);


        // Closes connection.
        pStatement.close();
        connection.close();

        // Recreate user from database data.
        /*User user = new User(firstName, lastName, dateOfBirh, gender, email, relationshipStatus, occupation, placeOfBirth, placeOfResidence, description);
        user.setUserID(userID);*/

        // Returns the recreated user.


        return user;
    }

    public static int getUserId(String email) throws SQLException {
        int userID = 0;
        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);
        // Set query string. ? is replaces by User parameters.
        String query = "SELECT user_id FROM user WHERE email=?;";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.
        pStatement.setString(1, email);
        // Executes the query and saves response in resultSet.
        ResultSet resultSet = pStatement.executeQuery();
        pStatement.close();
        connection.close();

        while (resultSet.next())
            userID = resultSet.getInt("user_id");

        return userID;
    }

    //TODO: tas bort? Inte så nödvändig för appen. Bra under utveckling dock.
    public static ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> allUsers = new ArrayList<>();

        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);

        // Set query string. ? is replaces by User parameters.
        String query = "SELECT * FROM user;";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.


        // Executes the query and saves response in resultSet.
        ResultSet resultSet = pStatement.executeQuery();

        int userID = 0;
        String firstName = null, lastName = null, dateOfBirh = null, gender = null, email = null, relationshipStatus = null, occupation = null, placeOfBirth = null, placeOfResidence = null, description = null;

        while (resultSet.next()) {
            userID = resultSet.getInt("user_id");
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            dateOfBirh = resultSet.getString("date_of_birth");
            gender = resultSet.getString("gender");
            email = resultSet.getString("email");
            relationshipStatus = resultSet.getString("relationship_status");
            occupation = resultSet.getString("occupation");
            placeOfBirth = resultSet.getString("place_of_birth");
            placeOfResidence = resultSet.getString("place_of_residence");
            description = resultSet.getString("description");
            User user = new User(firstName, lastName, dateOfBirh, gender, email, relationshipStatus, occupation, placeOfBirth, placeOfResidence, description);
            user.setUserID(userID);
            allUsers.add(user);
        }
        // Closes connection.

        pStatement.close();
        connection.close();


        return allUsers;
    }

    public static User reassembleUser(ResultSet resultSet) throws SQLException {

        int userID = 0;
        String firstName = null, lastName = null, dateOfBirh = null, gender = null, email = null, relationshipStatus = null, occupation = null, placeOfBirth = null, placeOfResidence = null, description = null;
        while (resultSet.next()) {
            userID = resultSet.getInt("user_id");
            firstName = resultSet.getString("first_name");
            lastName = resultSet.getString("last_name");
            dateOfBirh = resultSet.getString("date_of_birth");
            gender = resultSet.getString("gender");
            email = resultSet.getString("email");
            relationshipStatus = resultSet.getString("relationship_status");
            occupation = resultSet.getString("occupation");
            placeOfBirth = resultSet.getString("place_of_birth");
            placeOfResidence = resultSet.getString("place_of_residence");
            description = resultSet.getString("description");
        }
        User user = new User(firstName, lastName, dateOfBirh, gender, email, relationshipStatus, occupation, placeOfBirth, placeOfResidence, description);
        user.setUserID(userID);

        return user;
    }

    // Interests
    public static void addInterest(int userID, Interest interest) throws SQLException {
        // ArrayList to add temporary values to.
        ArrayList<String> inDB = new ArrayList<>();

        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);
        // Set query string. ? is replaces by User parameters.
        String query = "SELECT * FROM interest;";
        String queryAdd = "INSERT INTO interest (name) VALUES (?);";
        String queryConnect = "INSERT INTO has_interest (user_id, name) VALUES (?, ?);";

        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        PreparedStatement pStatementAdd = connection.prepareStatement(queryAdd);
        PreparedStatement pStatementConnect = connection.prepareStatement(queryConnect);

        // Parameters replacing ? in query string.
        pStatementAdd.setString(1, interest.getName());
        pStatementConnect.setInt(1, userID);
        pStatementConnect.setString(2, interest.getName());

        // Executes the query and saves response in resultSet.
        ResultSet resultSet = pStatement.executeQuery();

        // Adds result from query in inDB and adds name if it exists.
        while (resultSet.next()) {
            inDB.add(resultSet.getString("name"));
        }
        // If interest doesn't exist in inDB, add interest.
        if (!inDB.contains(interest.getName()))   //!inDB.contains(interest)
            pStatementAdd.executeUpdate();

        // Add userID and interest name to table has_interest to connect user with interest.
        pStatementConnect.executeUpdate();

        // Closes connection.
        pStatement.close();
        connection.close();
    }

    public static String getInterest(int userID) throws SQLException {
        ArrayList<String> interests = new ArrayList<>();

        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);
        // Set query string. ? is replaces by User parameters.
        String query = "SELECT name FROM has_interest WHERE user_id=?;";

        PreparedStatement pStatement = connection.prepareStatement(query);
        pStatement.setInt(1, userID);
        ResultSet resultSet = pStatement.executeQuery();

        pStatement.close();
        connection.close();

        while (resultSet.next())
            interests.add(resultSet.getString("name"));

        return interests.toString();
    }

    public static void deleteInterest(int userID, Interest interest) throws SQLException {
        // Establish database connection.
        Connection connection = getConnection(databaseUrl, databaseUsername, databasePassword);

        // Set query string. ? is replaces by User parameters.
        String query = "DELETE FROM has_interest WHERE user_id=? AND name=?;";
        // Prepared statement from query String.
        PreparedStatement pStatement = connection.prepareStatement(query);
        // Parameters replacing ? in query string.
        pStatement.setInt(1, userID);
        pStatement.setString(2, interest.getName());

        // Executes the query and saves number of rows effected in resultSet.
        pStatement.executeUpdate();


        // Closes connection.
        pStatement.close();
        connection.close();

    }
}
