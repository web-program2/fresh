package catalog_service.service;

import catalog_service.dto.output.CatalogUserDto;
import catalog_service.jpa.Catalog;

import java.util.List;

public interface CatalogService {

    List<Catalog> testAll(Long userIdx);

    boolean createCatalog(String name, String content, Integer stock, Long userIdx, Integer price);

    Catalog updateCatalog(Long catalogIdx, String name, String content, Integer stock, Integer price, Long userIdx);

    List<Catalog> getCatalogList();
    CatalogUserDto getCatalog(Long catalogIdx);

    boolean deleteCatalog(Long catalogIdx);
}
