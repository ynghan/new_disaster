package disaster_renewer.disaster.service;

import disaster_renewer.disaster.domain.Member;
import disaster_renewer.disaster.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void join() throws Exception {
        Member member = new Member("dlapdlf316", "dudgks56!!");
        Long memberId = memberService.join(member);
        Optional<Member> findMember = memberRepository.findById(memberId);
        Assertions.assertThat(findMember.get()).isEqualTo(member);
    }

    @Test
    public void duplication() throws Exception {
        Member member1 = new Member("dlapdlf316", "dudgks56!!");
        Member member2 = new Member("dlapdlf316", "dudgks5669");

        memberService.join(member1);


        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }

}