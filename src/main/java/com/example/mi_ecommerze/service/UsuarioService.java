package com.example.mi_ecommerze.service;

import com.example.mi_ecommerze.entity.Usuario;
import com.example.mi_ecommerze.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(Long id){
        return usuarioRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));
    }

    public Usuario guardarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Usuario usuario, Long id){
        Usuario usuarioActualizado = usuarioRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + id));

        usuarioActualizado.setNombre(usuario.getNombre());
        usuarioActualizado.setEmail(usuario.getEmail());
        usuarioActualizado.setDireccion(usuario.getDireccion());
        usuarioActualizado.setEdad(usuario.getEdad());

        return usuarioRepository.save(usuarioActualizado);
    }

    public void eliminarUsuario(Long id){
        if(!usuarioRepository.existsById(id)){
            throw new RuntimeException("Usuario no encontrado con id " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
