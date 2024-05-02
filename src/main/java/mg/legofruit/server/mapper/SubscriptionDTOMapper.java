package mg.legofruit.server.mapper;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.CategoryDTO;
import mg.legofruit.server.dto.UserDTO;
import mg.legofruit.server.entity.Category;
import mg.legofruit.server.entity.Subscription;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
@AllArgsConstructor
public class SubscriptionDTOMapper implements Function<CategoryDTO, Subscription> {
    private CategoryRepository categoryRepository;

    @Override
    public Subscription apply(CategoryDTO categoryDTO) {
        Subscription subscription = new Subscription();
        Category category = categoryRepository.findById(categoryDTO.getId()).orElseThrow(() -> new ValidationException("Invalid Data: category not found"));
        subscription.setCategory(category);
        return subscription;
    }
}
