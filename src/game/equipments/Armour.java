package game.equipments;


class Chainmail extends Equipment {
    public Chainmail() {
        super("Chainmail", EquipmentType.Armour, 70, 0, 1, 0, -1);
    }
}

class Regalia extends Equipment {
    public Regalia() {
        super("Regalia", EquipmentType.Armour, 105, 0, 1, 0, 0);
    }
}

class Fleece extends Equipment {
    public Fleece() {
        super("Fleece", EquipmentType.Armour, 150, 0, 2, 1, -1);
    }
}
