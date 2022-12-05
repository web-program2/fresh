package catalog_service.service;

import catalog_service.jpa.Catalog;

import java.util.List;

public interface CatalogService {

    List<Catalog> testAll(int userIdx);

    boolean createCatalog(String name, String content, Integer stock, Integer userIdx, Integer price);
}
