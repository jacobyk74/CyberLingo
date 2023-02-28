import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginHelper {

    private static final String endpoint = "cyberlingo.c2e35o45yt71.us-east-1.rds.amazonaws.com";
    private static String databaseUsername;  // TODO
    private static String databasePassword;

    public static boolean login(String username, String password) {

        try {
            Connection conn = (Connection) DriverManager.getConnection("jbdc:mysql://" + endpoint + ":3306/cyberlingo", databaseUsername, databasePassword);

            PreparedStatement st = (PreparedStatement) conn.prepareStatement("SELECT user_auth(?, ?)");
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                // TODO need to check whether returned true or false
                return rs.getBoolean(0);
            }
            else {
                return false;
            }
        }
        catch (SQLException sqlException) {
            return false;
        }

    }

    public static boolean createNewUser(String username, String password) {

        try {
            Connection conn = (Connection) DriverManager.getConnection("jbdc:mysql://" + endpoint + ":3306/cyberlingo", databaseUsername, databasePassword);

            PreparedStatement st = (PreparedStatement) conn.prepareStatement("CALL user_create(?, ?)");
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException sqlException) {
            return false;
        }

    }
}
