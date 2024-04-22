package mg.legofruit.server.mapper;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.UserDTO;
import mg.legofruit.server.dto.UserSignUpDTO;
import mg.legofruit.server.entity.Country;
import mg.legofruit.server.entity.Region;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.repository.CountryRepository;
import mg.legofruit.server.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class UserSignUpDTOMapper implements Function<UserSignUpDTO, Users> {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;

    @Override
    public Users apply(UserSignUpDTO userSignUpDTO) {
        Users user = new Users();
        user.setFirstName(userSignUpDTO.getFirstName());
        user.setLastName(userSignUpDTO.getLastName());
        user.setEmail(userSignUpDTO.getEmail());
        user.setAvatar(userSignUpDTO.getAvatar());
        user.setPhone(userSignUpDTO.getPhone());
        user.setAddress(userSignUpDTO.getAddress());

        Region region = regionRepository.findById(userSignUpDTO.getRegion()).orElseThrow(() -> new ValidationException("Invalid Data"));
        user.setRegion(region);

        Country country = countryRepository.findById(userSignUpDTO.getRegion()).orElseThrow(() -> new ValidationException("Invalid Data"));
        user.setCountry(country);

        user.setPassword(userSignUpDTO.getPassword());
        return user;
    }
}
