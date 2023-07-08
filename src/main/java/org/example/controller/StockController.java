package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.dto.StockDto;
import org.example.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stocks")
@Api(tags = "Stock API")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    @ApiOperation("Get all stocks")
    public ResponseEntity<List<StockDto>> getAllStocks() {
        List<StockDto> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/{productId}")
    @ApiOperation("Get stocks by product ID")
    public ResponseEntity<List<StockDto>> getStocksByProductId(@PathVariable Integer productId) {
        List<StockDto> stocks = stockService.getStocksByProductId(productId);
        return ResponseEntity.ok(stocks);
    }

    @PostMapping("/{productId}")
    @ApiOperation("Create a new stock")
    public ResponseEntity<StockDto> createStock(
            @PathVariable Integer productId,
            @RequestBody StockDto stockDto
    ) {
        StockDto createdStock = stockService.createStock(productId, stockDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }

    @PutMapping("/{stockId}")
    @ApiOperation("Update an existing stock")
    public ResponseEntity<StockDto> updateStock(
            @PathVariable Integer stockId,
            @RequestBody StockDto stockDto
    ) {
        StockDto updatedStock = stockService.updateStock(stockId, stockDto);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/{stockId}")
    @ApiOperation("Delete a stock by ID")
    public ResponseEntity<Void> deleteStock(@PathVariable Integer stockId) {
        stockService.deleteStock(stockId);
        return ResponseEntity.noContent().build();
    }
}
