package com.efecto.bar_efecto_backend.service;


import com.efecto.bar_efecto_backend.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public ProductDTO createProduct (ProductDTO productDTO);

    public List<ProductDTO> getProducts ();

    public ProductDTO getProductById(Long id);

    public List<ProductDTO> getProductByCategoryId(Long categoryId);

    public ProductDTO updateProduct (Long id, ProductDTO productDTO);

    public void removeProduct (Long id);
}
