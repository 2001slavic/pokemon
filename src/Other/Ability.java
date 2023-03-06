package Other;

public class Ability {
    private int damage;
    private boolean stun;
    private boolean dodge;
    private int cooldown;
    private int currCooldown;

    public Ability(int damage, boolean stun, boolean dodge, int cooldown) {
        this.damage = damage;
        this.stun = stun;
        this.dodge = dodge;
        this.cooldown = cooldown;
        this.currCooldown = 0;
    }

    public Ability() {
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isStun() {
        return stun;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public boolean isDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getCurrCooldown() {
        return currCooldown;
    }

    public void setCurrCooldown(int currCooldown) {
        this.currCooldown = currCooldown;
    }

    @Override
    public String toString() {
        return "\t\tAbility:\n" +
                "\t\t\tDamage: " + damage +
                "\n\t\t\tStun: " + stun +
                "\n\t\t\tDodge: " + dodge +
                "\n\t\t\tCooldown: " + cooldown +
                '\n';
    }
}
