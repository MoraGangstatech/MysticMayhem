package cli;

import cli.functions.Battle;
import cli.functions.Login;
import cli.functions.Logoff;
import cli.functions.Profile;
import cli.functions.prepare.BuyCharacter;
import cli.functions.prepare.BuyEquipment;
import cli.functions.prepare.DiscardEquipment;
import cli.functions.prepare.SellCharacter;
import game.Game;
import game.Player;

import java.io.*;
import java.util.ArrayList;

public class Cli {
    private final String playersFile = "players.ser";

    public Cli() {
    }

    public void begin() {
        String banner = """
                            
                █   __    __     __  __     ______     ______   __     ______                      █
                █  /\\ "-./  \\   /\\ \\_\\ \\   /\\  ___\\   /\\__  _\\ /\\ \\   /\\  ___\\                     █
                █  \\ \\ \\-./\\ \\  \\ \\____ \\  \\ \\___  \\  \\/_/\\ \\/ \\ \\ \\  \\ \\ \\____                    █
                █   \\ \\_\\ \\ \\_\\  \\/\\_____\\  \\/\\_____\\    \\ \\_\\  \\ \\_\\  \\ \\_____\\                   █
                █    \\/_/  \\/_/   \\/_____/   \\/_____/     \\/_/   \\/_/   \\/_____/                   █
                █                                                                                  █
                █            __    __     ______     __  __     __  __     ______     __    __     █
                █           /\\ "-./  \\   /\\  __ \\   /\\ \\_\\ \\   /\\ \\_\\ \\   /\\  ___\\   /\\ "-./  \\    █
                █           \\ \\ \\-./\\ \\  \\ \\  __ \\  \\ \\____ \\  \\ \\  __ \\  \\ \\  __\\   \\ \\ \\-./\\ \\   █
                █            \\ \\_\\ \\ \\_\\  \\ \\_\\ \\_\\  \\/\\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_\\ \\ \\_\\  █
                █             \\/_/  \\/_/   \\/_/\\/_/   \\/_____/   \\/_/\\/_/   \\/_____/   \\/_/  \\/_/  █
                █                                                                                  █
                """;
        System.out.println(banner);
        Game game = new Game(retrievePlayers());
        CliMenu mainMenu = new CliMenu(
                "Main",
                new CliFunction[]{
                        new Login()
                },
                new CliFunction[]{
                        new Profile(),
                        new CliMenu(
                                "Prepare",
                                new CliFunction[]{},
                                new CliFunction[]{
                                        new BuyCharacter(),
                                        new SellCharacter(),
                                        new BuyEquipment(),
                                        new DiscardEquipment()
                                }
                        ),
                        new Battle(),
                        new Logoff()
                });
        mainMenu.call(game);
        storePlayers(game.getPlayers());
        System.out.println(banner);
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
