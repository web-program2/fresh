package catalog_service.controller;

import catalog_service.dto.input.CatalogDto;
import catalog_service.dto.output.CatalogUserDto;
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
@RequestMapping(value = "/catalog-service" )
public class CatalogController {

    CatalogService catalogService;
    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @PostMapping("")
    public boolean createCatalog(@RequestBody CatalogDto catalogDto){
        boolean res = catalogService.createCatalog(catalogDto.getName(), catalogDto.getContent(), catalogDto.getStock(),
                catalogDto.getUserIdx(), catalogDto.getPrice()
        );
        return res;
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

    @GetMapping("/{catalogIdx}")
    public CatalogUserDto getCatalog(@PathVariable Long catalogIdx){
        CatalogUserDto catalogUserDto = catalogService.getCatalog(catalogIdx);
        return catalogUserDto;
    }
    @GetMapping("/test")
    public  String test(){
        return "aaa";
    }

    @GetMapping()
    public List<Catalog> getCatalogList(){
        List<Catalog> catalogList = catalogService.getCatalogList();
        //상품 - 작성자 다 가지고 오기
        return catalogList;
    }


    @DeleteMapping("/{catalogIdx}")
    public boolean deleteCatalog(@PathVariable Long catalogIdx){
        boolean res = catalogService.deleteCatalog(catalogIdx);
        return res;
    }
}
