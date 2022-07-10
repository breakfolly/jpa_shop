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

    fun findAllWithMemberDelivery(): List<Order> {
        return em.createQuery(
            "select o from Order o " +
                    "join fetch o.member m " +
                    "join fetch o.delivery", Order::class.java
        ).resultList
    }

    fun findOrderDtos(): List<OrderSimpleQueryDto> {
        return em.createQuery(
            "select new jpabook.jpashop.repository.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                    "from Order o " +
                "join o.member m " +
                "join o.delivery d", OrderSimpleQueryDto::class.java)
            .resultList
    }
}