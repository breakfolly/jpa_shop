package jpabook.jpashop.domain.Item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie (
    id: Long,
    name: String,
    price: Int,
    stockQuantity: Int,
    var director: String,
    var actor: String
) : Item(id = id, name = name, price = price, stockQuantity = stockQuantity) {

}