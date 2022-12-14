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


    public boolean createCatalog(Catalog catalog) {
        em.persist(catalog);
        return true;
    }
    public Catalog updateCatalog(Catalog catalog){
        return em.merge(catalog);
    }

    public List<Catalog> getCatalogList() {
        return em.createQuery("select e from Catalog e", Catalog.class).getResultList();
    }
    public boolean deleteCatalog(Catalog catalog) {
        System.out.println(catalog);
        em.remove(catalog);
        return true;
    }

    public List<Catalog> getCatalogbyIdx(Long catalogIdx) {
        return em.createQuery("select m from Catalog m where m.catalogIdx = :catalogIdx", Catalog.class)
                .setParameter("catalogIdx", catalogIdx)
                .getResultList();
    }
}
