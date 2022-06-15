package jpabook.jpashop.domain.Item

import jpabook.jpashop.domain.Category
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
}