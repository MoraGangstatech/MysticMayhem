package cli;

import game.Game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CliMenu extends CliFunction {
    private final CliFunction[] preLoginMenuItems;
    private final CliFunction[] postLoginMenuItems;

    public CliMenu(String name, CliFunction[] preLoginMenuItems, CliFunction[] postLoginMenuItems) {
        super(name);
        this.preLoginMenuItems = preLoginMenuItems;
        this.postLoginMenuItems = postLoginMenuItems;
    }

    @Override
    public void call(Game game) {
        CliFunction[] activeMenuItems;
        boolean exit = false;
        while (!exit) {
            if (game.getActivePlayer() == null) {
                activeMenuItems = preLoginMenuItems;
            } else {
                activeMenuItems = postLoginMenuItems;
            }
            System.out.printf("%s menu:\n", this.getName());
            for (int i = 0; i < activeMenuItems.length; i++) {
                System.out.printf("%d. %s\n", i, activeMenuItems[i].getName());
            }
            System.out.printf("%d. Exit this menu\n", activeMenuItems.length);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter command number: ");
            try {
                int commandNumber = scanner.nextInt();
                if (commandNumber >= 0 && commandNumber < activeMenuItems.length) {
                    System.out.println("╒═══════════════════════════════════╕");
                    activeMenuItems[commandNumber].call(game);
                    System.out.println("╘═══════════════════════════════════╛");
                } else if (commandNumber == activeMenuItems.length) {
                    exit = true;
                } else {
                    System.out.println("Invalid command. No command by that number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid command. Only enter the command number (e.g., 1).");
            }
        }
    }
}
