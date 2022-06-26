package jpabook.jpashop.domain

import jpabook.jpashop.domain.Item.Item
import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
class OrderItem (
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long? = null,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null,

    var orderPrice: Int,

    var count: Int
    ) {

    // 비즈니스 로직
    fun cancel() {
        item.addStock(count)
    }

    // 조회 로직
    // 주문상품 전체 가격 조회
    fun getTotalPrice(): Int {
        return orderPrice * count
    }

    // 생성 method
    companion object {
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            val orderItem = OrderItem(item = item, orderPrice = orderPrice, count = count)
            item.removeStock(count)
            return orderItem
        }
    }
}