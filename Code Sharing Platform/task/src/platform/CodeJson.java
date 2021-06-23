package platform;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CodeJson {
    String code;
    String date;

    public CodeJson(String code, String data) {
        this.code = code;
        this.date = data;
    }
//final String DATE_FORMATTER = "yyyy/MM/dd HH:mm:ss";
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

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
}
