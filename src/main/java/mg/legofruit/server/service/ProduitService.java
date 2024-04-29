package mg.legofruit.server.service;

import lombok.AllArgsConstructor;
import mg.legofruit.server.ProduitSpecification.ProduitSpecification;
import mg.legofruit.server.dto.AchatDTO;
import mg.legofruit.server.dto.ProduitDTO;
import mg.legofruit.server.entity.Produit;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.mapper.ProduitDTOMapper;
import mg.legofruit.server.repository.ProduitRepository;
import mg.legofruit.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProduitService {
    private final ProduitRepository produitRepository;
    private final ProduitDTOMapper produitDTOMapper;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProduitService.class);

    public ProduitDTO addNewProduct(ProduitDTO produitDTO, String userId) {
        logger.info("Adding new product with userID: {}", userId);
        Optional<Users> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            logger.error("Utilisateur introuvable pour l'ID: {}", userId);
            throw new RuntimeException("Utilisateur introuvable");
        }

        Users user = userOptional.get();

        Produit produit = new Produit();
        produit.setUserid(user);
        produit.setCategory(produitDTO.getCategory());
        produit.setName(produitDTO.getName());
        produit.setPrix(produitDTO.getPrix());
        produit.setQuantity(produitDTO.getQuantity());
        produit.setDaterecolte(produitDTO.getDaterecolte());
        produit.setDatepublication(produitDTO.getDatepublication());
        produit.setImage(produitDTO.getImage());

        Produit savedProduit = produitRepository.save(produit);

        return produitDTOMapper.toDTO(savedProduit);
    }
    public List<Produit> findAllProduct(String name, String category) {
        final Specification<Produit> specification =
                ProduitSpecification.filterProduct(category, name);
        final List<Produit> product = produitRepository.findAll(specification);
        return product;
    }

    public List<Produit> getAllProduit(){
        return produitRepository.findAll();
    }

 }
