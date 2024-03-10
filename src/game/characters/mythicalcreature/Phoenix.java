package game.characters.mythicalcreature;

import game.Player;
import game.characters.Category;
import game.characters.MythicalCreature;

public class Phoenix extends MythicalCreature {
    public Phoenix(Player owner) {
        super(owner, "Phoenix", Category.Sunchildren, 275, 17, 13, 17, 19);
    }
}
