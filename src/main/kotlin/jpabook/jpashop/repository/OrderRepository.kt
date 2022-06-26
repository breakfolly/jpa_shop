package jpabook.jpashop.repository

import jpabook.jpashop.domain.Order
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

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

//    fun findAll(orderSearch: OrderSearch): List<Order> {}
}