package Pokemons;

public class PokemonFactory {
    public Pokemon getPokemon(String name) {
        switch (name) {
            case "Bulbasaur" -> {return new Bulbasaur();}
            case "Charmander" -> {return new Charmander();}
            case "Eevee" -> {return new Eevee();}
            case "Jigglypuff" -> {return new Jigglypuff();}
            case "Meowth" -> {return new Meowth();}
            case "Neutrel1" -> {return new Neutrel1();}
            case "Neutrel2" -> {return new Neutrel2();}
            case "Pikachu" -> {return new Pikachu();}
            case "Psyduck" -> {return new Psyduck();}
            case "Snorlax" -> {return new Snorlax();}
            case "Squirtle" -> {return new Squirtle();}
            case "Vulpix" -> {return new Vulpix();}
            default -> {return null;}
        }
    }
}
