package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CustomerOrderDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.dao.OrderLineDataAccess;
import com.spring.henallux.firstSpringProject.dataAccess.dao.ProductDataAccess;
import com.spring.henallux.firstSpringProject.model.CustomerOrder;
import com.spring.henallux.firstSpringProject.model.OrderLine;
import com.spring.henallux.firstSpringProject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PaypalController {
    private final CustomerOrderDataAccess customerOrderDataAccess;
    private final OrderLineDataAccess orderLineDataAccess;
    private final ProductDataAccess productDataAccess;

    @Autowired
    public PaypalController(CustomerOrderDataAccess customerOrderDataAccess, OrderLineDataAccess orderLineDataAccess, ProductDataAccess productDataAccess){
        this.customerOrderDataAccess = customerOrderDataAccess;
        this.orderLineDataAccess = orderLineDataAccess;
        this.productDataAccess = productDataAccess;
    }


    @GetMapping("/pay/success")
    public String paySuccess(@RequestParam Integer orderId){
        CustomerOrder order = customerOrderDataAccess.get(orderId);
        if(order != null){
            order.setStatus("PAID");
            order.setPaid(true);
            customerOrderDataAccess.update(order);

            List<OrderLine> orderLineList = orderLineDataAccess.getOrderLinesByOrderId(order.getId());
            for (OrderLine orderLine : orderLineList){
                Product product = orderLine.getProduct();
                product.setStock(product.getStock() - orderLine.getQuantity());
                productDataAccess.update(product);
            }

        }
        return "redirect:/cart";
    }



    @GetMapping("/pay/cancel")
    public String payCancel(@RequestParam Integer orderId){
        CustomerOrder order = customerOrderDataAccess.get(orderId);
        if(order != null){
            order.setStatus("CANCELLED");
            order.setPaid(false);
            customerOrderDataAccess.update(order);
        }
        return "redirect:/cart";
    }

    @GetMapping("/pay/{orderId}")
    public String payWithPaypal(@PathVariable Integer orderId, HttpServletRequest request) {
        CustomerOrder order = customerOrderDataAccess.get(orderId);
        if (order == null) return "redirect:/cart";

        String contextPath = request.getContextPath();
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();

        String baseUrl = scheme + "://" + serverName + ":" + serverPort + contextPath;

        String returnUrl = baseUrl + "/pay/success?orderId=" + order.getId();
        String cancelUrl = baseUrl + "/pay/cancel?orderId=" + order.getId();


        String businessEmail = "sb-mc47z948462048@personal.example.com";
        String paypalUrl = "https://www.sandbox.paypal.com/cgi-bin/webscr"
                + "?cmd=_xclick"
                + "&business=" + businessEmail
                + "&amount=" + order.getTotalPrice()
                + "&currency_code=EUR"
                + "&item_name=Commande+" + order.getId()
                + "&return=" + returnUrl
                + "&cancel_return=" + cancelUrl;

        return "redirect:" + paypalUrl;
    }


}
