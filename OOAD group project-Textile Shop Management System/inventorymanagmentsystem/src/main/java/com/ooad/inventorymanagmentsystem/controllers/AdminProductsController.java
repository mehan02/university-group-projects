package com.ooad.inventorymanagmentsystem.controllers;

import com.ooad.inventorymanagmentsystem.models.Product;
import com.ooad.inventorymanagmentsystem.models.ProductDto;
import com.ooad.inventorymanagmentsystem.models.Supplier;
import com.ooad.inventorymanagmentsystem.models.SupplierDto;
import com.ooad.inventorymanagmentsystem.repositories.ProductRepository;
import com.ooad.inventorymanagmentsystem.repositories.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminProductsController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private SupplierRepository supplierRepo;

//    @GetMapping({"", "/"})
//    public String showDashBoard(Model model) {
//        return "admin/index";
//    }

    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productRepo.findAll(Sort.by(Sort.Direction.DESC, "productId"));
        model.addAttribute("products", products);
        return "admin/products";
    }

    @GetMapping("/create-product")
    public String showCreatePage(Model model) {
        List<Supplier> suppliers = supplierRepo.findAll();
        ProductDto productDto = new ProductDto(); //add this to page so that we can bind form details
        model.addAttribute("productDto", productDto);
        model.addAttribute("suppliers", suppliers);
        return "admin/create-product";
    }

    //method to creat new products, handle post request form  submitted by creatproduct.html
    @PostMapping("/create-product")
    public String creatProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result, Model model) {

        if (productDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("productDto", "imageFile", "Image file is required"));
        }

        //now check for validation errors, after adding errors for fields
        if (result.hasErrors()) {
            List<Supplier> suppliers = supplierRepo.findAll();
            model.addAttribute("suppliers", suppliers);
            return "admin/create-product";
        }

        //if we dont have error we come here

        MultipartFile image = productDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir, storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception ex) {
            System.out.println("Exception1: " + ex.getMessage());
        }

        //now transfer the productDto that we received from form to new product class
        Product product = new Product();
        SupplierDto supplierDto =productDto.getSupplierDto(); //supplierdto in productdto
        Supplier supplier = supplierRepo.findById(supplierDto.getSupplierId()).get();

        product.setName(productDto.getName());
        product.setSupplier(supplier); // the supplier retrived from supplierdto id
        product.setStockQuantity(productDto.getStockQuantity());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(createdAt);
        product.setImageFileName(storageFileName);

        productRepo.save(product);

        return "redirect:/admin/products";

    }


    @GetMapping("/edit-product")
    public String showEditPage(Model model, @RequestParam int id) {
        try {
            Product product = productRepo.findById(id).orElseThrow(() -> new Exception("Product not found"));
            ProductDto productDto = new ProductDto();

            // Map product fields to DTO
            productDto.setName(product.getName());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setStockQuantity(product.getStockQuantity());
            productDto.setDescription(product.getDescription());

            // Map supplier details to DTO
            Supplier supplier = product.getSupplier();
            if (supplier != null) {
                SupplierDto supplierDto = new SupplierDto();
                supplierDto.setSupplierId(supplier.getSupplierId());
                supplierDto.setSupplierName(supplier.getSupplierName());
                supplierDto.setSupplierContact(supplier.getSupplierContact());
                supplierDto.setSupplierAddress(supplier.getSupplierAddress());
                productDto.setSupplierDto(supplierDto);
            }

            // Add attributes for the view
            List<Supplier> suppliers = supplierRepo.findAll();
            model.addAttribute("suppliers", suppliers);
            model.addAttribute("productDto", productDto);
            model.addAttribute("product", product);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/admin/products";
        }

        return "admin/edit-product";
    }

    @PostMapping("/edit-product")
    public String updateProduct(Model model, @RequestParam int id, @Valid @ModelAttribute ProductDto productDto, BindingResult result) {

        try {
            Product product = productRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
            model.addAttribute("product", product);

            if (result.hasErrors()) {
                return "admin/edit-product"; // Show the current product data and errors
            }

            if (!productDto.getImageFile().isEmpty()) {
                // Define the upload directory
                String uploadDir = "public/images";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath); // Ensure directory exists
                }

                // Delete the old image if it exists
                Path oldImagePath = Paths.get(uploadDir, product.getImageFileName());
                if (Files.exists(oldImagePath)) {
                    try {
                        Files.delete(oldImagePath);
                    } catch (IOException ex) {
                        System.out.println("Error deleting old image: " + ex.getMessage());
                    }
                }

                // Save the new image
                MultipartFile image = productDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()) {
                    Path newImagePath = Paths.get(uploadDir, storageFileName);
                    Files.copy(inputStream, newImagePath, StandardCopyOption.REPLACE_EXISTING);
                    product.setImageFileName(storageFileName); // Update product image filename
                } catch (IOException ex) {
                    System.out.println("Error saving new image: " + ex.getMessage());
                }
            }

            // Update other product details
            Supplier supplier = supplierRepo.findById(productDto.getSupplierDto().getSupplierId()).orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

            product.setName(productDto.getName());
            product.setSupplier(supplier);
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setStockQuantity(productDto.getStockQuantity());
            product.setDescription(productDto.getDescription());

            productRepo.save(product); // Save updated product
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/admin/products";
    }

    @GetMapping("/delete-product")
    public String deleteProduct(@RequestParam int id) {

        try {
            Product product = productRepo.findById(id).get();

            //delete product image
            Path imagePath = Paths.get("/images" + product.getImageFileName());

            try {
                Files.delete(imagePath);
            }
            catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }

            productRepo.delete(product); //delete the product
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return "redirect:/admin/products";
    }

}
