package disaster_renewer.disaster.service;

import disaster_renewer.disaster.domain.Member;
import disaster_renewer.disaster.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validDuplicationMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 중복 검사
     */
    public void validDuplicationMember(Member member) {
        Member byIdentification = memberRepository.findByIdentification(member.getIdentification());
        if(!(byIdentification == null)) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원인지 확인 검사
     */
    public void validMember(Member member) {
        Member byIdentification = memberRepository.findByIdentification(member.getIdentification());
        if(byIdentification == null) {
            throw new IllegalStateException("존재하지 않는 회원입니다.");
        }
    }

    /**
     * 친구 추가
     */
    public void addFriend(Member member) {
        validMember(member);

    }

}
