package catalog_service.controller;

import catalog_service.dto.input.CatalogDto;
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
    @GetMapping()
    public List<Catalog> getCatalogList(){
        List<Catalog> catalogList = catalogService.getCatalogList();
        return catalogList;
    }
    @DeleteMapping("/{catalogIdx}")
    public boolean deleteCatalog(@PathVariable Long catalogIdx){
        boolean res = catalogService.deleteCatalog(catalogIdx);
        return res;
    }
    @GetMapping("/{catalogIdx}")
    public Catalog getCatalog(@PathVariable Long catalogIdx){
        Catalog catalog;
        catalog = catalogService.getCatalog(catalogIdx);
        return catalog;
    }

    @PostMapping("/all")
    public List<Catalog> test(@RequestBody int userIdx){
        List<Catalog> catalogList = catalogService.testAll(userIdx);
        System.out.println(catalogList);
        return catalogList;
    }


}
