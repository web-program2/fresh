package catalog_service.client;

import catalog_service.vo.ResponseUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "user-service")
public interface UserServiceClient {

    @PostMapping("/user-service/response_user")
    ResponseUser getResponseUser(@RequestBody Long userIdx);

}
