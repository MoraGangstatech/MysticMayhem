package cli.functions;

import cli.CliFunction;
import game.Game;
import game.Player;

import java.util.Scanner;

public class Login extends CliFunction {
    public Login() {
        super("Login");
    }

    @Override
    public void call(Game game) {
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
            System.out.println("New player registered with id: " + newPlayer.getUserID());
        }
    }
}
