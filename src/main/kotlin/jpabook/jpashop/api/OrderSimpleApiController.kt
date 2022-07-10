package jpabook.jpashop.api

import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.repository.OrderRepository
import jpabook.jpashop.repository.OrderSearch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderSimpleApiController(
    @Autowired
    val orderRepository: OrderRepository
) {

    @GetMapping("/api/v1/simple-orders")
    fun ordersV1(): List<Order> {
        val all = orderRepository.findAll(OrderSearch(memberName = "userA", orderStatus = OrderStatus.ORDER))
        for (order in all) {
            order.member?.name
            order.delivery.address
        }
        return all
    }
}