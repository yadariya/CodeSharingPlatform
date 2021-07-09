package platform.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.businessLayer.CodeJson;

import java.util.List;

@Repository
public interface CodeJsonRepository extends CrudRepository<CodeJson, Integer> {
    CodeJson findCodeJsonById(Integer id);

    List<CodeJson> findAll();

}
