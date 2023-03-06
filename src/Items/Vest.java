package Items;

import Pokemons.Pokemon;
import org.jetbrains.annotations.NotNull;

public class Vest implements Item {
    private static Vest singleton;

    public static Item getInstance() {
        if (singleton == null)
            singleton = new Vest();
        return singleton;
    }

    @Override
    public void equip(@NotNull Pokemon pokemon) {
        if (pokemon.getItems().contains(this)) return;
        pokemon.setHP(pokemon.getHP() + 10);
        pokemon.getItems().add(this);
    }

    @Override
    public void divest(@NotNull Pokemon pokemon) {
        if (!pokemon.getItems().remove(this)) return;
        pokemon.setHP(pokemon.getHP() - 10);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
