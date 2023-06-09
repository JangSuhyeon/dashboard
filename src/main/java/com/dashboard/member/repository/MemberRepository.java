package com.dashboard.member.repository;

import com.dashboard.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("select m from Member m left join ProjectMember pm on m.memId = pm.memId where pm.pjtId = :pjtId")
    List<Member> findAllByPjtId(@Param("pjtId") String pjtId);
}
