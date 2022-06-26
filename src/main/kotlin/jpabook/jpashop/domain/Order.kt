package jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
@Table(name = "orders")
class Order protected constructor(
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    var id: Long? = null,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    var member: Member? = null,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: List<OrderItem> = mutableListOf(),

    @OneToOne(fetch = LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery,

    var orderDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus
) {

    // 연관 관계 method
    fun setMemberAndOrder(member: Member) {
        this.member = member
        member.orders.plus(this)
    }

    fun addOrderItem(orderItem: OrderItem) {
        orderItems.plus(orderItem)
        orderItem.order = this
    }

    fun setDeliveryAndOrder(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }

    // 생성 method
    companion object {
        fun createOrder(member: Member, delivery: Delivery, orderItems: List<OrderItem>) : Order {
            val order =  Order(member = member, delivery = delivery, orderDate = LocalDateTime.now(), status = OrderStatus.ORDER)
            orderItems.forEach { orderItem -> order.addOrderItem(orderItem) }
            return order
        }
    }

    // 비즈니스 로직
    // 주문 취소
    fun cancel() {
        if (delivery.status == DeliveryStatus.COMP) {
            throw IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.")
        }

        this.status = OrderStatus.CANCEL
        orderItems.forEach { orderItem -> orderItem.cancel() }
    }

    // 조회 로직
    // 전체 주문 가격 조회
    fun getTotalPrice(): Int {
        return orderItems.map { it.getTotalPrice() }.sum()
    }
}