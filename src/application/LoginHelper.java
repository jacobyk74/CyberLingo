package application;

import java.sql.*;

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
            sqlException.printStackTrace();  // TODO get rid of after testing
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
//            System.out.println("Statement was executed"); // TODO remove after testing

            return true;

        }
        catch (SQLException sqlException) {
//            String state = sqlException.getSQLState();
//            if (state.equals("45000")) {
//                System.out.println("Username already taken");
//                return false;
//            }
//            else if (state.equals("1602")) {
//                System.out.println("Email already taken");
//                return false;
//            }
//            System.out.println("Something else went wrong idk");
            return false;
        }

    }
}
