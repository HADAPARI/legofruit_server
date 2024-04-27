package mg.legofruit.server.controller;

import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.ProduitDTO;
import mg.legofruit.server.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produit")
@AllArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @PostMapping("/ajout/{userId}")
    public ResponseEntity<ProduitDTO> addNewProduct(@PathVariable String userId, @RequestBody ProduitDTO produitDTO) {
        ProduitDTO newProduit = produitService.addNewProduct(produitDTO, userId);
        return new ResponseEntity<>(newProduit, HttpStatus.CREATED);
    }
}
