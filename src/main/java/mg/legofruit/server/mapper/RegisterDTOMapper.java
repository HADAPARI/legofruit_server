package mg.legofruit.server.mapper;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.RegisterDTO;
import mg.legofruit.server.entity.Country;
import mg.legofruit.server.entity.Region;
import mg.legofruit.server.entity.Role;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.repository.CountryRepository;
import mg.legofruit.server.repository.RegionRepository;
import mg.legofruit.server.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class RegisterDTOMapper implements Function<RegisterDTO, Users> {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final RoleRepository roleRepository;

    @Override
    public Users apply(RegisterDTO registerDTO) {
        Users user = new Users();
        user.setFirstname(registerDTO.getFirstname());
        user.setLastname(registerDTO.getLastname());
        user.setEmail(registerDTO.getEmail());
        user.setAvatar(registerDTO.getAvatar());
        user.setPhone(registerDTO.getPhone());
        user.setAddress(registerDTO.getAddress());

        Region region = regionRepository.findById(registerDTO.getRegion()).orElseThrow(() -> new ValidationException("Invalid Data: region"));
        user.setRegion(region);

        Country country = countryRepository.findById(registerDTO.getRegion()).orElseThrow(() -> new ValidationException("Invalid Data: country"));
        user.setCountry(country);

        Role role = roleRepository.findById(registerDTO.getRole()).orElseThrow(() -> new ValidationException("Invalid Data: role"));
        user.setRole(role);

        user.setPassword(registerDTO.getPassword());
        return user;
    }
}
