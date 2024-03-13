package cli.functions;

import cli.CliFunction;
import game.Game;
import game.Player;
import game.characters.Character;

import java.util.Scanner;

public class Profile extends CliFunction {
    public Profile() {
        super("Profile");
    }

    @Override
    public void call(Game game) {
        Player player = game.getActivePlayer();
        System.out.println("Player Profile:");
        System.out.println("Name        : " + player.getName());
        System.out.println("Username    : " + player.getUsername());
        System.out.println("GoldCoins   : " + player.getGoldCoins());
        System.out.println("Xp          : " + player.getXp());
        System.out.println("Army:");
        for (Character character : player.getArmy().getCharacters()) {
            System.out.println(character.toString());
        }
        System.out.println();
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
