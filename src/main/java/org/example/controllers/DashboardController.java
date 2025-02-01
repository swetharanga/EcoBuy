package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DashboardController {

    @GetMapping("/SustainableProducts")
    public String showSustainableProducts(){

        return  "SustainableProducts";
    }
    @PostMapping("/SustainableProducts")
    public String SustainableProducts()
    {
        return "redirect:/SustainableProducts";
    }

    @GetMapping("/RecycledProducts")
    public String showRecycleProducts(){
        return "RecycledProducts";
    }

    @PostMapping("/RecycledProducts")
    public String recycleProducts()
    {
        return "redirect:/RecycledProducts";
    }

    @GetMapping("/OrganicProducts")
    public String showOrganicProducts(){
        return  "OrganicProducts";
    }

    @PostMapping("/OrganicProducts")
    public String OrganicProducts()
    {
        return "redirect:/OrganicProducts";
    }

    @GetMapping("/EnergyEfficientProducts")
    public String showEnergyEfficientProducts(){
        return  "/EnergyEfficientProducts";
    }

    @PostMapping("/EnergyEfficientProducts")
    public String EnergyEfficientProducts()
    {
        return "redirect:/EnergyEfficientProducts";
    }

    @GetMapping("/Profile")
    public String showProfile(){
        return  "Profile";
    }

    @PostMapping("/Profile")
    public String Profile()
    {
        return "redirect:/Profile";
    }

    @GetMapping("/ShoppingCart")
    public String showShoppingCart(){
        return  "redirect:/api/cart/ShoppingCart";
    }
    @GetMapping("/Orders")
    public String showOrders(){
        return  "Orders";
    }

    @PostMapping("/Orders")
    public String Orders()
    {
        return "redirect:/Orders";
    }

    @GetMapping("/PaymentMethod")
    public String showPaymentMethod(){
        return  "PaymentMethod";
    }

    @PostMapping("/PaymentMethod")
    public String PaymentMethod()
    {
        return "redirect:/PaymentMethod";
    }


}