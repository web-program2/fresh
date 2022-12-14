package catalog_service.service;

import catalog_service.client.UserServiceClient;
import catalog_service.dto.input.CatalogDto;
import catalog_service.dto.output.CatalogUserDto;
import catalog_service.jpa.Catalog;
import catalog_service.jpa.CatalogRepo;
import catalog_service.vo.ResponseUser;
import com.google.inject.internal.ErrorsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{
    private final CatalogRepo catalogRepo;
    private final UserServiceClient userServiceClient;


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
        ResponseUser responseUser = userServiceClient.getResponseUser(catalog.getUserIdx());
        catalogUserDto.setResponseUser(responseUser);
        return catalogUserDto;
    }

    //1. cateIdx catelog 조회
    //2. catalog.getUserIdx 로 ResponseUser 받아오기
    //3. dto 로 catalog responseUser- catalogUserDto
    @Transactional
    @Override
    public boolean deleteCatalog(Long catalogIdx) {
//        Catalog catalog = getCatalog(catalogIdx);
//        boolean res = catalogRepo.deleteCatalog(catalog);
//        return res;
        return false;
    }



}
