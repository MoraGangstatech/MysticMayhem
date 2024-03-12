package game.characters.healer;

import game.Player;
import game.characters.Category;
import game.characters.Healer;

public class Soother extends Healer {
    public Soother(Player owner) {
        super(owner, "Soother", Category.Sunchildren, 95, 10, 8, 9, 8);
    }
}
