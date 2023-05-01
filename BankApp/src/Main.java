import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private final UserService userService;

    public Main() {
        this.userService = new UserService();
    }

    public static void main(String[] args) throws Exception {
        Main app = new Main();
        System.out.println("Welcome to BankApp!");
        boolean loggedIn = false;
        while (!loggedIn) { // keep looping until login is successful
            System.out.println("Type 'login' if you have an existing account, otherwise type 'register'.");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch (input) {
                case "login" -> loggedIn = app.userService.loginAccount();
                case "register" -> app.userService.registerUserAccount();
                default -> System.out.println("Please type 'login' to login or 'register' to create an account.");
            }
        }


    }


}

