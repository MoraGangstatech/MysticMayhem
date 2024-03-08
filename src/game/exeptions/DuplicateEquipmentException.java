package game.exeptions;

import java.io.Serial;

public class DuplicateEquipmentException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2923900286944305405L;

    public DuplicateEquipmentException() {
    }

}

