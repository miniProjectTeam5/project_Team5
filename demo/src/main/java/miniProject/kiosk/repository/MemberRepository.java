package miniProject.kiosk.repository;

import miniProject.kiosk.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findAllByPhoneNumber(String phoneNumber);
}
