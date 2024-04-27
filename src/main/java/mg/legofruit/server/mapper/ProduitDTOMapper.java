package mg.legofruit.server.mapper;

import mg.legofruit.server.dto.ProduitDTO;
import mg.legofruit.server.entity.Produit;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@Service
public class ProduitDTOMapper implements Function<Produit, ProduitDTO> {

    @Override
    public ProduitDTO apply(Produit produit) {
        return new ProduitDTO(
                produit.getId(),
                produit.getUserid(),
                produit.getCategory(),
                produit.getName(),
                produit.getPrix(),
                produit.getQuantity(),
                produit.getDatepublication(),
                produit.getImage(),
                produit.getDaterecolte()
        );
    }

    public ProduitDTO toDTO(Produit produit) {
        ProduitDTO produitDTO = new ProduitDTO();
        produitDTO.setId(produit.getId());
        produitDTO.setCategory(produit.getCategory());
        produitDTO.setName(produit.getName());
        produitDTO.setPrix(produit.getPrix());
        produitDTO.setQuantity(produit.getQuantity());
        produitDTO.setDatepublication(produit.getDatepublication());
        produitDTO.setDaterecolte(produit.getDaterecolte());
        produitDTO.setImage(produit.getImage());
        produitDTO.setUserid(produit.getUserid());
        return produitDTO;
    }
}