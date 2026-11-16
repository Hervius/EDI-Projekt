package de.thi.informatik.edi.example01;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    // Konstruktor-Injection
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    // Alle Produkte abrufen
    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    // Produkt nach ID abrufen
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Neues Produkt erstellen
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product);
    }

    // Produkt aktualisieren
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        return productRepository.findById(id)
                .map(existingProduct -> {
                    product.setId(id);
                    return ResponseEntity.ok(productRepository.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Produkt löschen
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        return productRepository.findById(id)
                .map(product ->  {
                    productRepository.delete(product);
                    return ResponseEntity.notFound().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
