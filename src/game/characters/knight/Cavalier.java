package game.characters.knight;

import game.Player;
import game.characters.Category;
import game.characters.Knight;

public class Cavalier extends Knight {
    public Cavalier(Player owner) {
        super(owner, "Cavalier", Category.Highlanders, 110, 10, 12, 7, 10);
    }
}
