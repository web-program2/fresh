package catalog_service.controller;

import catalog_service.dto.input.CatalogDto;
import catalog_service.dto.input.OrderqtyDto;
import catalog_service.dto.output.CatalogUserDto;
import catalog_service.jpa.Catalog;
import catalog_service.service.CatalogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/catalog-service" )
public class CatalogController {

    //모든 카탈로그 리스트 가져오기
    //카탈로그 인덱스로 카랄로그-유저 가져오기
    //오더
    //유저인덱스로 오더리스트 가져오기
    //오더인덱스로 오더유저카탈로그 가져오기
    CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
    @GetMapping("/test")
    public  String test(){
        return "aaa";
    }

    @PostMapping("/")
    public boolean createCatalog(@RequestBody CatalogDto catalogDto){
        boolean res = catalogService.createCatalog(catalogDto.getName(), catalogDto.getContent(), catalogDto.getStock(),
                catalogDto.getUserIdx(), catalogDto.getPrice()
        );
        return res;
    }

    @GetMapping("/{catalogIdx}")
    public CatalogUserDto getCatalog(@PathVariable Long catalogIdx){
        CatalogUserDto catalogUserDto = catalogService.getCatalog(catalogIdx);
        return catalogUserDto;
    }
    @PostMapping("/response_catalog")
    public Catalog getResponseCatalog(@RequestBody Long catalogIdx){
        Catalog catalog = catalogService.findCatalog(catalogIdx);
        return catalog;
    }

    @GetMapping("/catalog_list")
    public List<Catalog> getCatalogList(){
        List<Catalog> catalogList = catalogService.getCatalogList();
        return catalogList;
    }

    @PutMapping("")
    public Catalog updateCatalog(@RequestBody CatalogDto catalogDto){
        Catalog res = catalogService.updateCatalog(catalogDto.getCatalogIdx(),
                catalogDto.getName(), catalogDto.getContent(),
                catalogDto.getStock(),  catalogDto.getPrice(),
                catalogDto.getUserIdx()
        );
        return res;
    }
    @DeleteMapping("/{catalogIdx}")
    public boolean deleteCatalog(@PathVariable Long catalogIdx){
        boolean res = catalogService.deleteCatalog(catalogIdx);
        return res;
    }

    @PostMapping("/request_stock")
    public void requestStock(@RequestBody OrderqtyDto orderqtyDto){
        catalogService.updateStock(orderqtyDto.getCatalogIdx(), orderqtyDto.getQty());
    }
}
