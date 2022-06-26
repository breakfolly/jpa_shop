package jpabook.jpashop.repository

import jpabook.jpashop.domain.Order
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import jpabook.jpashop.repository.OrderSearch

@Repository
class OrderRepository {

    @PersistenceContext
    private lateinit var em : EntityManager

    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(id: Long): Order {
        return em.find(Order::class.java, id)
    }

    fun findAll(orderSearch: OrderSearch): List<Order> {
        // XXX: 나중에 queryDSL 을 사용하도록 수정
        return em.createQuery("select o from Order o join o.member m " +
                "where o.status = : status " +
                "and m.name like :name", Order::class.java)
            .setParameter("status", orderSearch.orderStatus)
            .setParameter("name", orderSearch.memberName)
            .setMaxResults(1000)
            .resultList
    }
}