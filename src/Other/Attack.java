package Other;

public class Attack {
    private int value;
    private final int type;

    /**
     * Clasa pentru a reprezenta damage-ul atacului si tipul acestuia.
     * @param value Valoarea atacului.
     * @param type Tipul atacului 0 - normal, 1 - special.
     */
    public Attack(int value, int type) {
        this.value = value;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
