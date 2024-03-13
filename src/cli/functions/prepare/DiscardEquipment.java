package cli.functions.prepare;

import cli.CliFunction;
import game.Game;
import game.Player;
import game.characters.Character;
import game.equipments.Equipment;
import game.equipments.EquipmentType;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DiscardEquipment extends CliFunction {
    public DiscardEquipment() {
        super("Discard Equipment");
    }

    @Override
    public void call(Game game) {
        Player owner = game.getActivePlayer();
        int startingGoldCoins = (int) owner.getGoldCoins();
        ArrayList<Character> characters = owner.getArmy().getCharacters();
        System.out.println("Select equipment to sell:");
        ArrayList<AbstractMap.SimpleEntry<Character, EquipmentType>> equipments = new ArrayList<>();
        for (Character character : characters) {
            Equipment armour = character.getArmour();
            Equipment artefact = character.getArtefact();
            if (armour != null) {
                equipments.add(new AbstractMap.SimpleEntry<>(character, armour.getType()));
                System.out.printf("%d. %s -> %s\n", equipments.size() - 1, character.getName(), armour);
            }
            if (artefact != null) {
                equipments.add(new AbstractMap.SimpleEntry<>(character, artefact.getType()));
                System.out.printf("%d. %s -> %s\n", equipments.size() - 1, character.getName(), artefact);
            }
        }
        System.out.printf("%d. Cancel\n", equipments.size());
        System.out.printf("░ Gold coin balance: %s\n", owner.getGoldCoins());
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter equipment number: ");
            int equNumber = scanner.nextInt();
            if (equNumber >= 0 && equNumber < equipments.size()) {
                Character character = equipments.get(equNumber).getKey();
                EquipmentType equipmentType = equipments.get(equNumber).getValue();
                owner.discardEquipment(equipmentType, character);
                System.out.println("Equipment discarded successfully.");
            } else if (equNumber != characters.size()) {
                System.out.println("Invalid equipment. No equipment by that number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid choice. Only enter the choice number (e.g., 1).");
        }
        System.out.printf("░ Gold coin change: %+d\n", (int) owner.getGoldCoins() - startingGoldCoins);
    }
}

