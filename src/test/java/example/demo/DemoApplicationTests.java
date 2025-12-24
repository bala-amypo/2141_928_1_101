package com.example.demo;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DemoApplicationTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private StockRecordService stockRecordService;

    @Autowired
    private ConsumptionLogService consumptionLogService;

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testProductCreation() {
        Product product = Product.builder()
                .productName("Test Product")
                .sku("TEST-001")
                .category("Electronics")
                .build();

        Product saved = productService.createProduct(product);
        assertNotNull(saved.getId());
        assertEquals("Test Product", saved.getProductName());
        assertEquals("TEST-001", saved.getSku());
    }

    @Test
    void testProductSkuUniqueness() {
        Product product1 = Product.builder()
                .productName("Product 1")
                .sku("UNIQUE-001")
                .category("Electronics")
                .build();
        productService.createProduct(product1);

        Product product2 = Product.builder()
                .productName("Product 2")
                .sku("UNIQUE-001")
                .category("Electronics")
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            productService.createProduct(product2);
        });
    }

    @Test
    void testWarehouseCreation() {
        Warehouse warehouse = Warehouse.builder()
                .warehouseName("Main Warehouse")
                .location("New York")
                .build();

        Warehouse saved = warehouseService.createWarehouse(warehouse);
        assertNotNull(saved.getId());
        assertEquals("Main Warehouse", saved.getWarehouseName());
        assertEquals("New York", saved.getLocation());
    }

    @Test
    void testStockRecordCreation() {
        Product product = Product.builder()
                .productName("Stock Product")
                .sku("STOCK-001")
                .category("Electronics")
                .build();
        Product savedProduct = productService.createProduct(product);

        Warehouse warehouse = Warehouse.builder()
                .warehouseName("Stock Warehouse")
                .location("California")
                .build();
        Warehouse savedWarehouse = warehouseService.createWarehouse(warehouse);

        StockRecord stockRecord = StockRecord.builder()
                .currentQuantity(100)
                .reorderThreshold(10)
                .build();

        StockRecord saved = stockRecordService.createStockRecord(
                savedProduct.getId(), savedWarehouse.getId(), stockRecord);
        
        assertNotNull(saved.getId());
        assertEquals(100, saved.getCurrentQuantity());
        assertEquals(10, saved.getReorderThreshold());
    }

    @Test
    void testStockRecordDuplicateValidation() {
        Product product = Product.builder()
                .productName("Duplicate Product")
                .sku("DUP-001")
                .category("Electronics")
                .build();
        Product savedProduct = productService.createProduct(product);

        Warehouse warehouse = Warehouse.builder()
                .warehouseName("Duplicate Warehouse")
                .location("Texas")
                .build();
        Warehouse savedWarehouse = warehouseService.createWarehouse(warehouse);

        StockRecord stockRecord1 = StockRecord.builder()
                .currentQuantity(50)
                .reorderThreshold(5)
                .build();
        stockRecordService.createStockRecord(
                savedProduct.getId(), savedWarehouse.getId(), stockRecord1);

        StockRecord stockRecord2 = StockRecord.builder()
                .currentQuantity(30)
                .reorderThreshold(3)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stockRecordService.createStockRecord(
                    savedProduct.getId(), savedWarehouse.getId(), stockRecord2);
        });
        
        assertTrue(exception.getMessage().contains("StockRecord already exists"));
    }

    @Test
    void testConsumptionLogCreation() {
        Product product = Product.builder()
                .productName("Consumption Product")
                .sku("CONS-001")
                .category("Electronics")
                .build();
        Product savedProduct = productService.createProduct(product);

        Warehouse warehouse = Warehouse.builder()
                .warehouseName("Consumption Warehouse")
                .location("Florida")
                .build();
        Warehouse savedWarehouse = warehouseService.createWarehouse(warehouse);

        StockRecord stockRecord = StockRecord.builder()
                .currentQuantity(200)
                .reorderThreshold(20)
                .build();
        StockRecord savedStockRecord = stockRecordService.createStockRecord(
                savedProduct.getId(), savedWarehouse.getId(), stockRecord);

        ConsumptionLog log = ConsumptionLog.builder()
                .consumedQuantity(15)
                .consumedDate(LocalDate.now().minusDays(1))
                .build();

        ConsumptionLog saved = consumptionLogService.logConsumption(
                savedStockRecord.getId(), log);
        
        assertNotNull(saved.getId());
        assertEquals(15, saved.getConsumedQuantity());
    }

    @Test
    void testConsumptionLogFutureDateValidation() {
        Product product = Product.builder()
                .productName("Future Product")
                .sku("FUT-001")
                .category("Electronics")
                .build();
        Product savedProduct = productService.createProduct(product);

        Warehouse warehouse = Warehouse.builder()
                .warehouseName("Future Warehouse")
                .location("Nevada")
                .build();
        Warehouse savedWarehouse = warehouseService.createWarehouse(warehouse);

        StockRecord stockRecord = StockRecord.builder()
                .currentQuantity(150)
                .reorderThreshold(15)
                .build();
        StockRecord savedStockRecord = stockRecordService.createStockRecord(
                savedProduct.getId(), savedWarehouse.getId(), stockRecord);

        ConsumptionLog log = ConsumptionLog.builder()
                .consumedQuantity(10)
                .consumedDate(LocalDate.now().plusDays(1))
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            consumptionLogService.logConsumption(savedStockRecord.getId(), log);
        });
        
        assertTrue(exception.getMessage().contains("consumedDate cannot be future"));
    }

    @Test
    void testPredictionRuleCreation() {
        PredictionRule rule = PredictionRule.builder()
                .ruleName("Standard Rule")
                .averageDaysWindow(30)
                .minDailyUsage(1)
                .maxDailyUsage(100)
                .build();

        PredictionRule saved = predictionService.createRule(rule);
        assertNotNull(saved.getId());
        assertEquals("Standard Rule", saved.getRuleName());
        assertEquals(30, saved.getAverageDaysWindow());
    }

    @Test
    void testUserRegistration() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setName("Test User");
        dto.setEmail("test@example.com");
        dto.setPassword("password123");
        dto.setRoles(Set.of("ROLE_USER"));

        User saved = userService.register(dto);
        assertNotNull(saved.getId());
        assertEquals("Test User", saved.getName());
        assertEquals("test@example.com", saved.getEmail());
    }

    @Test
    void testUserLogin() {
        UserRegisterDto registerDto = new UserRegisterDto();
        registerDto.setName("Login User");
        registerDto.setEmail("login@example.com");
        registerDto.setPassword("password123");
        registerDto.setRoles(Set.of("ROLE_USER"));
        userService.register(registerDto);

        AuthRequest loginRequest = new AuthRequest();
        loginRequest.setEmail("login@example.com");
        loginRequest.setPassword("password123");

        AuthResponse response = userService.login(loginRequest);
        assertNotNull(response.getToken());
        assertEquals("login@example.com", response.getEmail());
    }

    @Test
    void testGetAllProducts() {
        Product product1 = Product.builder()
                .productName("Product A")
                .sku("A-001")
                .category("Category A")
                .build();
        Product product2 = Product.builder()
                .productName("Product B")
                .sku("B-001")
                .category("Category B")
                .build();

        productService.createProduct(product1);
        productService.createProduct(product2);

        var products = productService.getAllProducts();
        assertTrue(products.size() >= 2);
    }

    @Test
    void testGetAllWarehouses() {
        Warehouse warehouse1 = Warehouse.builder()
                .warehouseName("Warehouse A")
                .location("Location A")
                .build();
        Warehouse warehouse2 = Warehouse.builder()
                .warehouseName("Warehouse B")
                .location("Location B")
                .build();

        warehouseService.createWarehouse(warehouse1);
        warehouseService.createWarehouse(warehouse2);

        var warehouses = warehouseService.getAllWarehouses();
        assertTrue(warehouses.size() >= 2);
    }

    @Test
    void testGetAllPredictionRules() {
        PredictionRule rule1 = PredictionRule.builder()
                .ruleName("Rule A")
                .averageDaysWindow(30)
                .minDailyUsage(1)
                .maxDailyUsage(50)
                .build();
        PredictionRule rule2 = PredictionRule.builder()
                .ruleName("Rule B")
                .averageDaysWindow(60)
                .minDailyUsage(2)
                .maxDailyUsage(100)
                .build();

        predictionService.createRule(rule1);
        predictionService.createRule(rule2);

        var rules = predictionService.getAllRules();
        assertTrue(rules.size() >= 2);
    }
}