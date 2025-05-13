package com.batigobackend.batigo.Service;

import com.batigobackend.batigo.Entity.Cart;
import com.batigobackend.batigo.Model.CartDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICartService {
    List<Cart> addCart(List<Cart> cart, String projectName);

    List<CartDTO> getAllCarts();
}
