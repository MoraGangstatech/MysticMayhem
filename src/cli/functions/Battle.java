package cli.functions;

import cli.CliFunction;
import exeptions.NotImplementedException;
import game.Game;

public class Battle extends CliFunction {
    public Battle() {
        super("Battle");
    }

    @Override
    public void call(Game game) {
        // TODO: implement call in Battle
        // Must call game.findOpponent() until user is satisfied.
        // Then call game.battle() and print battle records
        throw new NotImplementedException();
    }
}
