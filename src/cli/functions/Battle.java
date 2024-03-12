package cli.functions;

import cli.CliFunction;
import exeptions.NotImplementedException;
import game.Game;
import game.Player;
import game.characters.archer.Shooter;

import java.util.Scanner;

public class Battle extends CliFunction {
    public Battle() {
        super("Battle");
    }

    @Override
    public void call(Game game) {
        Player opponent = new Player("GeraltofRivia", "whitewolf"); //for testing (GeraltofRivia should be default opponent)
        game.setcurrentOpponent(opponent);
        String BattleMenu = """
                0.Find new Opponent
                1.Start Battle
                2.Exit""";

        int command = 0;
        while(command == 0){
            System.out.println("Your Opponent : "+ opponent.getUsername() + "   Xp : "+ opponent.getXp());
            System.out.println(BattleMenu);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter command number: ");
            command = scanner.nextInt();

            switch (command) {
                case 0:
                    game.clearOpponent();
                    opponent = game.findOpponent();
                    game.setcurrentOpponent(opponent);
                    if (opponent == null) {
                        System.out.println("No available opponents. Trying again...");
                    }
                    break;
                case 1:
                    // battle
                    break;
                case 2:
                    // Exit
                    break;
                default:
                    System.out.println("Invalid command number");
            }
        }
    }
}
