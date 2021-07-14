package platform.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.businessLayer.Code;
import platform.businessLayer.CodeJson;
import platform.businessLayer.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
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
    public Object getCode(@PathVariable String id) {
        ModelAndView model = new ModelAndView();
        CodeJson cj;
        try {
            cj = codeService.findCodeJsonById(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        LocalDateTime ldt = LocalDateTime.of(cj.getYear(), cj.getMonth(), cj.getDay(), cj.getHour(),
                cj.getMinute(), cj.getSecond());
        if (cj.isExpired()) {
            codeService.deleteById(id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            model.addObject("code", cj);
            model.addObject("timeRestriction", cj.isTimeRestricted());
            model.addObject("viewRestriction", cj.isViewRestricted());
            model.addObject("time", cj.getTime() - (int) Duration.between(ldt, LocalDateTime.now()).toSeconds());
            model.addObject("view", cj.getViews());
            model.addObject("service", codeService);
            model.setViewName("getNcode");
            return model;

        }
    }

    @GetMapping("/api/code/{id}")
    @ResponseBody
    public ResponseEntity getCodeAPI(@PathVariable String id) {
        CodeJson cj;
        try {
            cj = codeService.findCodeJsonById(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        LocalDateTime ldt = LocalDateTime.of(cj.getYear(), cj.getMonth(), cj.getDay(), cj.getHour(),
                cj.getMinute(), cj.getSecond());
        if (cj.isExpired()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            if (cj.isViewRestricted()) {
                cj.setViews(cj.getViews() - 1);
                codeService.save(cj);
            }
            if (cj.isTimeRestricted()) {
                cj.setTime(cj.getTime() - (int) Duration.between(ldt, LocalDateTime.now()).toSeconds());
            }
            Code code = new Code(cj.getCode(), cj.getDate(), cj.getTime(), cj.getViews());
            return new ResponseEntity<>(code, HttpStatus.OK);
        }
    }

    @GetMapping("/code/new")
    public ModelAndView getNewCodeHtml(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return new ModelAndView("getNew");
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public List<Code> getLatestApi() {
        List<CodeJson> latest = codeService.findAll();
        latest.removeIf(code -> code.isTimeRestricted() || code.isViewRestricted());
        ArrayList<Code> list = new ArrayList<>();
        for (CodeJson jsonCode : latest) {
            Code list_code = new Code();
            list_code.setCode(jsonCode.getCode());
            list_code.setDate(jsonCode.getDate());
            list_code.setTime(jsonCode.getTime());
            list_code.setViews(jsonCode.getViews());
            list.add(list_code);
        }
        Collections.reverse(list);
        return list.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/code/new")
    public HashMap<String, String> postCode(@RequestBody CodeJson codeJson) {
        UUID uuid = UUID.randomUUID();
        boolean isTimeRestricted;
        boolean isViewRestricted;
        isTimeRestricted = codeJson.getTime() > 0;
        isViewRestricted = codeJson.getViews() > 0;
        CodeJson codeCreate = codeService.save(new CodeJson(uuid.toString(), codeJson.getCode(), LocalDateTime.now().format(formatter),
                codeJson.getTime(), codeJson.getViews(), isTimeRestricted, isViewRestricted));
        HashMap<String, String> map = new HashMap<>();
        map.put("id", codeCreate.getId());
        return map;
    }

    @GetMapping("/code/latest")
    public ModelAndView getLatestWeb() {
        ModelAndView model = new ModelAndView();
        List<CodeJson> latest = codeService.findAll();
        latest.removeIf(code -> code.isTimeRestricted() || code.isViewRestricted());
        ArrayList<Code> list = new ArrayList<>();
        for (CodeJson jsonCode : latest) {
            Code list_code = new Code();
            list_code.setCode(jsonCode.getCode());
            list_code.setDate(jsonCode.getDate());
            list_code.setTime(jsonCode.getTime());
            list_code.setViews(jsonCode.getViews());
            list.add(list_code);
        }
        Collections.reverse(list);
        model.addObject("code_", list.stream()
                .limit(10)
                .collect(Collectors.toList()));
        model.setViewName("getLatest");
        return model;
    }
}
