package com.example.productos.controller;

import com.example.productos.model.Producto;
import com.example.productos.model.Response;
import com.example.productos.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductoById(@PathVariable Long id) throws Exception {
        return productoService.getProductoById(id);
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Response> createProducto(@RequestBody Producto producto) {
        return productoService.createProducto(producto);
    }

    // Actualizar un producto por ID
    @PutMapping("/{id}")
    public ResponseEntity<Response> updateProducto(@PathVariable Long id, @RequestBody Producto producto)
            throws Exception {
        return productoService.updateProducto(id, producto);
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteProducto(@PathVariable Long id) throws Exception {
        return productoService.deleteProducto(id);
    }
}
