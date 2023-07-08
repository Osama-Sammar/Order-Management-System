package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.dto.ProductOrderDto;
import org.example.service.ProductOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-orders")
@Api(tags = "Product Orders")
public class ProductOrderController {

    private final ProductOrderService productOrderService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductOrderController(ProductOrderService productOrderService, ModelMapper modelMapper) {
        this.productOrderService = productOrderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @ApiOperation("Get all product orders")
    public List<ProductOrderDto> getAllProductOrders() {
        List<ProductOrderDto> productOrderDtos = productOrderService.getAllProductOrders();
        return productOrderDtos;
    }

    @GetMapping("/order/{orderId}")
    @ApiOperation("Get product orders by order")
    public List<ProductOrderDto> getProductOrdersByOrder(@PathVariable Integer orderId) {
        List<ProductOrderDto> productOrderDtos = productOrderService.getProductOrdersByOrder(orderId);
        return productOrderDtos;
    }

    @PostMapping("/order/{orderId}")
    @ApiOperation("Create a product order")
    public ResponseEntity<ProductOrderDto> createProductOrder(
            @PathVariable Integer orderId,
            @RequestBody ProductOrderDto productOrderDto
    ) {
        ProductOrderDto createdProductOrderDto = productOrderService.createProductOrder(orderId, productOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductOrderDto);
    }

    @PutMapping("/order/{orderId}/product/{productOrderId}")
    @ApiOperation("Update a product order")
    public ResponseEntity<ProductOrderDto> updateProductOrder(
            @PathVariable Integer orderId,
            @PathVariable Integer productOrderId,
            @RequestBody ProductOrderDto productOrderDto
    ) {
        ProductOrderDto updatedProductOrderDto = productOrderService.updateProductOrder(orderId, productOrderId, productOrderDto);
        return ResponseEntity.ok(updatedProductOrderDto);
    }

    @DeleteMapping("/order/{orderId}/product/{productOrderId}")
    @ApiOperation("Delete a product order")
    public ResponseEntity<String> deleteProductOrder(
            @PathVariable Integer orderId,
            @PathVariable Integer productOrderId
    ) {
        productOrderService.deleteProductOrder(orderId, productOrderId);
        return ResponseEntity.ok("Product Order deleted successfully");
    }
}
