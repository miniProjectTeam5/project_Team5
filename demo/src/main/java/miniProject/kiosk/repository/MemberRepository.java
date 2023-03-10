package miniProject.kiosk.repository;

import miniProject.kiosk.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByPhoneNumber(String phoneNumber);


}
