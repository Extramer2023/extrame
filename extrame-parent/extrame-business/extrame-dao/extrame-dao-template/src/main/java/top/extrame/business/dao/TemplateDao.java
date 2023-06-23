package top.extrame.business.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.extrame.business.entity.Template;

/**
 * The type TemplateRepository.
 *
 * @author Jx-zou
 */
@Repository
public interface TemplateDao extends JpaRepository<Template, Long> {

    @Query("select t.content from business.tb_template t where t.type=#{type}")
    String findContentByType(Integer type);
}
