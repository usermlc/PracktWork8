package com.await.bdpw8.ui;

import com.await.bdpw8.da.UserDao;
import com.await.bdpw8.da.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void listUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();
        if (users.size() > 0) {
            for (User user : users) {
                System.out.println("ID: " + user.getId() + ", Ім'я: "
                        + user.getName() + ", Емайл: " + user.getEmail());
            }
        } else {
            System.out.println("Користувачів не знайдено!");
            Menu.menu();
        }
    }

    public void addUser() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть ім'я користувача");
        String name = scanner.nextLine();
        System.out.println("Ведіть електронну пошту");
        String email = scanner.nextLine();
        System.out.println("Введіть пароль");
        String password = scanner.nextLine();

        userDao.addUser(name, email, password);
        System.out.println("Користувача додано!");
    }

    public void updateUser() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        listUsers();

        System.out.print("Введіть ID користувача: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введіть нове ім'я користувача");
        String name = scanner.nextLine();

        System.out.println("Ведіть нову електронну пошту");
        String email = scanner.nextLine();

        System.out.println("Введіть новий пароль");
        String password = scanner.nextLine();

        userDao.updateUser(id, name, email, password);
        System.out.println("Користувача оновлено!");
    }

    public void deleteUser() throws SQLException {
        listUsers();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть ID користувача: ");
        int id = scanner.nextInt();
        userDao.deleteUser(id);
        System.out.println("Користувача видалено!");
    }

    public void findUserId() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введіть ID користувача: ");
            int id = scanner.nextInt();

            userDao.getUserById(id);
            System.out.println("Користувача знайдено Ім'я: " + userDao.getUserById(id).getName()
                    + " Емайл: " + userDao.getUserById(id).getEmail());
            Menu.menu();

        }catch (Exception e){
            System.out.println("Користувача не існує!");
            Menu.menu();
        }
    }
}
