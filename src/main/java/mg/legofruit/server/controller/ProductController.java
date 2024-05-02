package mg.legofruit.server.controller;

import lombok.AllArgsConstructor;
import mg.legofruit.server.dto.ProductDTO;
import mg.legofruit.server.entity.Product;
import mg.legofruit.server.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping("/ajout/{userId}")
    public ResponseEntity<ProductDTO> addNewProduct(@PathVariable String userId, @RequestBody ProductDTO productDTO) {
        ProductDTO newProduit = productService.addNewProduct(productDTO, userId);
        return new ResponseEntity<>(newProduit, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> searchProduct(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category
    ) {
        return ResponseEntity.ok(productService.findAllProduct(category, name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProduit(){
        List<Product> products = productService.getAllProduit();
        return ResponseEntity.ok().body(products);
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
