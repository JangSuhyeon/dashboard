package com.dashboard.common.repository;

import com.dashboard.common.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<Code, String> {

    Code findCodeNameByGroupCodeAndCode(String groupCode, String code);

    List<Code> findAllByGroupCode(String groupCode);

    @Query(value = "SELECT generate_project_id(:str)", nativeQuery = true)
    String callGenerateProjectIdFunction(@Param("str") String str);
}
