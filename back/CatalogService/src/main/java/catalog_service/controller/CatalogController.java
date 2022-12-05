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
//    @GetMapping()
//    public List<Catalog>

    @PostMapping("/all")
    public List<Catalog> test(@RequestBody int userIdx){
        List<Catalog> catalogList = catalogService.testAll(userIdx);
        System.out.println(catalogList);
        return catalogList;
    }


}
