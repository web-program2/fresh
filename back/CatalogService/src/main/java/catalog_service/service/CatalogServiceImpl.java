package catalog_service.service;

import catalog_service.client.UserServiceClient;
import catalog_service.dto.input.CatalogDto;
import catalog_service.dto.output.CatalogUserDto;
import catalog_service.jpa.Catalog;
import catalog_service.jpa.CatalogRepo;
import catalog_service.vo.ResponseUser;
import com.google.inject.internal.ErrorsException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{
    private final CatalogRepo catalogRepo;
    private final UserServiceClient userServiceClient;

    private final CircuitBreakerFactory circuitBreakerFactory;

    @Override
    public List<Catalog> testAll(Long userIdx) {
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
    public boolean createCatalog(String name, String content, Integer stock, Long userIdx, Integer price) {
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
    public Catalog updateCatalog(Long catalogIdx, String name, String content, Integer stock, Integer price, Long userIdx) {
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

    @Override
    public List<Catalog> getCatalogList() {
        List<Catalog> catalogList = catalogRepo.getCatalogList();
        return catalogList;
    }

    //catalogIdx로 User+Catalog 가져오기
    @Override
    public CatalogUserDto getCatalog(Long catalogIdx) {
        List<Catalog> catalogList = catalogRepo.getCatalogList();
        Catalog catalog = null;
        for(int i = 0; i < catalogList.size(); i++){
            if(catalogList.get(i).getCatalogIdx() == catalogIdx){
                catalog = catalogList.get(i);
            }
        }

        //catalogUserDto 추가
        CatalogUserDto catalogUserDto = new CatalogUserDto();
        catalogUserDto.setCatalog(catalog);

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        Catalog finalCatalog = catalog;
        ResponseUser responseUser = circuitBreaker.run(() ->
                userServiceClient.getResponseUser(finalCatalog.getUserIdx()),
                        throwable -> null
                    );

        catalogUserDto.setResponseUser(responseUser);
        return catalogUserDto;
    }

    public ResponseUser getDefaultUser() {
        ResponseUser responseUser = new ResponseUser();
        return responseUser;
    }
        @Transactional
    @Override
    public boolean deleteCatalog(Long catalogIdx) {
        List<Catalog> catalogList = catalogRepo.getCatalogList();
        Catalog catalog = null;
        for(int i = 0; i < catalogList.size(); i++){
            if(catalogList.get(i).getCatalogIdx() == catalogIdx){
                catalog = catalogList.get(i);
            }
        }
        boolean res = catalogRepo.deleteCatalog(catalog);
        return res;
    }

    @Override
    public Catalog findCatalog(Long catalogIdx) {
        Catalog catalog = catalogRepo.getCatalogbyIdx(catalogIdx).get(0);
        return catalog;
    }

    @Transactional
    @Override
    public void updateStock(Long catalogIdx, Integer qty) {
        List<Catalog> catalogList = catalogRepo.getCatalogList();
        Catalog catalog = null;
        for(int i = 0; i < catalogList.size(); i++){
            if(catalogList.get(i).getCatalogIdx() == catalogIdx){
                catalog = catalogList.get(i);
            }
        }
        catalogRepo.updateStock(catalogIdx, catalog.getStock()-qty);
    }


}
