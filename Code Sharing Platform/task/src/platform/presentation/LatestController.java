package platform.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import platform.businessLayer.CodeJson;
import platform.businessLayer.CodeService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class LatestController {
    @Autowired
    CodeService codeService;

    @GetMapping("/code/latest")
    public String getLatestWeb(Model model) {
        List<CodeJson> latest = codeService.findAll();
        Collections.reverse(latest);
        model.addAttribute("code_", latest.stream()
                .limit(10)
                .collect(Collectors.toList()));
        return "getLatest";
    }
}
