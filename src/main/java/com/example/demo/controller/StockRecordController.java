@RestController
@RequestMapping("/api/stocks")
public class StockRecordController {

    private final StockRecordService service;

    public StockRecordController(StockRecordService service) {
        this.service = service;
    }

    @PostMapping
    public StockRecord create(@RequestBody StockRecord stockRecord) {
        return service.save(stockRecord);
    }

    @GetMapping
    public List<StockRecord> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public StockRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/product/{productId}")
    public List<StockRecord> getByProduct(@PathVariable Long productId) {
        return service.getRecordsByProduct(productId);
    }
}
