package mg.legofruit.server.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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


    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addNewProduct(HttpServletRequest request, @RequestBody ProductDTO productDTO) {
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("at".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }

        ProductDTO newProduit = productService.addNewProduct(productDTO, token);
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

    @GetMapping("/ismine/{id}")
    public Boolean isMine(@PathVariable Long id, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("at".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }

        return productService.isMine(id, token);
    }
}
