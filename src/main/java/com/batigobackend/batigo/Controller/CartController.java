package com.batigobackend.batigo.Controller;

import com.batigobackend.batigo.Entity.Cart;
import com.batigobackend.batigo.Model.CartDTO;
import com.batigobackend.batigo.Service.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:53570"})
public class CartController {
    private final ICartService cartService;

    @PostMapping("/addCart")
    public ResponseEntity<List<Cart>> addCart(@RequestBody List<Cart> cart, @RequestParam String projectName) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addCart(cart, projectName));
    }
    @GetMapping("/getAllCarts")
    public List<CartDTO>getAllCarts() {
        return cartService.getAllCarts();
    }
}
