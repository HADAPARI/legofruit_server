package mg.legofruit.server.controller;

import lombok.AllArgsConstructor;
import mg.legofruit.server.entity.Country;
import mg.legofruit.server.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
@AllArgsConstructor
public class CountryController {
    private CountryService countryService;

    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }


}
