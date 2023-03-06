# Pokemon

Aplicatie unde antrenorii de pokemoni si pokemonii dueleaza intre ei. Proiectul ca
scop cunoasterea si aplicarea principiilor OOP si design patterns in cod.

## Descriere
### Antrenori
Despre antrenori se cunosc urmatoarele:
* Nume
* Varsta
* Pokemoni pe care ii detin

### Pokemoni
Pokemonii au urmatoarele atribute:
* Nume
* HP (hit points)
* Atac
  * Putere
  * Tip
    * Normal
    * Special
* Defense
  * Valoare
  * Tip
    * Normal
    * Special
* Abilitati, pot contine:
  * Damage (ignora orice tip de defense)
  * Stun (blocheaza urmatorul atac al oponentului)
  * Dodge (ignora atacul oponentului, cand este aplicata abilitatea)
  * Cooldown (numarul de unitati de timp peste care abilitatea se poate folosi din nou)
* Iteme
  * Scut
  * Vesta
  * Sabiuta
  * Bagheta Magica
  * Vitamine
  * Brad de Craciun
  * Pelerina

Pokemonii sunt de mai multe tipuri:

| Name       | HP  | Normal Attack | Special Attack | Def | Special Def | Ability 1                         | Ability 2                         |
|------------|-----|---------------|----------------|-----|-------------|-----------------------------------|-----------------------------------|
| Neutrel1   | 10  | 3             | N/A            | 1   | 1           | N/A                               | N/A                               |
| Neutrel2   | 20  | 4             | N/A            | 1   | 1           | N/A                               | N/A                               |
| Pikachu    | 35  | N/A           | 4              | 2   | 3           | Dmg: 6 Stun: No  Dodge: No  Cd: 4 | Dmg: 4 Stun: Yes Dodge: Yes Cd: 5 |
| Bulbasaur  | 42  | N/A           | 5              | 3   | 1           | Dmg: 6 Stun: No Dodge: No Cd: 4   | Dmg: 5 Stun: No Dodge: No Cd: 3   |
| Charmander | 50  | 4             | N/A            | 3   | 2           | Dmg: 4 Stun: Yes Dodge: No Cd: 4  | Dmg: 7 Stun: No Dodge: No Cd: 6   |
| Squirtle   | 60  | N/A           | 3              | 5   | 5           | Dmg: 4 Stun: No Dodge: No Cd: 3   | Dmg: 2 Stun: Yes Dodge: No Cd: 2  |
| Snorlax    | 62  | 3             | N/A            | 6   | 4           | Dmg: 4 Stun: Yes Dodge: No Cd: 5  | Dmg: 0 Stun: No Dodge: Yes Cd: 5  |
| Vulpix     | 36  | 5             | N/A            | 2   | 4           | Dmg: 8 Stun: Yes Dodge: No Cd: 6  | Dmg: 2 Stun: No Dodge: Yes Cd: 7  |
| Eevee      | 39  | N/A           | 4              | 3   | 3           | Dmg: 5 Stun: No Dodge: No Cd: 3   | Dmg: 3 Stun: Yes Dodge: No Cd: 3  |
| Jigglypuff | 34  | 4             | N/A            | 2   | 3           | Dmg: 4 Stun: Yes Dodge: No Cd: 4  | Dmg: 3 Stun: Yes Dodge: No Cd: 4  |
| Meowth     | 41  | 3             | N/A            | 4   | 2           | Dmg: 5 Stun: No Dodge: Yes Cd: 4  | Dmg: 1 Stun: No Dodge: Yes Cd: 3  |
| Psyduck    | 43  | 3             | N/A            | 3   | 3           | Dmg: 2 Stun: No Dodge: No Cd: 4   | Dmg: 2 Stun: Yes Dodge: No Cd: 5  |

### Iteme
Pokemonii (nu si Neutreii) pot echipa iteme cu urmatoarele caracteristici:

| Name            | HP  | Attack | Special Attack | Defense | Special Defense |
|-----------------|-----|--------|----------------|---------|-----------------|
| Scut            | -   | -      | -              | +2      | +2              |
| Vesta           | +10 | -      | -              | -       | -               |
| Sabiuta         | -   | +3     | -              | -       | -               |
| Bagheta Magica  | -   | -      | +3             | -       | -               |
| Vitamine        | +2  | +2     | +2             | -       | -               |
| Brad de Craciun | -   | +3     | -              | +1      | -               |
| Pelerina        | -   | -      | -              | -       | +3              |

### Aventura

Aventura se desfasoara intr-o arena. In arena vor intra de fiecare data doi antrenori. Fiecare
antrenor poate sa aleaga (de fapt in mod aleator, sau in fisierele de test .xml) cu ce pokemon sa intre in arena si ce obiecte
sa-i ofere.

Arena poate alege, in mod aleator, unul din urmatoarele evenimente:
1. Fiecare antrenor alege un pokemon care va lupta cu Neutrel (tip 1 sau 2).
2. Antrenorii se vor duela intre ei.

Cand un pokemon castiga o lupta, acesta primeste +1 la toate caracteristicile (nu si la caracteristicile
abilitatilor).

#### Duelul intre antrenori
Lupta dintre antrenori este putin mai speciala. Toti cei trei pokemoni lupta intre ei (primul cu primul, al doilea
cu al doilea, etc. conform pozitiilor lor in listele antrenorilor). Aceste trei lupte sunt realizate concomitent, pe
trei fire de executie.

Pentru lupta finala, de la ambii antrenori se aleg pokemonii cu cel mai bun scor (suma tuturor caracteristicilor).
Pokemonii lupta intre ei, si rezultatul acestei batalii este rezultatul aventurii. Castiga antrenorul pokemonul caruia
castiga in lupta finala.

## Rulare
Proiectul a fost creat si rulat in IntelliJ si are nevoie de ``jetbrains.annotations`` care poate fi gasit in
repozitoriile Maven.
De asemenea, proiectul poate fi rulat si prin fisierul ``Pokemon.jar``, insa va functiona doar generarea de un test random.

### Testare
La rularea proiectului se propun 3 variante de rulare:
1. Rularea a unui din testele existente.
2. Rularea tuturor testelor din ``src/InputOutput/in``. In acest caz rezultatele pentru fiecare test vor fi scrise in
``src/InputOutput/out``.
3. Generarea a unui test random.

Mersul si rezultatul aventurii for fi scrise in ``stdout`` sau in fisierele aferente de output.

Februarie 2022