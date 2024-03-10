package cli.functions;

import cli.CliFunction;
import game.Game;

public class Logoff extends CliFunction {
    public Logoff() {
        super("Logoff");
    }

    @Override
    public void call(Game game) {
        game.setActivePlayer(null);
        game.clearOpponent();
        System.out.println("Logged off.");
    }
}
