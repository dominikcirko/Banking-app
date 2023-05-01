package service;

import com.sun.tools.javac.Main;
import dao.UserDAO;
import dto.UserDTO;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    private final UserDAO userDAO;
    private final UserDTO userDTO;

    public UserService() {
        userDAO = new UserDAO();
        userDTO = new UserDTO();
    }

    public void registerUserAccount() throws SQLException {
        System.out.println("Create new account");

        UserDTO user = getRegisterUserInput();

        if (!userDAO.doesAccountExist(user.getEmail())) userDAO.createAccount(user);
        else throw new RuntimeException("This email address is already in use");
    }

    public boolean loginAccount() throws Exception {

        System.out.println("Login");
        UserDTO user = getLoginUserInput();
        if (userDAO.doesAccountExist(user.getEmail())) {
            System.out.println("Login successful");
            userDAO.getAccount(user.getEmail());
            return true;
        } else {
            System.out.println("Account does not exist");
            return false;
        }

    }

    private UserDTO getLoginUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        userDTO.setEmail(email);
        userDTO.setPassword(password);
        return userDTO;

    }

    private UserDTO getRegisterUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("Phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Address: ");
        String address = scanner.nextLine();
        System.out.println("Account balance: ");
        long accountBalance = Long.parseLong(scanner.nextLine());

        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setAddress(address);
        userDTO.setAccountBalance(accountBalance);

        return userDTO;
    }

}
