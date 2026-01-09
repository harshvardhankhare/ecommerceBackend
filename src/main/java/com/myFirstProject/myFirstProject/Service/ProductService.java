package com.myFirstProject.myFirstProject.Service;

import com.myFirstProject.myFirstProject.DTO.ProductRequestDto;
import com.myFirstProject.myFirstProject.Repository.ProductRepository;
import com.myFirstProject.myFirstProject.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public  void insertProduct(ProductRequestDto pr){
        Products p = new Products();
        p.setProduct_name(pr.getName());
        p.setDiscount(pr.getDiscount());
        p.setCategory(pr.getCategory());
        p.setProduct_price(pr.getPrice().intValue());
        p.setSlug(pr.getSlug());
        p.setImgLink(pr.getImg());
        p.setProduct_desc(pr.getDesc());

        productRepository.save(p);

    }

    public Optional<Products> getPrductById(int id){
     Optional<Products> product = productRepository.findById(id);

     if(product.isPresent()){
         return product;
     }else {
         return null;
     }
    }

    public List<Products> getAllProducts(){

     return   productRepository.findAll();
    }

    public Products getProductById(int Id){

        return productRepository.findById(Id).orElseThrow(()-> new RuntimeException(" Product Not Found"));
    }

public void deleteProduct(int Id){
        Products product = productRepository.findById(Id).orElseThrow(()-> new RuntimeException("Product Not Found"));
        productRepository.delete(product);
}

    public List<Products> getSearchProduct(String q) {

        return productRepository.searchByProductName(q);
    }
}
