package disaster_renewer.disaster.domain;

import disaster_renewer.disaster.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void testMember() {
        Member member = new Member("dlapdlf316", "dudgks5669");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findByIdentification(savedMember.getIdentification());

        Assertions.assertThat(findMember).isEqualTo(member);
    }
}