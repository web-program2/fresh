package order_service.client;

import order_service.dto.OrderqtyDto;
import order_service.vo.ResponseCatalog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "catalog-service")
public interface CatalogServiceClient {

    @PostMapping("/catalog-service/response_catalog")
    ResponseCatalog getResponseCatalog(@RequestBody Long catalogIdx);

    @PostMapping("/catalog-service/request_stock")
    void requestStock(@RequestBody OrderqtyDto orderqtyDto);
}
