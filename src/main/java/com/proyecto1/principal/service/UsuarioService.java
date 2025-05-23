package com.proyecto1.principal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto1.principal.model.Usuario;
import com.proyecto1.principal.model.dto.UsuarioDto;
import com.proyecto1.principal.model.entity.UsuarioEntity;
import com.proyecto1.principal.repository.UsuarioRepository;

@Service

public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String crearUsuario(Usuario user){
        
        try{
            Boolean estado = usuarioRepository.existsByCorreo(user.getCorreo());
            if (!estado){
                UsuarioEntity usuarioNuevo = new UsuarioEntity();
                    usuarioNuevo.setIdUsuario(user.getIdUsuario());
                    usuarioNuevo.setNombre(user.getNombre());
                    usuarioNuevo.setApellidos(user.getApellidos());
                    usuarioNuevo.setCorreo(user.getCorreo());
                    usuarioNuevo.setContrasena(user.getContrasena());
                    usuarioRepository.save(usuarioNuevo);
                    return "Usuario creado correctamente";
                }    
                return "el correo ya existe";
                } 
        catch (Exception e) {
            return "Error al crear el usuario " + e.getMessage();
        }

        }

    public Usuario obtenerUsuario(String correo){
        try{
            UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
            if (usuario != null){
                Usuario user = new Usuario(
                    usuario.getIdUsuario(),
                    usuario.getNombre(),
                    usuario.getApellidos(),
                    usuario.getCorreo(),
                    usuario.getContrasena()
                );
                return user;
            }
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            // para ver el error en consola
            return null;
        }
    }

    public UsuarioDto obtenerUsuarioDto(int idUsuario){
        try{
            UsuarioEntity usuario = usuarioRepository.findByIdUsuario(idUsuario);
            if (usuario != null){
                UsuarioDto user = new UsuarioDto(
                    usuario.getNombre(),
                    usuario.getApellidos(),
                    usuario.getCorreo()
                );
                return user;
            }
            return null;
        }catch (Exception e) {
            return null;
        }
    }

    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
    return usuarioRepository.findAll();
    }


}



