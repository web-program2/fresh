package com.example.CatalogService.jpa;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "catalog")
public class CatalogEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catalog_idx;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault( value = "CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(nullable = false)
    private Integer user_Idx;

}
