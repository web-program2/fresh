package catalog_service.service;

import catalog_service.jpa.Catalog;

import java.util.List;

public interface CatalogService {

    List<Catalog> testAll(int userIdx);

    boolean createCatalog(String name, String content, Integer stock, Integer userIdx, Integer price);

    Catalog updateCatalog(Long catalogIdx, String name, String content, Integer stock, Integer price, Integer userIdx);

    List<Catalog> getCatalogList();
    Catalog getCatalog(Long catalogIdx);

    boolean deleteCatalog(Long catalogIdx);
}
