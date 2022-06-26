package jpabook.jpashop.service

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Item.Book
import jpabook.jpashop.domain.Item.Item
import jpabook.jpashop.domain.Member
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.exception.NotEnoughStockException
import jpabook.jpashop.repository.OrderRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
internal class OrderServiceTest @Autowired constructor (
    val orderService: OrderService,
    val orderRepository: OrderRepository
) {
    @PersistenceContext
    private lateinit var em: EntityManager

    @Test
    fun 상품주문() {
        // given
        val member = createMember()
        val book = createBook("시골 JPA", 10000, 10)
        val orderCount = 2

        // when
        val orderId = orderService.order(member.id!!, book.id!!, orderCount)

        //then
        val getOrder = orderRepository.findOne(orderId)
        assertEquals(OrderStatus.ORDER, getOrder.status)
        assertEquals(1, getOrder.orderItems.size)
        assertEquals(10000 * orderCount, getOrder.getTotalPrice())
        assertEquals(8, book.stockQuantity)
    }


    @Test
    fun 상품주문_재고수량초과() {
        // given
        val member = createMember()
        val item: Item = createBook("시골 JPA", 10000, 10)

        val orderCount = 11

        // when
        val notEnoughStockException = assertThrows(NotEnoughStockException::class.java) {
            orderService.order(member.id!!, item.id!!, orderCount)
        }

        //then
        assertEquals("need more stock", notEnoughStockException.message)
    }

    @Test
    fun 주문취소() {
        // given
        val member = createMember()
        val item: Item = createBook("시골 JPA", 10000, 10)
        val orderCount = 2
        val orderId = orderService.order(member.id!!, item.id!!, orderCount)

        // when
        orderService.cancelOrder(orderId)

        // then
        val getOrder = orderRepository.findOne(orderId)

        assertEquals(OrderStatus.CANCEL, getOrder.status)
        assertEquals(10, item.stockQuantity)
    }

    private fun createBook(name: String, price: Int, stockQuantity: Int): Book {
        val book = Book(name = name, price = price, stockQuantity = stockQuantity)
        em.persist(book)
        return book
    }

    private fun createMember(): Member {
        val member = Member(name = "회원1", address = Address(city = "서울", street = "강가", zipcode = "12345"))
        em.persist(member)
        return member
    }
}