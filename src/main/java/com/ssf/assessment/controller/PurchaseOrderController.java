package com.ssf.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssf.assessment.model.Checkout;
import com.ssf.assessment.model.Item;
import com.ssf.assessment.model.Quotation;
import com.ssf.assessment.model.ShippingAddress;
import com.ssf.assessment.service.QuotationService;
import com.ssf.assessment.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping
public class PurchaseOrderController {
    
    @Autowired
    private ShoppingCartService scSvc;
    @Autowired
     private QuotationService qService;

    @GetMapping
    public String getView01(Model model,HttpSession session) {
        
        List<Item> list = (List<Item>) session.getAttribute("cart");

        model.addAttribute("item", new Item());
        model.addAttribute("cart", list);
        return "view1";
    }

    @PostMapping(path = "/additem")
    public String addItem(@Valid Item items,BindingResult result,HttpSession session) {

        if(result.hasErrors()){
            return "redirect:/";
        }
       
        scSvc.addItem(items);
        session.setAttribute("cart", scSvc.getCart());
        
        return "redirect:/";
    }

    @GetMapping(path = "/shippingaddress")
    public String getShipping(Model model,HttpSession session) {
        model.addAttribute("shipping", new ShippingAddress());
        if(session.getAttribute("cart")==null){
            return "redirect:/";
        }
        return "view2";
    }

    @PostMapping(path = "/getQuotation")
    public String getQuotation(@Valid ShippingAddress shipping,BindingResult result,HttpSession session,Model model) {

        if(result.hasErrors()){
            return "redirect:/shippingaddress";
        }

        List<Item> list = (List<Item>) session.getAttribute("cart");
        Checkout checkout = new Checkout(shipping);

        Quotation q = qService.geQuotation2(list);

        checkout.setId(q.getQuoteId());

        Float total = qService.getTotal(list, q);
        checkout.setTotal(total);

        System.out.println(qService.getSet(list));

        model.addAttribute("checkout", checkout);

        session.invalidate();

        return "view3";
    }

}
