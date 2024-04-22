package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.repository.RegionRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;

}
