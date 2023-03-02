import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                // TODO need to check whether returned true or false
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

    public static boolean createNewUser(String username, String password) {

        try {

            Connection conn = (Connection) DriverManager.getConnection("jbdc:mysql://" + endpoint + ":3306/cyberlingo", databaseUsername, databasePassword);

            PreparedStatement st = (PreparedStatement) conn.prepareStatement("SELECT user_create(?, ?)");  // TODO may need to change if I switch user_create to a procedure instead
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
