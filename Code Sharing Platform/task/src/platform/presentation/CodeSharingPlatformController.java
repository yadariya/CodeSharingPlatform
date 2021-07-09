package platform.presentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.businessLayer.CodeJson;
import platform.businessLayer.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CodeSharingPlatformController {

    final String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    @Autowired
    CodeService codeService;


    @GetMapping("/code/{id}")
    public ModelAndView getCode(@PathVariable int id, HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        ModelAndView model = new ModelAndView();
        model.addObject("code", codeService.findCodeJsonById(id).getCode());
        model.addObject("date", codeService.findCodeJsonById(id).getDate());
        model.setViewName("getNcode");
        return model;
    }

    @GetMapping("/api/code/{id}")
    @ResponseBody
    public HashMap<String, String> getCodeAPI(@PathVariable int id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("code", codeService.findCodeJsonById(id).getCode());
        map.put("date", codeService.findCodeJsonById(id).getDate());
        return map;
    }

    @GetMapping("/code/new")
    public ModelAndView getNewCodeHtml(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return new ModelAndView("getNew");
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public List<CodeJson> getLatestApi() {
        List<CodeJson> latest = codeService.findAll();
        Collections.reverse(latest);
        return latest.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/code/new")
    public HashMap<String, String> postCode(@RequestBody CodeJson codeJson) {
        CodeJson codeCreate = codeService.save(new CodeJson(codeJson.getCode(), LocalDateTime.now().format(formatter)));
        HashMap<String, String> map = new HashMap<>();
        map.put("id", codeCreate.getId().toString());
        return map;
    }
}
