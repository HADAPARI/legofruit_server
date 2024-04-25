package mg.legofruit.server.controller;

import lombok.AllArgsConstructor;
import mg.legofruit.server.entity.Country;
import mg.legofruit.server.entity.Region;
import mg.legofruit.server.entity.Role;
import mg.legofruit.server.enums.RoleType;
import mg.legofruit.server.service.CountryService;
import mg.legofruit.server.service.RegionService;
import mg.legofruit.server.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class MainController {
    private final CountryService countryService;
    private final RegionService regionService;
    private final RoleService roleService;

    @GetMapping("/initialize")
    public void initialize() {
        List<String> pays = Arrays.asList(
                "Albanie", "Allemagne", "Andorre", "Autriche", "Belgique", "Bosnie-Herzégovine",
                "Bulgarie", "Chypre", "Croatie", "Danemark", "Espagne", "Estonie", "Finlande",
                "France", "Grèce", "Hongrie", "Irlande", "Islande", "Italie", "Lettonie",
                "Liechtenstein", "Lituanie", "Luxembourg", "Macédoine du Nord", "Malte", "Moldavie",
                "Monaco", "Monténégro", "Norvège", "Pays-Bas", "Pologne", "Portugal", "République tchèque",
                "Roumanie", "Royaume-Uni", "Russie", "Saint-Marin", "Serbie", "Slovaquie", "Slovénie",
                "Suède", "Suisse", "Ukraine", "Vatican", "Madagascar"
        );

        Collections.sort(pays);

        for (String p : pays) {
            Country country = new Country();
            country.setName(p);
            countryService.add(country);
        }

        List<String> regionsAlbanie = Arrays.asList(
                "Berat", "Dibër", "Durrës", "Elbasan", "Fier", "Gjirokastër",
                "Korçë", "Kukës", "Lezhë", "Shkodër", "Tiranë", "Vlorë"
        );
        List<String> regionsAllemagne = Arrays.asList(
                "Baden-Württemberg", "Bavière", "Berlin", "Brandebourg", "Brême", "Hambourg",
                "Hesse", "Mecklembourg-Poméranie-Occidentale", "Basse-Saxe", "Rhénanie-du-Nord-Westphalie",
                "Rhénanie-Palatinat", "Sarre", "Saxe", "Saxe-Anhalt", "Schleswig-Holstein", "Thuringe"
        );
        List<String> regionsAndorre = Arrays.asList(
                "Andorre-la-Vieille", "Canillo", "Encamp", "Escaldes-Engordany", "La Massana", "Ordino", "Sant Julià de Lòria"
        );
        List<String> regionsAutriche = Arrays.asList(
                "Basse-Autriche", "Burgenland", "Carinthie", "Haute-Autriche", "Salzbourg", "Styrie",
                "Tyrol", "Vienne", "Vorarlberg"
        );
        List<String> regionsBelgique = Arrays.asList(
                "Anvers", "Brabant flamand", "Brabant wallon", "Bruxelles-Capitale", "Flandre-Occidentale",
                "Flandre-Orientale", "Hainaut", "Liège", "Limbourg", "Luxembourg", "Namur"
        );
        List<String> regionsBosnieHerzegovine = Arrays.asList(
                "Fédération de Bosnie-et-Herzégovine", "Republika Srpska"
        );
        List<String> regionsBulgarie = Arrays.asList(
                "Blagoevgrad", "Burgas", "Dobritch", "Gabrovo", "Haskovo", "Kardjali", "Kjustendil",
                "Lovech", "Montana", "Pazardjik", "Pernik", "Pleven", "Plovdiv", "Razgrad", "Ruse",
                "Silistra", "Sliven", "Smolyan", "Sofia (oblast)", "Sofia (ville)", "Stara Zagora",
                "Targovichte", "Varna", "Veliko Tarnovo", "Vidin", "Vraca", "Yambol"
        );
        List<String> regionsChypre = Arrays.asList(
                "Famagouste", "Kyrenia", "Larnaca", "Limassol", "Nicosie", "Paphos"
        );
        List<String> regionsCroatie = Arrays.asList(
                "Comitat de Bjelovar-Bilogora", "Comitat de Brod-Posavina", "Comitat de Dubrovnik-Neretva",
                "Comitat de Karlovac", "Comitat de Koprivnica-Križevci", "Comitat de Lika-Senj",
                "Comitat de Međimurje", "Comitat de Osijek-Baranja", "Comitat de Požega-Slavonie",
                "Comitat de Primorje-Gorski Kotar", "Comitat de Šibenik-Knin", "Comitat de Sisak-Moslavina",
                "Comitat de Split-Dalmatie", "Comitat de Varaždin", "Comitat de Virovitica-Podravina",
                "Comitat de Vukovar-Syrmie", "Comitat de Zadar", "Comitat de Zagreb"
        );
        List<String> regionsDanemark = Arrays.asList(
                "Hovedstaden", "Midtjylland", "Nordjylland", "Sjælland", "Syddanmark"
        );
        List<String> regionsEspagne = Arrays.asList(
                "Andalousie", "Aragon", "Asturies", "Îles Baléares", "Canaries", "Cantabrie", "Castille-La Manche",
                "Castille-et-León", "Catalogne", "Communauté valencienne", "Estremadure", "Galice", "La Rioja",
                "Madrid", "Navarre", "Pays basque", "Région de Murcie"
        );
        List<String> regionsEstonie = Arrays.asList(
                "Comté de Harju", "Comté de Hiiu", "Comté de Ida-Viru", "Comté de Järva", "Comté de Jõgeva",
                "Comté de Lääne", "Comté de Lääne-Viru", "Comté de Pärnu", "Comté de Rapla", "Comté de Saare",
                "Comté de Tartu", "Comté de Valga", "Comté de Viljandi", "Comté de Võru"
        );
        List<String> regionsFinlande = Arrays.asList(
                "Åland", "Kainuu", "Kymenlaakso", "Laponie", "Pirkanmaa", "Pohjanmaa", "Pohjois-Karjala", "Pohjois-Pohjanmaa",
                "Pohjois-Savo", "Päijät-Häme", "Satakunta", "Uusimaa", "Varsinais-Suomi"
        );
        List<String> regionsFrance = Arrays.asList(
                "Auvergne-Rhône-Alpes", "Bourgogne-Franche-Comté", "Bretagne", "Centre-Val de Loire", "Corse",
                "Grand Est", "Hauts-de-France", "Île-de-France", "Normandie", "Nouvelle-Aquitaine", "Occitanie",
                "Pays de la Loire", "Provence-Alpes-Côte d'Azur"
        );

        List<String> regionsGrece = Arrays.asList(
                "Attique", "Épire", "Îles de l'Égée", "Macédoine-Centrale", "Macédoine-Occidentale", "Macédoine-Orientale-et-Thrace",
                "Mer Égée du Nord", "Mer Ionienne", "Mer Méditerranée", "Péloponnèse", "Thessalie", "Égée-Méridionale",
                "Égée-Septentrionale"
        );


        Collections.sort(regionsAlbanie);
        Collections.sort(regionsAllemagne);
        Collections.sort(regionsAndorre);
        Collections.sort(regionsAutriche);
        Collections.sort(regionsBelgique);
        Collections.sort(regionsBosnieHerzegovine);
        Collections.sort(regionsBulgarie);
        Collections.sort(regionsChypre);
        Collections.sort(regionsCroatie);
        Collections.sort(regionsDanemark);
        Collections.sort(regionsEspagne);
        Collections.sort(regionsEstonie);
        Collections.sort(regionsFinlande);
        Collections.sort(regionsFrance);
        Collections.sort(regionsGrece);

        for (String r : regionsAlbanie) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Albanie"));
            regionService.add(region);
        }
        for (String r : regionsAllemagne) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Allemagne"));
            regionService.add(region);
        }
        for (String r : regionsAndorre) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Andorre"));
            regionService.add(region);
        }
        for (String r : regionsAutriche) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Autriche"));
            regionService.add(region);
        }
        for (String r : regionsBelgique) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Belgique"));
            regionService.add(region);
        }
        for (String r : regionsBosnieHerzegovine) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Bosnie-Herzégovine"));
            regionService.add(region);
        }
        for (String r : regionsBulgarie) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Bulgarie"));
            regionService.add(region);
        }
        for (String r : regionsChypre) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Chypre"));
            regionService.add(region);
        }
        for (String r : regionsCroatie) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Croatie"));
            regionService.add(region);
        }
        for (String r : regionsDanemark) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Danemark"));
            regionService.add(region);
        }
        for (String r : regionsEspagne) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Espagne"));
            regionService.add(region);
        }
        for (String r : regionsEstonie) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Estonie"));
            regionService.add(region);
        }
        for (String r : regionsFinlande) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Finlande"));
            regionService.add(region);
        }
        for (String r : regionsFrance) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("France"));
            regionService.add(region);
        }
        for (String r : regionsGrece) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Grèce"));
            regionService.add(region);
        }

        RoleType[] roles = RoleType.values();
        Arrays.sort(roles);

        for (RoleType roleType : roles){
            Role role = new Role();
            role.setType(roleType);
            roleService.add(role);
        }
    }
}
