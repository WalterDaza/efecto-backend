package com.efecto.bar_efecto_backend.serviceImpl;

import com.efecto.bar_efecto_backend.dto.ProductDTO;
import com.efecto.bar_efecto_backend.mapper.ProductMapper;
import com.efecto.bar_efecto_backend.model.Product;
import com.efecto.bar_efecto_backend.repository.ProductRepository;
import com.efecto.bar_efecto_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    /*public ProductDTO createProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setNameProduct(productDTO.getNameProduct());
        product.setPricePurchase(productDTO.getPricePurchase());
        product.setPriceSale(productDTO.getPriceSale());
        product.setImage(productDTO.getImage());

        Product newProduct = productRepository.save(product);

        ProductDTO productResponse = new ProductDTO();
        productResponse.setId(newProduct.getId());
        productResponse.setNameProduct(newProduct.getNameProduct());
        productResponse.setPricePurchase(newProduct.getPricePurchase());
        productResponse.setPriceSale(newProduct.getPriceSale());
        productResponse.setImage(newProduct.getImage());

        return productResponse;

    }*/

    public ProductDTO createProduct(ProductDTO productDTO){

        System.out.println("DTO nameProduct: " + productDTO.getNameProduct());

        //Convertimos de DTO a entidad
        Product productRequest = productMapper.toEntity(productDTO);

        Product newProduct = productRepository.save(productRequest); //Se guarda en base de datos con JPA

        //Convertimos de entidad a DTO
        ProductDTO productResponse = productMapper.toDTO(newProduct);

        return productResponse;
    }

    /*@Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> );
    }*/
}
