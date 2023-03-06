package Items;

import Pokemons.Pokemon;
import org.jetbrains.annotations.NotNull;

public class Vitamins implements Item {
    private static Vitamins singleton;

    public static Item getInstance() {
        if (singleton == null)
            singleton = new Vitamins();
        return singleton;
    }

    @Override
    public void equip(@NotNull Pokemon pokemon) {
        if (pokemon.getItems().contains(this)) return;
        pokemon.setHP(pokemon.getHP() + 2);
        pokemon.getAttack().setValue(pokemon.getAttack().getValue() + 2);
        pokemon.getItems().add(this);
    }

    @Override
    public void divest(@NotNull Pokemon pokemon) {
        if (!pokemon.getItems().remove(this)) return;
        pokemon.setHP(pokemon.getHP() - 2);
        pokemon.getAttack().setValue(pokemon.getAttack().getValue() - 2);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
