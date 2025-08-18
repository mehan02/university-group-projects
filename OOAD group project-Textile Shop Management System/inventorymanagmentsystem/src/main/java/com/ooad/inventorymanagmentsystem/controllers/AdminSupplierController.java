package com.ooad.inventorymanagmentsystem.controllers;

import com.ooad.inventorymanagmentsystem.models.Supplier;
import com.ooad.inventorymanagmentsystem.models.SupplierDto;
import com.ooad.inventorymanagmentsystem.repositories.ProductRepository;
import com.ooad.inventorymanagmentsystem.repositories.SupplierRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminSupplierController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private SupplierRepository supplierRepo;


    @GetMapping("/suppliers")
    public String showSuppliers(Model model) {
        List<Supplier> suppliers = supplierRepo.findAll(Sort.by(Sort.Direction.DESC, "supplierId"));
        model.addAttribute("suppliers", suppliers);
        return "admin/suppliers";
    }

    @GetMapping("/create-supplier")
    public String showCreatePage(Model model) {
        SupplierDto supplierDto = new SupplierDto(); //add this to page so that we can bind form details
        model.addAttribute("supplierDto", supplierDto);
        return "admin/create-supplier";
    }

    //method to creat new products, handle post request form  submitted by creatproduct.html
    @PostMapping("/create-supplier")
    public String creatSupplier(@Valid @ModelAttribute SupplierDto supplierDto, BindingResult result) {


        //now check for validation errors, after adding errors for fields
        if (result.hasErrors()) {
            //return "admin/create-supplier";
        }

        //if we dont have error we come here


        //now transfer the productDto that we received from form to new product class
        Supplier supplier = new Supplier();

        supplier.setSupplierName(supplierDto.getSupplierName());
        supplier.setSupplierAddress(supplierDto.getSupplierAddress());
        supplier.setSupplierContact(supplierDto.getSupplierContact());

        supplierRepo.save(supplier);

        return "redirect:/admin/suppliers";

    }

    @GetMapping("/edit-supplier")
    public String showEditPage(Model model, @RequestParam int id) {

        try {
            Supplier supplier = supplierRepo.findById(id).get();
            model.addAttribute("supplier", supplier);


            SupplierDto supplierDto = new SupplierDto();

            supplierDto.setSupplierName(supplier.getSupplierName());
            supplierDto.setSupplierAddress(supplier.getSupplierAddress());
            supplierDto.setSupplierContact(supplier.getSupplierContact());

            model.addAttribute("supplierDto", supplierDto);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/admin/suppliers";
        }

        return "admin/edit-supplier";
    }

    @PostMapping("/edit-supplier")
    public String updateSupplier(Model model, @RequestParam int id, @Valid @ModelAttribute SupplierDto supplierDto, BindingResult result) {

        try {
            Supplier supplier = supplierRepo.findById(id).get();
            model.addAttribute("supplier", supplier);

            if (result.hasErrors()) {
                return "admin/edit-supplier"; //we can view the current data since we have product object and productdto accessible here.
            }

            supplier.setSupplierName(supplierDto.getSupplierName());
            supplier.setSupplierAddress(supplierDto.getSupplierAddress());
            supplier.setSupplierContact(supplierDto.getSupplierContact());


            supplierRepo.save(supplier);
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/admin/suppliers";
    }

    @GetMapping("/delete-suppliers")
    public String deleteSupplier(@RequestParam int id) {

        try {

            Supplier supplier = supplierRepo.findById(id).get();

            supplierRepo.delete(supplier);

        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/admin/suppliers";
    }

}
