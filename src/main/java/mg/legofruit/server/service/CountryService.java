package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.entity.Country;
import mg.legofruit.server.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public void add(Country country){
        countryRepository.save(country);
    }
}
