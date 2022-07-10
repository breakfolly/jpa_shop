package jpabook.jpashop.api

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.Order
import jpabook.jpashop.domain.OrderStatus
import jpabook.jpashop.repository.OrderRepository
import jpabook.jpashop.repository.OrderSearch
import jpabook.jpashop.repository.OrderSimpleQueryDto
import lombok.Data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

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


    @GetMapping("/api/v2/simple-orders")
    fun ordersV2(): List<SimpleOrderDto> {
        val orders = orderRepository.findAll(OrderSearch(memberName = "userA", orderStatus = OrderStatus.ORDER))
        return orders.map { order ->
            SimpleOrderDto(
                orderId = order.id!!,
                name = order.member?.name!!,
                orderDate = order.orderDate,
                orderStatus = order.status,
                address = order.delivery.address!!)
        }
    }

    @GetMapping("/api/v3/simple-orders")
    fun ordersV3(): List<SimpleOrderDto> {
        val orders = orderRepository.findAllWithMemberDelivery()
        return orders.map { order ->
            SimpleOrderDto(
                orderId = order.id!!,
                name = order.member?.name!!,
                orderDate = order.orderDate,
                orderStatus = order.status,
                address = order.delivery.address!!)
        }
    }

    @GetMapping("/api/v4/simple-orders")
    fun ordersV4(): List<OrderSimpleQueryDto> {
        return orderRepository.findOrderDtos()
    }

    @Data
    class SimpleOrderDto(
        val orderId: Long,
        val name: String,
        val orderDate: LocalDateTime,
        val orderStatus: OrderStatus,
        val address: Address
    ) {

    }
}