package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;
import notebook.util.mapper.impl.UserMapper;

import java.util.Scanner;

import static notebook.util.mapper.Runner.HASH;

public class UserView {
    private final UserController userController;
    private String userId;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com;

        while (true) {
            String command = prompt("Введите команду: ").toUpperCase();
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    userId = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(userId));
                        System.out.println(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    userId = prompt("Enter user id: ");
                    userController.updateUser(userId, createUser());
                    break;
                case DELETE:
                    userId = prompt("Идентификатор пользователя: ");
                    if(userController.deleteuser(Long.parseLong(userId))) {
                        System.out.println("Пользователь удален");
                    }else {
                        System.out.println("Пользователя невозможно удалить");
                    }
                    break;
                case LIST:
                    System.out.println(getAvailableCommands());
                    break;
                default:
                    throw new UnsupportedOperationException("Команда не поддерживается");
            }
        }
    }

    private String getAvailableCommands() {
    return String.format("Список доступных команд:%n" );
    }


    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }



}
