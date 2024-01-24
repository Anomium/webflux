package com.aprendizaje.webflux.servicio;

import com.aprendizaje.webflux.entidad.Producto;
import com.aprendizaje.webflux.repositorio.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    public Flux<Producto> getAll() {
        return productoRepositorio.findAll();
    }

    public Mono<Producto> getById(int id){
        return productoRepositorio.findById(id)
                .switchIfEmpty(Mono.error(new Exception("Producto no encontrado.")));
    }

    public Mono<Producto> save(Producto producto) {
        Mono<Boolean> existeNombre = productoRepositorio.findByNombre(producto.getNombre()).hasElement();
        return existeNombre.flatMap(existeNombre1
                -> existeNombre1 ? Mono.error(new Exception("El nombre del producto ya esta en uso"))
                : productoRepositorio.save(producto));
    }

    public Mono<Producto> update(int id, Producto producto) {
        Mono<Boolean> productoId = productoRepositorio.existsById(producto.getId()).hasElement();
        Mono<Boolean> nombreProductoRepetido = productoRepositorio.repeatedNombre(producto.getId(), producto.getNombre()).hasElement();
        return productoId.flatMap(
                existsId -> existsId ?
                        nombreProductoRepetido.flatMap(
                                existsName -> existsName ? Mono.error(new Exception("El nombre del producto ya esta en uso"))
                                        : productoRepositorio.save(new Producto(id, producto.getNombre(), producto.getPrecio()))
                        )
        : Mono.error(new Exception("Producto no encontrado."))
        );
    }

    public Mono<Void> delete(int id) {
        Mono<Boolean> productoId = productoRepositorio.findById(id).hasElement();
        return productoId.flatMap(exists -> exists ? productoRepositorio.deleteById(id)
                : Mono.error(new Exception("Producto no encontrado.")));
    }

}
