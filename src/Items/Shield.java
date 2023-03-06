package Items;

import Pokemons.Pokemon;
import org.jetbrains.annotations.NotNull;

public class Shield implements Item {
    private static Shield singleton;

    public static Item getInstance() {
        if (singleton == null)
            singleton = new Shield();
        return singleton;
    }

    @Override
    public void equip(@NotNull Pokemon pokemon) {
        if (pokemon.getItems().contains(this)) return;
        pokemon.setDefense(pokemon.getDefense() + 2);
        pokemon.setSpecialDefense(pokemon.getSpecialDefense() + 2);
        pokemon.getItems().add(this);
    }

    @Override
    public void divest(@NotNull Pokemon pokemon) {
        if (!pokemon.getItems().remove(this)) return;
        pokemon.setDefense(pokemon.getDefense() - 2);
        pokemon.setSpecialDefense(pokemon.getSpecialDefense() - 2);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
