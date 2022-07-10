package jpabook.jpashop.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Member(
    @Id @GeneratedValue
    @Column(name = "member_id")
    var id : Long? = null,

    var name: String,

    @Embedded
    var address : Address? = null,

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    var orders : List<Order> = mutableListOf()
) {

}