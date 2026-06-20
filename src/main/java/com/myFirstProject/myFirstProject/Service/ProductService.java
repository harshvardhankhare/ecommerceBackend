package com.myFirstProject.myFirstProject.Service;

import com.myFirstProject.myFirstProject.DTO.ProductRequestDto;
import com.myFirstProject.myFirstProject.Repository.ProductRepository;
import com.myFirstProject.myFirstProject.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Products insertProduct(ProductRequestDto pr) {

        Products p = new Products();

        p.setTitle(pr.getTitle());
        p.setDescription(pr.getDescription());

        p.setCategory(pr.getCategory());

        p.setPrice(pr.getPrice());
        p.setDiscountPercentage(pr.getDiscountPercentage());

        p.setStock(pr.getStock());

        p.setBrand(pr.getBrand());
        p.setSku(pr.getSku());

        p.setWarrantyInformation(pr.getWarrantyInformation());
        p.setShippingInformation(pr.getShippingInformation());
        p.setAvailabilityStatus(pr.getAvailabilityStatus());
        p.setReturnPolicy(pr.getReturnPolicy());

        p.setMaterial(pr.getMaterial());
        p.setCareInstructions(pr.getCareInstructions());

        p.setThumbnailImage(pr.getThumbnailImage());

        p.setTags(pr.getTags());
        p.setImageUrls(pr.getImageUrls());
        p.setKeyFeatures(pr.getKeyFeatures());

        productRepository.save(p);
        return p;
    }

    public Optional<Products> getPrductById(Long id){
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

    public Products getProductById(Long Id){
        return productRepository.findById(Id).orElseThrow(()-> new RuntimeException(" Product Not Found"));
    }

public void deleteProduct(Long Id){
        Products product = productRepository.findById(Id).orElseThrow(()-> new RuntimeException("Product Not Found"));
        productRepository.delete(product);
}

    public List<Products> getSearchProduct(String q) {

        return productRepository.searchByTitle(q);
    }

    public Products updateProduct(ProductRequestDto reqProduct, Long id) {

        Products product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        product.setTitle(reqProduct.getTitle());
        product.setDescription(reqProduct.getDescription());

        product.setCategory(reqProduct.getCategory());

        product.setPrice(reqProduct.getPrice());
        product.setDiscountPercentage(reqProduct.getDiscountPercentage());

        product.setStock(reqProduct.getStock());

        product.setBrand(reqProduct.getBrand());
        product.setSku(reqProduct.getSku());

        product.setWarrantyInformation(reqProduct.getWarrantyInformation());
        product.setShippingInformation(reqProduct.getShippingInformation());
        product.setAvailabilityStatus(reqProduct.getAvailabilityStatus());
        product.setReturnPolicy(reqProduct.getReturnPolicy());

        product.setMaterial(reqProduct.getMaterial());
        product.setCareInstructions(reqProduct.getCareInstructions());

        product.setThumbnailImage(reqProduct.getThumbnailImage());

        product.setTags(reqProduct.getTags());
        product.setImageUrls(reqProduct.getImageUrls());
        product.setKeyFeatures(reqProduct.getKeyFeatures());

        return productRepository.save(product);
    }


}
