package com.aprendizaje.webflux.controlador;

import com.aprendizaje.webflux.entidad.Producto;
import com.aprendizaje.webflux.servicio.ProductoServicio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/producto")
@Slf4j
@RequiredArgsConstructor
public class ProductoControlador {

    private final ProductoServicio productoServicio;

    @GetMapping
    public Flux<Producto> getAll() {
        return productoServicio.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Producto> getById(@PathVariable int id) {
        return productoServicio.getById(id);
    }

    @PostMapping
    public Mono<Producto> save(@RequestBody Producto producto) {
        return productoServicio.save(producto);
    }

    @PutMapping("/{id}")
    public Mono<Producto> update(@PathVariable int id, @RequestBody Producto producto) {
        return productoServicio.update(id, producto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable int id) {
        return productoServicio.delete(id);
    }

}
