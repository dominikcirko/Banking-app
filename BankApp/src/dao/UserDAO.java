package dao;

import dto.UserDTO;
import sql.DataSourceSingleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final UserDTO user;
    private static final String INSERT_USER = "INSERT INTO accounts (firstname, lastname, password, address, phonenumber, email, accountbalance) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_ALL_USERS = "SELECT email FROM accounts";
    private static final String GET_USER = "SELECT * FROM accounts where email = ?";

    // Singleton instance of the DataSource object
    private static final DataSource dataSource = DataSourceSingleton.getInstance();

    public UserDAO() {
        user = new UserDTO();
    }

    public void createAccount(UserDTO user) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhoneNumber());
            statement.setLong(7, user.getAccountBalance());
            statement.executeUpdate();
        }
    }

    public UserDTO getAccount(String email) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(GET_USER)) {

            UserDTO user = new UserDTO();
            user.setEmail(email);
            statement.setString(1, user.getEmail());
            // If there is a matching row, create a UserDTO object with the data from that row.
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    user.setEmail(rs.getString("email"));
                    return user;
                } else {
                    throw new RuntimeException("Invalid email.");
                }
            }
        }
    }


    public boolean doesAccountExist(String email) throws SQLException {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_USERS)) {

            ResultSet resultSet = statement.executeQuery();

            // Loop through the results to check if the provided email already exists
            while (resultSet.next()) {
                String dbEmail = resultSet.getString("email"); // Get the email from the database result
                if (dbEmail.equals(email)) { // Compare the email from the database to the provided email
                    return true;
                }
            }
            return false;
        }

    }

}
