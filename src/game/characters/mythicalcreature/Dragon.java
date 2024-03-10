package game.characters.mythicalcreature;

import game.Player;
import game.characters.Category;
import game.characters.MythicalCreature;

public class Dragon extends MythicalCreature {
    public Dragon(Player owner) {
        super(owner, "Dragon", Category.Sunchildren, 120, 12, 14, 15, 8);
    }
}
