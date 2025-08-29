package com.example.mi_ecommerze.service;

import com.example.mi_ecommerze.entity.Categoria;
import com.example.mi_ecommerze.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria obtenerCategoriaPorId(Long id){
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrado con id " + id));
    }

    public Categoria crearCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
    public Categoria actualizarCategoria(Categoria categoria, Long id){
        Categoria categoriaActualizado = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrado con id " + id));

        categoriaActualizado.setNombre(categoria.getNombre());
        categoriaActualizado.setDescripcion(categoria.getDescripcion());
        categoriaActualizado.setImagen(categoria.getImagen());
        return categoriaRepository.save(categoriaActualizado);
    }

    public void eliminarCategoria(Long id){
        if(!categoriaRepository.existsById(id)){
            throw new RuntimeException("Categoria no encontrado con id " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
