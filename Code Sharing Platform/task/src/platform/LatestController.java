package platform;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static platform.CodeSharingPlatformController.codeList;

@Controller
public class LatestController {
    @GetMapping("/code/latest")
    public String getLatestWeb(Model model) {
        List<CodeJson> latest = new ArrayList<>(codeList);
        Collections.reverse(latest);
        model.addAttribute("code_", latest.stream()
                .limit(10)
                .collect(Collectors.toList()));
        return "getNth";
    }
}
