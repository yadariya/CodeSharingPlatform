package platform;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@RestController
public class CodeSharingPlatformController {

    final String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    String given_code = "main()";
    String date_t = "2020/01/01 12:00:03";

    @GetMapping("/code")
    public String getCode() {
        return "<html>\n" +
                "<head>\n" +
                "    <title>Code</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<span id=\"load_date\">\n" +
                date_t +
                "</span>\n" +
                "<pre id=\"code_snippet\">\n" +
                given_code +
                "</pre>\n" +
                "</body>\n" +
                "</html>";
    }

    @GetMapping("/api/code")
    @ResponseBody
    public HashMap<String, String> getCodeAPI() {
        HashMap<String, String> map = new HashMap<>();
        map.put("code", given_code);
        map.put("date", date_t);
        return map;
    }

    @GetMapping("/code/new")
    public String getCodeNew() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns:th=\"https://www.thymeleaf.org/\">\n" +
                "<head>\n" +
                "    <title>Create</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<textarea id=\"code_snippet\"> ... </textarea>\n" +
                "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>\n" +
                "</body>\n" +
                "</html>";
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<EmptyJsonBody> postCode(@RequestBody CodeJson codeJson) {
        given_code = codeJson.getCode();
        date_t = LocalDateTime.now().format(formatter);
        ResponseEntity.BodyBuilder responseBuilder = ResponseEntity.ok();
        return responseBuilder.body(new EmptyJsonBody());
    }
}