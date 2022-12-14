package catalog_service.dto.input;

import lombok.Data;

@Data
public class OrderqtyDto {

    private Long catalogIdx;

    private Integer qty;
}
