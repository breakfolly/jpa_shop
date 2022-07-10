package jpabook.jpashop.repository

import jpabook.jpashop.domain.Address
import jpabook.jpashop.domain.OrderStatus
import lombok.Data
import java.time.LocalDateTime


@Data
class OrderSimpleQueryDto(
    var orderId: Long,
    var name: String,
    var orderDate: LocalDateTime,
    var orderStatus: OrderStatus,
    var address: Address?
) {

}