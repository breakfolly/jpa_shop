package jpabook.jpashop.domain.Item

import jpabook.jpashop.domain.Category
import jpabook.jpashop.exception.NotEnoughStockException
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item(
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    var id: Long,

    var name: String,

    var price: Int,

    var stockQuantity: Int,

    @ManyToMany(mappedBy = "items")
    var categories: MutableList<Category> = mutableListOf()

) {

    // 비즈니스 로직
    fun addStock(quentity: Int) {
        stockQuantity += quentity
    }

    fun removeStock(quentity: Int) {
        val restStock = stockQuantity - quentity
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }
        stockQuantity -= quentity
    }
}