package jpabook.jpashop.domain

import javax.persistence.*
import javax.persistence.FetchType.*

@Entity
class Delivery(
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    var id: Long,

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    var order: Order,

    @Embedded
    var address: Address,

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus
) {
}