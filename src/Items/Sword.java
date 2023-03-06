package Items;

import Pokemons.Pokemon;
import org.jetbrains.annotations.NotNull;

public class Sword implements Item {
    private static Sword singleton;

    public static Item getInstance() {
        if (singleton == null)
            singleton = new Sword();
        return singleton;
    }

    @Override
    public void equip(@NotNull Pokemon pokemon) {
        if (pokemon.getItems().contains(this)) return;
        if (pokemon.getAttack().getType() != 0) return;
        pokemon.getAttack().setValue(pokemon.getAttack().getValue() + 3);
        pokemon.getItems().add(this);
    }

    @Override
    public void divest(@NotNull Pokemon pokemon) {
        if (!pokemon.getItems().remove(this)) return;
        pokemon.getAttack().setValue(pokemon.getAttack().getValue() - 3);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
