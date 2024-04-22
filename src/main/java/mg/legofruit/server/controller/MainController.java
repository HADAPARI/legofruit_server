package mg.legofruit.server.controller;

import lombok.AllArgsConstructor;
import mg.legofruit.server.entity.Country;
import mg.legofruit.server.service.CountryService;
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
    }
}
