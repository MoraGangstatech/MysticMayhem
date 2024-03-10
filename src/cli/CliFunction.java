package cli;

import game.Game;

public abstract class CliFunction {
    private final String name;

    public CliFunction(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    public abstract void call(Game game);
}
