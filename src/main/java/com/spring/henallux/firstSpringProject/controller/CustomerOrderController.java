package com.spring.henallux.firstSpringProject.controller;


import com.spring.henallux.firstSpringProject.dataAccess.dao.CustomerOrderDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.dao.OrderLineDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.model.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("/order")
@SessionAttributes({Constants.ORDER})
public class CustomerOrderController {
    private final CustomerOrderDataAccess customerOrderDataAccess;
    private final ProductDataAccess productDataAccess;
    private final OrderLineDataAccess orderLineDataAccess;


    public CustomerOrderController(CustomerOrderDataAccess customerOrderDataAccess, ProductDataAccess productDataAccess, OrderLineDataAccess orderLineDataAccess){
        this.customerOrderDataAccess = customerOrderDataAccess;
        this.productDataAccess = productDataAccess;
        this.orderLineDataAccess = orderLineDataAccess;
    }



    @PostMapping
    public String createCustomerOrder(
            @AuthenticationPrincipal User currentUser,
            HttpSession session) {

        Cart cart = (Cart) session.getAttribute(Constants.CART);
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart?error=panier_vide";
        }


        CustomerOrder customerOrder = new CustomerOrder();
        double totalPrice = 0.0;
        for (Map.Entry<Integer, Integer> entry : cart.getItems().entrySet()) {
            Product p = productDataAccess.get(entry.getKey());
            if (p != null) totalPrice += p.getPrice() * entry.getValue();
        }

        customerOrder.setUser(currentUser);
        customerOrder.setOrderDate(LocalDateTime.now());
        customerOrder.setStatus("CONFIRMED");
        customerOrder.setTotalPrice(totalPrice);
        customerOrder.setPaid(false);

        Integer orderId = customerOrderDataAccess.add(customerOrder);

        for (Map.Entry<Integer, Integer> entry : cart.getItems().entrySet()) {
            Product p = productDataAccess.get(entry.getKey());
            if (p != null) {
                OrderLine orderLine = new OrderLine(entry.getValue(), p.getPrice(), customerOrder, p);
                orderLineDataAccess.add(orderLine);
            }
        }


        cart.getItems().clear();
        session.setAttribute(Constants.CART, cart);


        return "redirect:/pay/" + orderId;
    }



}
