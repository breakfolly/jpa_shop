package jpabook.jpashop.controller

import javax.validation.constraints.NotEmpty

class MemberForm (
    @field:NotEmpty(message = "회원 이름은 필수 입니다.")
    val name: String,
    val city: String,
    val street: String,
    val zipcode: String
        ) {

}