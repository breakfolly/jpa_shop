package jpabook.jpashop.domain

import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
@Table(name = "orders")
class Order(
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
}