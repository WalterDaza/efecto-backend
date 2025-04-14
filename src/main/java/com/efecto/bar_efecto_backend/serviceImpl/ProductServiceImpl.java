package com.efecto.bar_efecto_backend.serviceImpl;

import com.efecto.bar_efecto_backend.dto.ProductDTO;
import com.efecto.bar_efecto_backend.exceptions.ResourceNotFoundException;
import com.efecto.bar_efecto_backend.mapper.ProductMapper;
import com.efecto.bar_efecto_backend.model.Category;
import com.efecto.bar_efecto_backend.model.Product;
import com.efecto.bar_efecto_backend.repository.CategoryRepository;
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
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductDTO createProduct(ProductDTO productDTO) {
        // Validar que la categoría exista
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + productDTO.getCategoryId()));

        // Convertir DTO a entidad
        Product product = productMapper.toEntity(productDTO);

        // Asegurarse de que la categoría es la correcta (aunque mapStruct la puede llenar con solo el ID)
        product.setCategory(category);

        // Guardar en base de datos
        Product savedProduct = productRepository.save(product);

        // Devolver DTO
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> productsList = productRepository.findAll();
        return productsList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        return productMapper.toDTO(product);
    }

    @Override
    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
        List <Product> productsListByCtegory = productRepository.findByCategoryId(categoryId);
        return productsListByCtegory.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));

        product.setNameProduct(productDTO.getNameProduct());
        product.setPricePurchase(productDTO.getPricePurchase());
        product.setPriceSale(productDTO.getPriceSale());
        product.setImage(productDTO.getImage());

        // Buscar la categoría y asignarla al producto
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría", "id", productDTO.getCategoryId()));

        product.setCategory(category);

        Product saveProduct = productRepository.save(product);

        return productMapper.toDTO(saveProduct);
    }

    @Override
    public void removeProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));

        productRepository.delete(product);
    }


}
