package com.myFirstProject.myFirstProject.Service;

import com.myFirstProject.myFirstProject.DTO.CartItemResponseDTO;
import com.myFirstProject.myFirstProject.DTO.CartResponseDTO;
import com.myFirstProject.myFirstProject.Repository.CartRepository;
import com.myFirstProject.myFirstProject.Repository.ProductRepository;
import com.myFirstProject.myFirstProject.Repository.UserRepository;
import com.myFirstProject.myFirstProject.entity.Cart;
import com.myFirstProject.myFirstProject.entity.CartItem;
import com.myFirstProject.myFirstProject.entity.Products;
import com.myFirstProject.myFirstProject.entity.Users;
import com.myFirstProject.myFirstProject.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartResponseDTO addItemToCart(Long userId, Long productId, int quantity) {

        Cart cart = getCartEntity(userId);

        Products product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        CartItem existingItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {

            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(quantity);

            cart.getItems().add(item);
        }

        recalculateTotal(cart);

        return mapToResponse(cartRepository.save(cart));
    }

    public CartResponseDTO getOrCreateCart(Long userId) {

        return mapToResponse(getCartEntity(userId));
    }

    public CartResponseDTO increaseQuantity(Long userId, Long cartItemId) {

        Cart cart = getCartEntity(userId);

        CartItem item = cart.getItems()
                .stream()
                .filter(i -> i.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart item not found"));

        item.setQuantity(item.getQuantity() + 1);

        recalculateTotal(cart);

        return mapToResponse(cartRepository.save(cart));
    }

    public CartResponseDTO decreaseQuantity(Long userId, Long cartItemId) {

        Cart cart = getCartEntity(userId);

        cart.getItems().removeIf(item -> {

            if (item.getId().equals(cartItemId)) {

                item.setQuantity(item.getQuantity() - 1);

                return item.getQuantity() <= 0;
            }

            return false;
        });

        recalculateTotal(cart);

        return mapToResponse(cartRepository.save(cart));
    }

    public CartResponseDTO removeItem(Long userId, Long cartItemId) {

        Cart cart = getCartEntity(userId);

        cart.getItems().removeIf(item ->
                item.getId().equals(cartItemId));

        recalculateTotal(cart);

        return mapToResponse(cartRepository.save(cart));
    }

    private Cart getCartEntity(Long userId) {

        Cart cart = cartRepository.findByUser_Id(userId);

        if (cart != null) {
            return cart;
        }

        Users user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setTotalPrice(0);

        return cartRepository.save(newCart);
    }

    private void recalculateTotal(Cart cart) {

        double total = cart.getItems()
                .stream()
                .mapToDouble(item ->
                        item.getProduct().getPrice() * item.getQuantity())
                .sum();

        cart.setTotalPrice(total);
    }

    private CartResponseDTO mapToResponse(Cart cart) {

        List<CartItemResponseDTO> items = cart.getItems()
                .stream()
                .map(item -> new CartItemResponseDTO(
                        item.getId(),
                        item.getProduct().getProductId(),
                        item.getProduct().getTitle(),
                        item.getProduct().getThumbnailImage(),
                        item.getProduct().getPrice(),
                        item.getQuantity(),
                        item.getProduct().getPrice() * item.getQuantity()
                ))
                .toList();

        return new CartResponseDTO(
                cart.getId(),
                cart.getUser().getId(),
                items,
                cart.getTotalPrice()
        );
    }
}