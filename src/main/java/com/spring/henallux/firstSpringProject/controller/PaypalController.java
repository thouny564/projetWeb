package com.spring.henallux.firstSpringProject.controller;

import com.spring.henallux.firstSpringProject.dataAccess.dao.CustomerOrderDataAccess;
import com.spring.henallux.firstSpringProject.model.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;

@Controller
public class PaypalController {
    private final CustomerOrderDataAccess customerOrderDataAccess;

    @Autowired
    public PaypalController(CustomerOrderDataAccess customerOrderDataAccess){
        this.customerOrderDataAccess = customerOrderDataAccess;
    }


    @GetMapping("/pay/success")
    public String paySuccess(){
        return "redirect:/cart";
    }

    @GetMapping("/pay/cancel")
    public String payCancel(){
        return "redirect:/welcome";
    }

    @GetMapping("/pay/{orderId}")
    public String payWithPaypal(@PathVariable Integer orderId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        CustomerOrder order = customerOrderDataAccess.get(orderId);
        if (order == null) {
            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "error.cart.empty"
            );
            return "redirect:/cart";

        }

        String contextPath = request.getContextPath();
        String scheme = request.getScheme();            // http ou https
        String serverName = request.getServerName();    // localhost ou domaine
        int serverPort = request.getServerPort();      // 8080 par ex.

        String baseUrl = scheme + "://" + serverName + ":" + serverPort + contextPath;

        String returnUrl = baseUrl + "/pay/success/";
        String cancelUrl = baseUrl + "/pay/cancel/";

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
