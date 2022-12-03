package catalog_service.controller;

import catalog_service.jpa.Catalog;
import catalog_service.service.CatalogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
public class CatalogController {

    CatalogService catalogService;
//
    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
//
//    @GetMapping("/health_check")
//    public String status(HttpServletRequest request) {
//        return String.format("It's Working in Catalog Service on Port %s", request.getServerPort());
//    }
//
//    @GetMapping("/catalogs")
//    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
//        Iterable<CatalogEntity> orderList = catalogService.getAllCatalogs();
//        List<ResponseCatalog> result = new ArrayList<>();
//        orderList.forEach(v->{
//            result.add(new ModelMapper().map(v, ResponseCatalog.class));
//        });
//
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

    @PostMapping("/all")
    public List<Catalog> test(@RequestBody int userIdx){
        List<Catalog> catalogList = catalogService.testAll(userIdx);
        System.out.println(catalogList);
        return catalogList;
    }
//    @GetMapping("/{userId}/orders")
//    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId")String userId) {
//        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);
//
//        List<ResponseOrder> result = new ArrayList<>();
//        orderList.forEach(v ->{
//            result.add(new ModelMapper().map(v, ResponseOrder.class));
//        });
//
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

}
