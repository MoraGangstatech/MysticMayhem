package game.exeptions;

import java.io.Serial;

public class NotEnoughGoldException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -4457019409038143567L;

    public NotEnoughGoldException() {
    }

}
