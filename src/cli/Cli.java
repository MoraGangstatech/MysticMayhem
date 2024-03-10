package cli;

import exeptions.NotImplementedException;
import game.Game;
import game.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cli {
    private final Game game;
    private final Map<Integer, String> commands;
    private final String playersFile = "players.ser";

    public Cli() {
        game = new Game(retrievePlayers());
        commands = new HashMap<>();
        commands.put(1, "login");
        commands.put(2, "exit");
//        storePlayers(game.getPlayers());
    }

    public void begin() {
        boolean exit = false;
        while (!exit) {
            switch (getCommand()) {
                case "login":
                    login();
                    break;
                case "battle":
                    battle();
                    break;
                case "logoff":
                    logoff();
                    break;
                case "prepare":
                    prepare();
                    break;
                case "profile":
                    profile();
                    break;
                case "exit":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private String getCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Available commands:");
        for (Map.Entry<Integer, String> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.print("Enter command number: ");
        int commandNumber = scanner.nextInt();
        return commands.getOrDefault(commandNumber, null);
    }

    private void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        for (Player player : game.getPlayers()) {
            if (player.getUsername().equals(username)) {
                game.setActivePlayer(player);
                System.out.println("Welcome back, " + player.getName() + "!");
            }
        }
        if (game.getActivePlayer() == null) {
            System.out.println("Player does not exist. Creating a new profile...");
            System.out.print("Enter player name: ");
            String playerName = scanner.nextLine();
            Player newPlayer = new Player(playerName, username);
            game.addNewPlayer(newPlayer);
            game.setActivePlayer(newPlayer);
            storePlayers(game.getPlayers());
            System.out.println("New player registered with id: " + newPlayer.getUserID());
        }

        commands.remove(1);
        commands.put(3, "battle");
        commands.put(4, "logoff");
        commands.put(5, "prepare");
        commands.put(6, "profile");
    }

    private void profile() {
        if (game.getActivePlayer() == null) {
            System.out.println("Please log in first.");
            return;
        }

        System.out.println("Player Profile:");
        System.out.println("Name: " + game.getActivePlayer().getName());
        System.out.println("Username: " + game.getActivePlayer().getUsername());
        System.out.println("GoldCoins: " + game.getActivePlayer().getGoldCoins());
        System.out.println("Xp: " + game.getActivePlayer().getXp());
//        System.out.println("Army: " + game.getActivePlayer().getArmy());

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

    private void prepare() {
        // TODO: implement prepare
        // Allows players to buy/sell equipments and modify their armies.
        // Directly update using player methods
        throw new NotImplementedException();
    }

    private void battle() {
        // TODO: implement battle in Cli
        // Must call game.findOpponent() until user is satisfied.
        // Then call game.battle() and print battle records
        throw new NotImplementedException();
    }

    private void logoff() {
        game.setActivePlayer(null);
        game.clearOpponent();
        System.out.println("Logged off.");

        commands.put(1, "login");
        commands.remove(3);
        commands.remove(4);
        commands.remove(5);
        commands.remove(6);
    }

    private ArrayList<Player> retrievePlayers() {
        ArrayList<Player> players = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(playersFile))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?> deserializedList) {
                for (Object element : deserializedList) {
                    if (element instanceof Player) {
                        players.add((Player) element);
                    } else {
                        System.out.println("Invalid player data found.");
                    }
                }
            } else {
                System.out.println("Invalid player data found.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No player data found.");
        }
        return players;
    }


    private void storePlayers(ArrayList<Player> players) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(playersFile))) {
            oos.writeObject(players);
        } catch (IOException e) {
            System.out.println("Error occurred when storing player data.");
            System.out.println(e.getMessage());
        }
    }
}
