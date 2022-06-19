package jpabook.jpashop.repository

import jpabook.jpashop.domain.Item.Item
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class ItemRepository {

    @PersistenceContext
    private lateinit var em : EntityManager

    fun save(item: Item) {
        item.id?.run { em.persist(item) } ?: run { em.merge(item) }
    }

    fun findOne(id: Long): Item ?{
        return em.find(Item::class.java, id)
    }

    fun findAll(): List<Item> {
        return em.createQuery("select i from Item i", Item::class.java)
            .resultList
    }
}