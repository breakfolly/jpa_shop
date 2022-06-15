package jpabook.jpashop

import jpabook.jpashop.domain.Member
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class MemberRepositoryTest @Autowired constructor(
    private val memberRepository: MemberRepository
) {
    @Test
    @Transactional
    fun testMember() {
        // given
        val member = Member(username = "memberA")

        // when
        val findMember = memberRepository.save(member)?.let { savedId ->
            memberRepository.find(savedId)
        }

        // then
        assertEquals(findMember?.id, member.id)
        assertEquals(findMember?.username, member.username)
        assertEquals(findMember, member)
    }

}