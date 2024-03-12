package cli.functions.prepare;

import cli.CliFunction;
import game.Game;
import game.Player;
import game.characters.Character;
import game.equipments.Equipment;
import game.equipments.armour.Chainmail;
import game.equipments.armour.Fleece;
import game.equipments.armour.Regalia;
import game.equipments.artefact.Amulet;
import game.equipments.artefact.Crystal;
import game.equipments.artefact.Excalibur;
import game.exeptions.NotEnoughGoldException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BuyEquipment extends CliFunction {
    public BuyEquipment() {
        super("BuyEquipment");
    }

    @Override
    public void call(Game game) {
        Player owner = game.getActivePlayer();
        Equipment[] equipments = new Equipment[]{
                new Chainmail(),
                new Regalia(),
                new Fleece(),
                new Excalibur(),
                new Amulet(),
                new Crystal()
        };
        ArrayList<Character> characters = owner.getArmy().getCharacters();
        System.out.printf("Gold coin balance: %s\n", owner.getGoldCoins());
        try {
            Scanner scanner = new Scanner(System.in);

            Equipment equipment;
            System.out.println("Select equipment to buy:");
            for (int i = 0; i < equipments.length; i++) {
                System.out.printf("%d. %s\n", i, equipments[i]);
            }
            System.out.printf("%d. Cancel\n", equipments.length);
            System.out.print("Enter equipment number: ");
            int equNumber = scanner.nextInt();
            if (equNumber >= 0 && equNumber < equipments.length) {
                equipment = equipments[equNumber];
            } else {
                if (equNumber != equipments.length) {
                    System.out.println("Invalid equipment. No equipment by that number.");
                }
                return;
            }

            Character character;
            System.out.print("Select character to equip:");
            for (int i = 0; i < characters.size(); i++) {
                System.out.printf("%d. %s\n", i, characters.get(i).toString());
            }
            System.out.printf("%d. Cancel\n", characters.size());
            System.out.print("Enter character number: ");
            int charNumber = scanner.nextInt();
            if (charNumber >= 0 && charNumber < characters.size()) {
                character = characters.get(charNumber);
            } else {
                if (charNumber != characters.size()) {
                    System.out.println("Invalid character. No character by that number.");
                }
                return;
            }

            owner.buyEquipment(equipment, character);
        } catch (InputMismatchException e) {
            System.out.println("Invalid choice. Only enter the choice number (e.g., 1).");
        } catch (NotEnoughGoldException e) {
            System.out.println("Insufficient gold coin balance.");
        }
    }
}
