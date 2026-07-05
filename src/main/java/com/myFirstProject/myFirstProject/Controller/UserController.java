package com.myFirstProject.myFirstProject.Controller;

import com.myFirstProject.myFirstProject.DTO.CartRequest;
import com.myFirstProject.myFirstProject.DTO.CartResponseDTO;
import com.myFirstProject.myFirstProject.DTO.MeResponse;
import com.myFirstProject.myFirstProject.DTO.OrderRequest;
import com.myFirstProject.myFirstProject.Service.CartService;
import com.myFirstProject.myFirstProject.Service.OrderService;
import com.myFirstProject.myFirstProject.Service.UserService;
import com.myFirstProject.myFirstProject.entity.Cart;
import com.myFirstProject.myFirstProject.entity.Order;
import com.myFirstProject.myFirstProject.entity.Users;
import com.myFirstProject.myFirstProject.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private CartService cartService;

    @Autowired
 private OrderService orderService;

    @PostMapping("/cart")
    public ResponseEntity<ApiResponse<CartResponseDTO>> addCartItem(
            @RequestBody CartRequest cartRequest,
            @AuthenticationPrincipal Users user) {

        CartResponseDTO cart = cartService.addItemToCart(
                user.getId(),
                cartRequest.getProductId(),
                cartRequest.getQuantity()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Item added to cart successfully",
                        cart
                ));
    }
    @PutMapping("/increase/{cartItemId}")
    public ResponseEntity<ApiResponse<CartResponseDTO>> increase(
            @PathVariable Long cartItemId,
            @AuthenticationPrincipal Users user) {

        CartResponseDTO cart =
                cartService.increaseQuantity(user.getId(), cartItemId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quantity increased successfully",
                        cart
                )
        );
    }
    @PutMapping("/decrease/{cartItemId}")
    public ResponseEntity<ApiResponse<CartResponseDTO>> decrease(
            @PathVariable Long cartItemId,
            @AuthenticationPrincipal Users user) {

        CartResponseDTO cart =
                cartService.decreaseQuantity(user.getId(), cartItemId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Quantity decreased successfully",
                        cart
                )
        );
    }
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<ApiResponse<CartResponseDTO>> removeItem(
            @PathVariable Long cartItemId,
            @AuthenticationPrincipal Users user) {

        CartResponseDTO cart =
                cartService.removeItem(user.getId(), cartItemId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Item removed successfully",
                        cart
                )
        );
    }


    @GetMapping("/me")
public MeResponse getUser(@AuthenticationPrincipal Users user){
    MeResponse mr = new MeResponse();
           mr.setEmail(user.getEmail());
           mr.setName(user.getName());
           mr.setId(user.getId());
        return mr;
}


    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<CartResponseDTO>> getCart(
            @PathVariable Long userId) {

        CartResponseDTO cart = cartService.getOrCreateCart(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Cart fetched successfully",
                        cart
                )
        );
    }
    @PostMapping("/place")
    public Order placeOrder(
            @AuthenticationPrincipal Users user,
            @RequestBody OrderRequest request
    ) {

        return orderService.placeOrder(
                user.getId(),
                request.getAddress(),
                request.getPaymentMethod()
        );
    }
    @GetMapping("/myorder")
    public List<Order> getMyOrders(
            @AuthenticationPrincipal Users user
    ) {
        return orderService.getOrdersByUser(user.getId());
    }


}
