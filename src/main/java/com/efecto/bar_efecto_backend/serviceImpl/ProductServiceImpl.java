package com.efecto.bar_efecto_backend.serviceImpl;

import com.efecto.bar_efecto_backend.dto.ProductDTO;
import com.efecto.bar_efecto_backend.exceptions.ResourceNotFoundException;
import com.efecto.bar_efecto_backend.mapper.ProductMapper;
import com.efecto.bar_efecto_backend.model.Product;
import com.efecto.bar_efecto_backend.repository.ProductRepository;
import com.efecto.bar_efecto_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO){

        //Convertimos de DTO a entidad
        Product productRequest = productMapper.toEntity(productDTO);

        Product newProduct = productRepository.save(productRequest); //Se guarda en base de datos con JPA

        //Convertimos de entidad a DTO
        ProductDTO productResponse = productMapper.toDTO(newProduct);

        return productResponse;
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(long id) {
        Product product = productRepository.findById(id) //Se utiliza JPA para buscar por id en la bd
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id)); //Si no encuentra nada utilizamos la excepcion y le pasamos los parametros
        return productMapper.toDTO(product); //Se pasa a DTO para enviar resultado
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));

        product.setNameProduct(productDTO.getNameProduct());
        product.setPricePurchase(productDTO.getPricePurchase());
        product.setPriceSale(productDTO.getPriceSale());
        product.setImage(productDTO.getImage());

        Product saveProduct = productRepository.save(product);

        return productMapper.toDTO(saveProduct);
    }

    @Override
    public void removeProduct(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        productRepository.delete(product);
    }
}
