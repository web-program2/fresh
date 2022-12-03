package user_service.client;

import org.springframework.web.bind.annotation.PostMapping;
import user_service.vo.ResponseCatalog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "catalog-service")
public interface CatalogServiceClient {

    @PostMapping("/catalog-service/all")
    List<ResponseCatalog> testAll (int userIdx);

}
