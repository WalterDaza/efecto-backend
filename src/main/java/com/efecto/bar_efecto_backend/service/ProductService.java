package com.efecto.bar_efecto_backend.service;

import com.efecto.bar_efecto_backend.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    public ProductDTO createProduct(ProductDTO productDTO);

    public List<ProductDTO> getProducts ();

    public ProductDTO getProductById (long id);

    public ProductDTO updateProduct (ProductDTO productDTO, long id);

    public void removeProduct (long id);
}
