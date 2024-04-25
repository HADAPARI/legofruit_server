package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.entity.Country;
import mg.legofruit.server.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public void add(Country country){
        countryRepository.save(country);
    }

    public Country find(Integer id){
        return countryRepository.findById(id).orElse(null);
    }

    public Country findByName(String name){
        return countryRepository.findByName(name);
    }

    public List<Country> getAll(){
        return countryRepository.findAll();
    }
}
