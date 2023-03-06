package InputOutput;

import Items.Item;
import Items.ItemFactory;
import Other.Coach;
import Pokemons.Pokemon;
import Pokemons.PokemonFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Read {
    /**
     * Citeste datele din fisierul xml.
     * @param fileName Numele fisierului.
     * @return Lista de antrenori cititi din xml.
     */
    public List<Coach> read(String fileName) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        PokemonFactory pokemonFactory = new PokemonFactory();
        ItemFactory itemFactory = new ItemFactory();
        List<Coach> coachList = new ArrayList<>();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("./src/InputOutput/in/" + fileName));
            doc.getDocumentElement().normalize();

            NodeList list1 = doc.getElementsByTagName("coach");

            for (int i = 0; i < list1.getLength(); i++) {
                Node node = list1.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE) continue;
                Element element = (Element) node;
                // citeste numele antrenorului
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                // citeste varsta antrenorului
                int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                NodeList list2 = element.getElementsByTagName("pokemon");
                List<Pokemon> pokemonList = new ArrayList<>();
                // citeste pokemonii
                for (int j = 0; j < list2.getLength(); j++) {
                    node = list2.item(j);
                    if (node.getNodeType() != Node.ELEMENT_NODE) continue;
                    element = (Element) node;
                    Pokemon pokemon = pokemonFactory.getPokemon(element.getElementsByTagName("name").item(0)
                            .getTextContent());
                    NodeList list3 = ((Element)element.getElementsByTagName("items").item(0))
                            .getElementsByTagName("item");
                    // citeste itemele
                    for (int k = 0; k < list3.getLength(); k++) {
                        node = list3.item(k);
                        if (node.getNodeType() != Node.ELEMENT_NODE) continue;
                        element = (Element) node;
                        Item item = itemFactory.getItem(element.getTextContent());
                        // echipeaza pokemonul
                        item.equip(pokemon);
                    }
                    pokemonList.add(pokemon);
                }
                coachList.add(new Coach(name, age, pokemonList));
            }

        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return coachList;
    }
}
