package platform;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeJson {
    String code;
    String date;
    final String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CodeJson(String snippetCode) {
        this.code = snippetCode;
        this.date = LocalDateTime.now().format(formatter);
    }

    public CodeJson() {
        this.code = "public static void main(String[] args) {\\n    SpringApplication.run(CodeSharingPlatform.class, args);\\n}";
        this.date = LocalDateTime.now().format(formatter);
    }
}
