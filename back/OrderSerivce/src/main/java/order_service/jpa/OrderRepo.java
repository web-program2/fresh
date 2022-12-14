package order_service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepo{

    private final EntityManager em;
    public void save(Order orderEntity) {
//        em.persist(orderEntity);
    }
    public List<Order> getOrderListbyUserIdx(Long userIdx) {
//        return em.createQuery("select m from Order m where m.userIdx = :userIdx", Order.class)
//                .setParameter("userIdx", userIdx)
//                .getResultList();
        return null;
    }

}
