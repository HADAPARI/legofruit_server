package mg.legofruit.server.controller;

import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.ProduitDTO;
import mg.legofruit.server.entity.Produit;
import mg.legofruit.server.entity.Users;
import mg.legofruit.server.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Produit>> searchProduct(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category
    ) {
        return ResponseEntity.ok(produitService.findAllProduct(category, name));
    }

    public String getAllProduit(Model model){
        List<Produit> produit = produitService.getAllProduit();
        model.addAttribute("produit", produit);
        return "produit";
    }

    @PostMapping("/achatProduit")
    public  String addAchat(@RequestParam("produitId") Long produitId,
                            @RequestParam("userId") Long userId,
                            RedirectAttributes redirectAttributes){
        try{
           redirectAttributes.addFlashAttribute("Message de succèes",
                   "Le produit a été ajouté au panier avec succès");

        } catch (Exception e){
            redirectAttributes.addFlashAttribute("message d’erreur",
                    "Impossible d’ajouter le produit au panier :"+ e.getMessage());

        }
        return "redirect:/Basket";
    }
}
