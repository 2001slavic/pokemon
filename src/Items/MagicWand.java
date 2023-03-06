package Items;

import Pokemons.Pokemon;
import org.jetbrains.annotations.NotNull;

public class MagicWand implements Item {
    private static MagicWand singleton;

    public static Item getInstance() {
        if (singleton == null)
            singleton = new MagicWand();
        return singleton;
    }

    @Override
    public void equip(@NotNull Pokemon pokemon) {
        if (pokemon.getItems().contains(this)) return;
        if (pokemon.getAttack().getType() != 1) return;
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
        return "Magic wand";
    }
}
