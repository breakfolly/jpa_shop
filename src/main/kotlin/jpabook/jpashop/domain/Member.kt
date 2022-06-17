package jpabook.jpashop.domain

import javax.persistence.*

@Entity
class Member(
    @Id @GeneratedValue
    @Column(name = "member_id")
    var id : Long? = null,

    var name: String,

    @Embedded
    var address : Address,

    @OneToMany(mappedBy = "member")
    var orders : List<Order> = mutableListOf()
) {

}