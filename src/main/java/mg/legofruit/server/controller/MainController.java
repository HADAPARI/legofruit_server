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
        Collections.sort(regionsAlbanie);

        for (String r : regionsAlbanie) {
            Region region = new Region();
            region.setName(r);
            region.setCountry(countryService.findByName("Albanie"));
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
