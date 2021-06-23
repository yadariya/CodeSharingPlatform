package platform;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CodeSharingPlatformController {

    final String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    int id = 0;

    static ArrayList<CodeJson> codeList = new ArrayList<>();

    @GetMapping("/code/{id}")
    public String getCode(@PathVariable int id) {
        return "<html>\n" +
                "<head>\n" +
                "    <title>Code</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<span id=\"load_date\">\n" +
                codeList.get(id - 1).getDate() +
                "</span>\n" +
                "<pre id=\"code_snippet\">\n" +
                codeList.get(id - 1).getCode() +
                "</pre>\n" +
                "</body>\n" +
                "</html>";
    }

    @GetMapping("/api/code/{id}")
    @ResponseBody
    public HashMap<String, String> getCodeAPI(@PathVariable int id) {
        HashMap<String, String> map = new HashMap<>();
        map.put("code", codeList.get(id - 1).getCode());
        map.put("date", codeList.get(id - 1).getDate());
        return map;
    }

    @GetMapping("/code/new")
    public String getCodeNew() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>Create</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<textarea id=\"code_snippet\"> Put new code here </textarea>\n" +
                "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>\n" +
                "</body>\n" +
                "</html>";
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public List<CodeJson> getLatestApi() {
        List<CodeJson> latest = new ArrayList<>(codeList);
        Collections.reverse(latest);
        return latest.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/code/new")
    public HashMap<String, String> postCode(@RequestBody CodeJson codeJson) {
        codeJson.setDate(LocalDateTime.now().format(formatter));
        codeList.add(codeJson);
        id += 1;
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        return map;
    }
}
