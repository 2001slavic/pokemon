package InputOutput;

import Items.Item;
import Items.ItemFactory;
import Other.Coach;
import Pokemons.Pokemon;
import Pokemons.PokemonFactory;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Generate {
    private final List<String> pokemons = Stream.of("Bulbasaur",
            "Charmander",
            "Eevee",
            "Jigglypuff",
            "Meowth",
            "Pikachu",
            "Psyduck",
            "Snorlax",
            "Squirtle",
            "Vulpix").toList();

    private final List<String> items = Stream.of("Cape",
            "Christmas tree",
            "Magic wand",
            "Shield",
            "Sword",
            "Vest",
            "Vitamins").toList();

    private final List<String> names = Stream.of("Iuliu",
            "Raul",
            "Ionel",
            "Felix",
            "Radu",
            "Aurel",
            "Paula",
            "Ileana",
            "Viorica",
            "Roxana",
            "Marilena").toList();

    /**
     * Realizeaza scrierea propriu-zisa in fisier xml.
     * @param doc
     * @param output
     * @throws TransformerException
     */
    private void writeXml(Document doc, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }

    /**
     * Construieste lista de antrenori.
     * @return Lista de antrenori.
     */
    private @NotNull List<Coach> getCoaches() {
        Random rand = new Random();
        List<Coach> coachList = new ArrayList<>();
        PokemonFactory pokemonFactory = new PokemonFactory();
        ItemFactory itemFactory = new ItemFactory();
        List<String> tempCoaches = new ArrayList<>();

        int NUMBER_OF_COACHES = 2;
        // adauga antrenorii in lista
        for (int i = 0; i < NUMBER_OF_COACHES; ) {
            String coachName = names.get(rand.nextInt(names.size()));
            if (tempCoaches.contains(coachName)) continue;
            tempCoaches.add(coachName);
            List<String> tempPokemons = new ArrayList<>();
            List<Pokemon> pokemonList = new ArrayList<>();
            int NUMBER_OF_POKEMONS = 3;
            // adauga pokemonii in lista de Pokemoni a antrenorului
            for (int j = 0; j < NUMBER_OF_POKEMONS; ) {
                String pokemonName = pokemons.get(rand.nextInt(pokemons.size()));
                if (tempPokemons.contains(pokemonName)) continue;
                tempPokemons.add(pokemonName);
                Pokemon pokemon = pokemonFactory.getPokemon(pokemonName);
                int numItems = rand.nextInt(4);
                // echipeaza Pokemonul cu iteme
                while (pokemon.getItems().size() < numItems) {
                    Item item = itemFactory.getItem(items.get(rand.nextInt(items.size())));
                    item.equip(pokemon);
                }
                pokemonList.add(pokemon);
                j++;
            }
            // adauga antrenorul-rezultat in lista
            coachList.add(new Coach(coachName, rand.nextInt(12, 65), pokemonList));
            i++;
        }
        return coachList;
    }

    /**
     * Populeaza fisierul xml cu elementele necesare si trimite informatiile la scriere.
     * @return Lista de antrenori
     * @throws ParserConfigurationException
     */
    public List<Coach> generate() throws ParserConfigurationException {
        List<Coach> coaches = getCoaches();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("coaches");
        doc.appendChild(rootElement);

        for (Coach coach : coaches) {
            // creeaza elementul coach
            Element coachesElement = doc.createElement("coach");
            rootElement.appendChild(coachesElement);
            Element coachNameElement = doc.createElement("name"); // seteaza numele
            coachesElement.appendChild(coachNameElement);
            coachNameElement.setTextContent(coach.getName());
            Element coachAgeElement = doc.createElement("age"); // seteaza varsta
            coachesElement.appendChild(coachAgeElement);
            coachAgeElement.setTextContent(Integer.toString(coach.getAge()));
            Element coachPokemonsElement = doc.createElement("pokemons"); // creeaza elementul pokemons
            coachesElement.appendChild(coachPokemonsElement);
            // seteaza pokemonii
            for (Pokemon pokemon : coach.getPokemons()) {
                Element pokemonElement = doc.createElement("pokemon");
                coachPokemonsElement.appendChild(pokemonElement);
                Element pokemonNameElement = doc.createElement("name");
                pokemonElement.appendChild(pokemonNameElement);
                pokemonNameElement.setTextContent(pokemon.getName());
                Element itemsElement = doc.createElement("items");
                pokemonElement.appendChild(itemsElement);
                // seteaza itemele pentru pokemon
                for (Item item : pokemon.getItems()) {
                    Element itemElement = doc.createElement("item");
                    itemsElement.appendChild(itemElement);
                    itemElement.setTextContent(item.getName());
                }
            }
        }

        try (FileOutputStream output =
                new FileOutputStream("./src/InputOutput/in/test.xml")) {
            writeXml(doc, output);
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }
        return coaches;
    }
}
