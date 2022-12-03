package catalog_service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CatalogRepo {

    final EntityManager em;

    public List<Catalog> getCatalogByUserIdx(int userIdx) {
        return em.createQuery("select m from Catalog m where m.userIdx = :userIdx", Catalog.class)
                .setParameter("userIdx", userIdx)
                .getResultList();
    }


}
