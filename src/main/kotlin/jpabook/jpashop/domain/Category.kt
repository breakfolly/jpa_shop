package jpabook.jpashop.domain

import jpabook.jpashop.domain.Item.Item
import javax.persistence.*

@Entity
class Category(
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    var id: Long,

    var name: String,

    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    var items: MutableList<Item> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "parent_id")
    var parent: Category,

    @OneToMany(mappedBy = "parent")
    var child: MutableList<Category> = mutableListOf()
) {


}