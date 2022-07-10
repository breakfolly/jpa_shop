package jpabook.jpashop

import jpabook.jpashop.domain.*
import jpabook.jpashop.domain.Item.Book
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class InitDB(
    val initService: InitService
) {

    @PostConstruct
    fun init() {
        initService.dbInit1()
        initService.dbInit2()
    }

    @Component
    @Transactional
    class InitService {
        @PersistenceContext
        private lateinit var em : EntityManager

        fun dbInit1() {
            val member = Member(
                name = "userA",
                address = Address(city = "서울", street = "1", zipcode = "1111")
            )
            em.persist(member)

            val book1 = Book(name = "JPA1 BOOK", price = 10000, stockQuantity = 100)
            em.persist(book1)

            val book2 = Book(name = "JPA2 BOOK", price = 20000, stockQuantity = 100)
            em.persist(book2)

            val orderItem1 = OrderItem.createOrderItem(book1, 10000, 1)
            val orderItem2 = OrderItem.createOrderItem(book2, 20000, 2)

            val order = Order.createOrder(member, Delivery(address = member.address), listOf(orderItem1, orderItem2))
            em.persist(order)
        }

        fun dbInit2() {
            val member = Member(
                name = "userB",
                address = Address(city = "진주", street = "2", zipcode = "2222")
            )
            em.persist(member)

            val book1 = Book(name = "SPRING1 BOOK", price = 20000, stockQuantity = 200)
            em.persist(book1)

            val book2 = Book(name = "SPRING2 BOOK", price = 40000, stockQuantity = 300)
            em.persist(book2)

            val orderItem1 = OrderItem.createOrderItem(book1, 20000, 3)
            val orderItem2 = OrderItem.createOrderItem(book2, 40000, 4)

            val order = Order.createOrder(member, Delivery(address = member.address), listOf(orderItem1, orderItem2))
            em.persist(order)
        }

    }
}

