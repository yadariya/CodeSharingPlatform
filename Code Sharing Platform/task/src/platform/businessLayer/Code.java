package platform.businessLayer;

public class Code {
    private String code;
    private String date;
    private Integer time;
    private Integer views;

    public Code(String code, String date, Integer time, Integer views) {
        this.code = code;
        this.date = date;
        this.time = time;
        this.views = views;
    }

    public Code() {
    }

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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }
}
