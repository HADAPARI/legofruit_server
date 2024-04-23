package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.entity.Region;
import mg.legofruit.server.repository.RegionRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

    public void add(Region region){
        regionRepository.save(region);
    }

}
