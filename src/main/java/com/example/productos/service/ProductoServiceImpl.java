package com.example.productos.service;

import com.example.productos.model.Producto;
import com.example.productos.model.Response;
import com.example.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // Obtener producto por ID
    @Override
    public ResponseEntity<Response> getProductoById(Long idProducto) {
        Response response;
        Optional<Producto> productoOpt = productoRepository.findById(idProducto);
        if (!productoOpt.isPresent()) {
            response = new Response("error", null, "Producto no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        response = new Response("success", productoOpt.get(), "");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Crear un nuevo producto
    @Override
    public ResponseEntity<Response> createProducto(Producto producto) {
        producto.setFechaCreacion(LocalDate.now());
        Producto nuevoProducto = productoRepository.save(producto);
        Response response = new Response("success", nuevoProducto, "");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Actualizar un producto existente
    @Override
    public ResponseEntity<Response> updateProducto(Long idProducto, Producto productoActualizado) {
        Response response;
        Optional<Producto> productoOpt = productoRepository.findById(idProducto);
        if (!productoOpt.isPresent()) {
            response = new Response("error", null, "Producto no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Producto productoExistente = productoOpt.get();
        // Actualizar los campos del producto
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setCategoria(productoActualizado.getCategoria());
        productoExistente.setEstado(productoActualizado.getEstado());
        productoExistente.setFechaModificacion(LocalDate.now());

        Producto productoActualizadoResult = productoRepository.save(productoExistente);
        response = new Response("success", productoActualizadoResult, "");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Eliminar un producto por ID
    @Override
    public ResponseEntity<Response> deleteProducto(Long idProducto) {
        Response response;
        Optional<Producto> productoOpt = productoRepository.findById(idProducto);
        if (!productoOpt.isPresent()) {
            response = new Response("error", null, "Producto no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        productoRepository.deleteById(idProducto);
        response = new Response("success", "Producto eliminado con éxito", "");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}