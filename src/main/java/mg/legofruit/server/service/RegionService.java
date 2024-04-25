package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.entity.Country;
import mg.legofruit.server.entity.Region;
import mg.legofruit.server.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private CountryService countryService;

    public void add(Region region){
        regionRepository.save(region);
    }

    public List<Region> getAll(){
        return regionRepository.findAll();
    }

    public List<Region> getRegions(Integer countryId){
        Country country = countryService.find(countryId);
        return regionRepository.findAllByCountry(country);
    }

}
