package jpabook.jpashop.service

import jpabook.jpashop.domain.Member
import jpabook.jpashop.repository.MemberRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Transactional
internal class MemberServiceTest @Autowired constructor (
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) {

    @Test
    fun 회원가입() {
        // given
        val member = Member(name = "kim")

        // when
        val savedId: Long = memberService.join(member)

        //then
        assertEquals(member, memberRepository.findOne(savedId))
    }

    @Test
    fun 중복_회원_예외() {
        // given
        val member1 = Member(name = "kim")
        val member2 = Member(name = "kim")

        // when
        memberService.join(member1)

        //then
        val illegalStateException = assertThrows(IllegalStateException::class.java) {
            memberService.join(member2)
        }

        assertEquals("이미 존재하는 회원입니다.", illegalStateException.message)
    }
}