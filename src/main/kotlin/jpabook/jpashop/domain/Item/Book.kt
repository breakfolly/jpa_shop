package jpabook.jpashop.domain.Item

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    id: Long? = null,
    name: String,
    price: Int,
    stockQuantity: Int,
    var author: String? = null,
    var isbn: String? = null
) : Item(id = id, name = name, price = price, stockQuantity = stockQuantity) {

}