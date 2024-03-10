package cli.functions;

import cli.CliFunction;
import game.Game;

import java.util.Scanner;

public class Profile extends CliFunction {
    public Profile() {
        super("Profile");
    }

    @Override
    public void call(Game game) {
        System.out.println("Player Profile:");
        System.out.println("Name        : " + game.getActivePlayer().getName());
        System.out.println("Username    : " + game.getActivePlayer().getUsername());
        System.out.println("GoldCoins   : " + game.getActivePlayer().getGoldCoins());
        System.out.println("Xp          : " + game.getActivePlayer().getXp());

        System.out.print("Do you want to change your name? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("y")) {
            System.out.print("Enter a new name: ");
            String newName = scanner.nextLine().trim();
            game.getActivePlayer().setName(newName);
            System.out.println("Name updated successfully!");
        } else {
            System.out.println("No changes made to the profile.");
        }
    }
}
