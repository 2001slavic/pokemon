package Items;

import Pokemons.Pokemon;
import org.jetbrains.annotations.NotNull;

public class Cape implements Item {
    private static Cape singleton;

    public static Item getInstance() {
        if (singleton == null)
            singleton = new Cape();
        return singleton;
    }

    @Override
    public void equip(@NotNull Pokemon pokemon) {
        if (pokemon.getItems().contains(this)) return;
        pokemon.setSpecialDefense(pokemon.getSpecialDefense() + 3);
        pokemon.getItems().add(this);
    }

    @Override
    public void divest(@NotNull Pokemon pokemon) {
        if (!pokemon.getItems().remove(this)) return;
        pokemon.setSpecialDefense(pokemon.getSpecialDefense() - 3);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
