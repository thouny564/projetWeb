package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.*;
import com.spring.henallux.firstSpringProject.model.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("/order")
@SessionAttributes({Constants.ORDER})
public class CustomerOrderController {

    private final CustomerOrderDataAccess customerOrderDataAccess;
    private final ProductDataAccess productDataAccess;
    private final OrderLineDataAccess orderLineDataAccess;
    private final PromotionDataAccess promotionDataAccess;

    public CustomerOrderController(CustomerOrderDataAccess customerOrderDataAccess,
                                   ProductDataAccess productDataAccess,
                                   OrderLineDataAccess orderLineDataAccess,
                                   PromotionDataAccess promotionDataAccess) {
        this.customerOrderDataAccess = customerOrderDataAccess;
        this.productDataAccess = productDataAccess;
        this.orderLineDataAccess = orderLineDataAccess;
        this.promotionDataAccess = promotionDataAccess;
    }

    @PostMapping
    public String createCustomerOrder(@AuthenticationPrincipal User currentUser,
                                      HttpSession session, RedirectAttributes redirectAttributes) {

        Cart cart = (Cart) session.getAttribute(Constants.CART);
        if (cart == null || cart.getItems().isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "error.cart.empty"
            );
            return "redirect:/cart";
        }


        CustomerOrder customerOrder = new CustomerOrder();
        double totalPrice = 0.0;


        Map<Integer, Integer> items = cart.getItems();
        for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
            Product p = productDataAccess.get(entry.getKey());
            if (p != null) {

                List<Promotion> promotions = promotionDataAccess.getPromotionsByProductId(p.getId());
                double discountedPrice = p.getPrice();
                int maxDiscount = 0;
                for (Promotion promo : promotions) {
                    if (promo.getDiscountPercentage() > maxDiscount) {
                        maxDiscount = promo.getDiscountPercentage();
                    }
                }
                if (maxDiscount > 0) {
                    discountedPrice = p.getPrice() * (1 - maxDiscount / 100.0);
                }
                totalPrice += discountedPrice * entry.getValue();
            }
        }

        customerOrder.setUser(currentUser);
        customerOrder.setOrderDate(LocalDateTime.now());
        customerOrder.setStatus("CONFIRMED");
        customerOrder.setTotalPrice(totalPrice);
        customerOrder.setPaid(false);

        Integer orderId = customerOrderDataAccess.add(customerOrder);
        CustomerOrder newCustomerOrder = customerOrderDataAccess.get(orderId);


        for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
            Product p = productDataAccess.get(entry.getKey());
            if (p != null) {
                List<Promotion> promotions = promotionDataAccess.getPromotionsByProductId(p.getId());
                double discountedPrice = p.getPrice();
                int maxDiscount = 0;
                for (Promotion promo : promotions) {
                    if (promo.getDiscountPercentage() > maxDiscount) {
                        maxDiscount = promo.getDiscountPercentage();
                    }
                }
                if (maxDiscount > 0) {
                    discountedPrice = p.getPrice() * (1 - maxDiscount / 100.0);
                }

                OrderLine orderLine = new OrderLine(entry.getValue(), discountedPrice, newCustomerOrder, p);
                orderLineDataAccess.add(orderLine);
            }
        }

        cart.getItems().clear();
        session.setAttribute(Constants.CART, cart);

        return "redirect:/pay/" + newCustomerOrder.getId();
    }

}
