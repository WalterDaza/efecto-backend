package com.efecto.bar_efecto_backend.serviceImpl;

import com.efecto.bar_efecto_backend.dto.CategoryDTO;
import com.efecto.bar_efecto_backend.exceptions.ResourceNotFoundException;
import com.efecto.bar_efecto_backend.mapper.CategoryMapper;
import com.efecto.bar_efecto_backend.model.Category;
import com.efecto.bar_efecto_backend.repository.CategoryRepository;
import com.efecto.bar_efecto_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        //Convertimos de DTO a entidad
        Category categoryRequest = categoryMapper.toEntity(categoryDTO);

        Category newCategory = categoryRepository.save(categoryRequest); //Se guarda en base de datos con JPA

        //Convertimos de entidad a DTO
        CategoryDTO categoryResponse = categoryMapper.toDTO(newCategory);

        return categoryResponse;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> categoryMapper.toDTO(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(long id) {
        Category category = categoryRepository.findById(id) //Se utiliza JPA para buscar por id en la bd
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id)); //Si no encuentra nada utilizamos la excepcion y le pasamos los parametros
        return categoryMapper.toDTO(category); //Se pasa a DTO para enviar resultado
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));

        category.setNameCategory(categoryDTO.getNameCategory());
        category.setImage(categoryDTO.getImage());

        Category saveCategory = categoryRepository.save(category);

        return categoryMapper.toDTO(saveCategory);
    }

    @Override
    public void removeCategory(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", "id", id));
        categoryRepository.delete(category);
    }
}
