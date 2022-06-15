package jpabook.jpashop.domain.Item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(
    id: Long,
    name: String,
    price: Int,
    stockQuantity: Int,
    var artist: String,
    var etc: String
) : Item(id = id, name = name, price = price, stockQuantity = stockQuantity) {

}