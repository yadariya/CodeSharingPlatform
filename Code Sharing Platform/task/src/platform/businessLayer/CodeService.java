package platform.businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.persistence.CodeJsonRepository;

import java.util.List;

@Service
public class CodeService {
    private final CodeJsonRepository codeJsonRepository;

    @Autowired
    public CodeService(CodeJsonRepository codeJsonRepository) {
        this.codeJsonRepository = codeJsonRepository;
    }

    public CodeJson findCodeJsonById(String id) {
        return codeJsonRepository.findCodeJsonById(id);
    }

    public List<CodeJson> findAll() {
        return codeJsonRepository.findAll();
    }

    public CodeJson save(CodeJson toSave) {
        return codeJsonRepository.save(toSave);
    }

    public void deleteById(String id) {
        codeJsonRepository.deleteById(id);
    }
}
