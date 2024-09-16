package org.example.optionsmanager;

import java.util.Scanner;

import static java.lang.System.exit;

public class Menue {
    private Option[] option;

    public Menue(Option[] option) {
        this.option = option;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 0; i < option.length; i++) {
                System.out.println((i + 1) + ". " + option[i].getDescription());
            }
            System.out.println("Enter your choice ( -1 to exit ): ");

            int choice = scanner.nextInt();

            if(choice == -1) {
                System.out.println("\nExiting the program ...");
                exit(0);
            }

            if (choice > 0 && choice <= option.length) {
                option[choice - 1].run();
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}
