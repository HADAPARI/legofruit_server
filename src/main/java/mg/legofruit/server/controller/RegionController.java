package mg.legofruit.server.controller;

import lombok.AllArgsConstructor;
import mg.legofruit.server.entity.Region;
import mg.legofruit.server.service.RegionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/region")
@AllArgsConstructor
public class RegionController {
    private RegionService regionService;

    @GetMapping("/{countryId}")
    public List<Region> getRegions(@PathVariable Integer countryId) {
        return regionService.getRegions(countryId);
    }
}
