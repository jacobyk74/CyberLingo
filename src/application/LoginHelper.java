package application;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class LoginHelper {

    private static final String endpoint = "cyberlingo.c2e35o45yt71.us-east-1.rds.amazonaws.com";
    private static final String databaseUsername = "guest_user";  // TODO
    private static final String databasePassword = "guest";

    public static boolean login(String username, String password) {

        try {

            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + endpoint + ":3306/cyberlingo", databaseUsername, databasePassword);

            PreparedStatement st = (PreparedStatement) conn.prepareStatement("SELECT user_auth(?, ?)");
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
            else {
                return false;
            }
        }
        catch (SQLException sqlException) {
            return false;
        }

    }

    public static boolean createNewUser(String email, String password, String username) {

        try {

            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + endpoint + ":3306/cyberlingo", databaseUsername, databasePassword);

            PreparedStatement st = conn.prepareStatement("CALL create_user(?, ?, ?)");
            st.setString(1, email);
            st.setString(2, password);
            st.setString(3, username);

            st.execute();

            return true;

        }
        catch (SQLException sqlException) {
            return false;
        }

    }

    public static HashMap<String, Integer> getScores() {

        try {
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + endpoint + ":3306/cyberlingo", databaseUsername, databasePassword);

            PreparedStatement st = conn.prepareStatement("CALL get_scores()");
            st.execute();

            ResultSet resultSet = st.getResultSet();
            HashMap<String, Integer> scores = new HashMap<>();
            while (resultSet.next()) {
                String username = resultSet.getString(1);
                Integer score = resultSet.getInt(2);

                scores.put(username, score);
            }

            return scores;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace(); // TODO remove after testing
            return null;
        }
    }

    public static void updateScore(String email, int score) {

        try {
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + endpoint + ":3306/cyberlingo", databaseUsername, databasePassword);

            PreparedStatement st = conn.prepareStatement("CALL update_points(?, ?)");
            st.setString(1, email);
            st.setInt(2, score);

            st.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static List<String> getSpecificScore(String email) {

        try {
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://" + endpoint + ":3306/cyberlingo", databaseUsername, databasePassword);

            PreparedStatement st = conn.prepareStatement("CALL get_specific_score(?)");
            st.setString(1, email);

            st.execute();

            ResultSet resultSet = st.getResultSet();
            resultSet.next();
            String username = resultSet.getString(1);
            String totalScore = Integer.toString(resultSet.getInt(2));

            return List.of(username, totalScore);
        } catch (SQLException sqlException) {
            return null;

        }

    }
}
