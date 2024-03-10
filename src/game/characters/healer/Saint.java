package game.characters.healer;

import game.Player;
import game.characters.Category;
import game.characters.Healer;

public class Saint extends Healer {
    public Saint(Player owner) {
        super(owner, "Saint", Category.Mystics, 200, 16, 14, 17, 9);
    }
}
