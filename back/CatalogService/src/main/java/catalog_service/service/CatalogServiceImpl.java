package catalog_service.service;

import catalog_service.jpa.Catalog;
import catalog_service.jpa.CatalogRepo;
import com.google.inject.internal.ErrorsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CatalogServiceImpl implements CatalogService{

    private CatalogRepo catalogRepo;

    @Autowired
    public CatalogServiceImpl(CatalogRepo catalogRepo){
        this.catalogRepo = catalogRepo;
    }

    @Override
    public List<Catalog> testAll(int userIdx) {
        List<Catalog> catalogList = new ArrayList<>();
//        try{
//            catalogList = this.catalogRepo.getCatalogByUserIdx(userIdx);
//        }catch(){
//            throw new ErrorsException();
//        }
        return catalogList;
    }

    @Transactional
    @Override
    public boolean createCatalog(String name, String content, Integer stock, Integer userIdx, Integer price) {
        Catalog catalog = new Catalog();
        catalog.setName(name);
        catalog.setContent(content);
        catalog.setStock(stock);
        catalog.setPrice(price);
        catalog.setUserIdx(userIdx);
        boolean res  = catalogRepo.createCatalog(catalog);
        return res;
    }

    @Transactional
    @Override
    public Catalog updateCatalog(Long catalogIdx, String name, String content, Integer stock, Integer price, Integer userIdx) {
        Catalog catalog = new Catalog();
        catalog.setCatalogIdx(catalogIdx);
        catalog.setName(name);
        catalog.setContent(content);
        catalog.setStock(stock);
        catalog.setPrice(price);
        catalog.setUserIdx(userIdx);
        Catalog res = catalogRepo.updateCatalog(catalog);
        return res;
    }
}
