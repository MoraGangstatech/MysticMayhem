package cli.functions.prepare;

import cli.CliFunction;
import game.Game;
import game.Player;
import game.characters.Character;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SellCharacter extends CliFunction {
    public SellCharacter() {
        super("SellCharacter");
    }

    @Override
    public void call(Game game) {
        Player owner = game.getActivePlayer();
        ArrayList<Character> characters = owner.getArmy().getCharacters();
        System.out.printf("Gold coin balance: %s\n", owner.getGoldCoins());
        System.out.println("Select character to sell:");
        for (int i = 0; i < characters.size(); i++) {
            System.out.printf("%d. %s\n", i, characters.get(i).toString());
        }
        System.out.printf("%d. Cancel\n", characters.size());
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter character number: ");
            int charNumber = scanner.nextInt();
            if (charNumber >= 0 && charNumber < characters.size()) {
                owner.sellCharacter(characters.get(charNumber));
            } else if (charNumber != characters.size()) {
                System.out.println("Invalid character. No character by that number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid character. Only enter the character number (e.g., 1).");
        }
    }
}
