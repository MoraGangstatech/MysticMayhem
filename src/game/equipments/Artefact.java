package game.equipments;


class Excalibur extends Equipment {
    public Excalibur() {
        super("Excalibur", EquipmentType.Artefact, 150, 2, 0, 0, 0);
    }
}

class Amulet extends Equipment {
    public Amulet() {
        super("Amulet", EquipmentType.Artefact, 200, 1, -1, 1, 1);
    }
}

class Crystal extends Equipment {
    public Crystal() {
        super("Crystal", EquipmentType.Artefact, 210, 2, 1, -1, -1);
    }
}
