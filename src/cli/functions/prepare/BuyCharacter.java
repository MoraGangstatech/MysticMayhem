package cli.functions.prepare;

import cli.CliFunction;
import game.Game;
import game.Player;
import game.characters.Character;
import game.characters.archer.*;
import game.characters.healer.*;
import game.characters.knight.*;
import game.characters.mage.*;
import game.characters.mythicalcreature.*;
import game.exeptions.InvalidCharacterException;
import game.exeptions.NotEnoughGoldException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BuyCharacter extends CliFunction {
    public BuyCharacter() {
        super("Buy Character");
    }

    @Override
    public void call(Game game) {
        Player owner = game.getActivePlayer();
        int startingGoldCoins = (int) owner.getGoldCoins();
        Character[] characters = new Character[]{
                new Shooter(),
                new Ranger(),
                new Sunfire(),
                new Zing(),
                new Saggitarius(),
                new Squire(),
                new Cavalier(),
                new Templar(),
                new Zoro(),
                new Swiftblade(),
                new Warlock(),
                new Illusionist(),
                new Enchanter(),
                new Conjurer(),
                new Eldritch(),
                new Soother(),
                new Medic(),
                new Alchemist(),
                new Saint(),
                new Lightbringer(),
                new Dragon(),
                new Basilisk(),
                new Hydra(),
                new Phoenix(),
                new Pegasus()
        };
        System.out.println("Select character to buy:");
        for (int i = 0; i < characters.length; i++) {
            System.out.printf("%d. %s\n", i, characters[i]);
        }
        System.out.printf("%d. Cancel\n", characters.length);
        System.out.printf("░ Gold coin balance: %s\n", owner.getGoldCoins());
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter character number: ");
            int charNumber = scanner.nextInt();
            if (charNumber >= 0 && charNumber < characters.length) {
                owner.buyCharacter(characters[charNumber]);
                System.out.println("Character bought successfully.");
            } else if (charNumber != characters.length) {
                System.out.println("Invalid choice. No choice by that number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid choice. Only enter the choice number (e.g., 1).");
        } catch (NotEnoughGoldException e) {
            System.out.println("Insufficient gold coin balance.");
        } catch (InvalidCharacterException e) {
            System.out.println("A character of the same class is already present in the army.");
        }
        System.out.printf("░ Gold coin change: %+d\n", (int) owner.getGoldCoins() - startingGoldCoins);
    }
}
