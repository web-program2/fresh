package catalog_service.service;

import catalog_service.jpa.Catalog;
import catalog_service.jpa.CatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService{

    private CatalogRepo catalogRepo;

    @Autowired
    public CatalogServiceImpl(CatalogRepo catalogRepo){
        this.catalogRepo = catalogRepo;
    }

    @Override
    public List<Catalog> testAll(int userIdx) {
        return this.catalogRepo.getCatalogByUserIdx(userIdx);
    }
}
