package com.proyecto1.principal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1.principal.model.Usuario;
import com.proyecto1.principal.model.dto.UsuarioDto;
import com.proyecto1.principal.model.entity.UsuarioEntity;
import com.proyecto1.principal.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crearUsuario")
    // responseEntity responde segun la accion o resultado
    // 404 no se encuentra el recurso
    // 200 significa ok
    public ResponseEntity<String> obtenerUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @GetMapping("/obtenerUsuario/correo/{correo}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String correo){
        Usuario usuario = usuarioService.obtenerUsuario(correo);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/obtenerUsuario/id/{idUsuario}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioDto(@PathVariable int idUsuario) {
        if (usuarioService.obtenerUsuarioDto(idUsuario) != null) {
            return ResponseEntity.ok(usuarioService.obtenerUsuarioDto(idUsuario));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listarUsuarios")
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios() {
    return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }

    
}
