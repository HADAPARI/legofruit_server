package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.entity.Role;
import mg.legofruit.server.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void add(Role role){
        roleRepository.save(role);
    }
}
