package com.proyecto1.principal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto1.principal.model.Usuario;
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

    @GetMapping("/obtenerUsuario/{correo}")
    public ResponseEntity<Usuario> obetenerUsuario(@PathVariable String correo){
        Usuario usuario = usuarioService.obteneUsuario(correo);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }
    
}
