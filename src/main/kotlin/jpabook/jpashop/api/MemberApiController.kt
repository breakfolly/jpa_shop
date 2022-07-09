package jpabook.jpashop.api

import jpabook.jpashop.domain.Member
import jpabook.jpashop.service.MemberService
import lombok.Data
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@RestController
class MemberApiController(
    @Autowired
    val memberService: MemberService
) {

    @PostMapping("/api/v1/members")
    fun saveMemberV1(@RequestBody @Valid member: Member): CreateMemberResponse {
        val id = memberService.join(member)
        return CreateMemberResponse(id = id)
    }

    @PostMapping("/api/v2/members")
    fun saveMemberV2(@RequestBody @Valid request: CreateMemberRequest): CreateMemberResponse {
        val member = Member(name = request.name)

        val id = memberService.join(member)
        return CreateMemberResponse(id = id)
    }

    @PutMapping("/api/v2/members/{id}")
    fun updateMemberV2(@PathVariable("id") id: Long,
                       @RequestBody @Valid request: UpdateMemberRequest): UpdateMemberResponse {
        memberService.update(id, request.name)
        val member = memberService.findOne(id)
        return UpdateMemberResponse(id, member.name)
    }

    @Data
    class CreateMemberRequest(
        @NotEmpty
        val name: String
    ) {

    }

    @Data
    class CreateMemberResponse(
        val id: Long
    ) {

    }

    @Data
    class UpdateMemberRequest(
        @NotEmpty
        val name: String
    ) {

    }

    @Data
    class UpdateMemberResponse(
        val id: Long,
        val name: String
    ) {

    }


}