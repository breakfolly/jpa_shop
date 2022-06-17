package jpabook.jpashop.domain

import jpabook.jpashop.domain.Item.Item
import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
class OrderItem (
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    var order: Order,

    var orderPrice: Int,

    var count: Int

        ) {
}