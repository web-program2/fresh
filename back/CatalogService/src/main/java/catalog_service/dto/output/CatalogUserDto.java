package catalog_service.dto.output;

import catalog_service.jpa.Catalog;
import catalog_service.vo.ResponseUser;
import lombok.Data;

@Data
public class CatalogUserDto {

    private Catalog catalog;
    private ResponseUser responseUser;

}
