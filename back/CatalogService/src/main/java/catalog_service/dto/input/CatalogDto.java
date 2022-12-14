package catalog_service.dto.input;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class CatalogDto implements Serializable {
    private Long catalogIdx;

    private String name;
    private String content;
    private Integer stock;
    private Integer price;

    private Long userIdx;
}
