package platform.businessLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "code_json")
public class CodeJson {

    @Id
    @JsonIgnore
    private String id;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private Integer time;

    @Column(name = "views")
    private Integer views;

    private boolean isTimeRestricted;

    private boolean isViewRestricted;

    private boolean Expired;

    public boolean isExpired() {
        LocalDateTime ldt = LocalDateTime.of(getYear(), getMonth(), getDay(), getHour(), getMinute(), getSecond());
        if (isViewRestricted && views <= 0 ||
                isTimeRestricted && time - Math.abs((int) Duration.between(ldt, LocalDateTime.now()).toSeconds()) <= 0) {
            Expired = true;
        } else {
            Expired = false;
        }
        return Expired;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public CodeJson(String id, String code, String data, Integer time, Integer views, boolean isTimeRestricted,
                    boolean isViewRestricted) {
        this.id = id;
        this.code = code;
        this.date = data;
        this.time = time;
        this.views = views;
        this.isTimeRestricted = isTimeRestricted;
        this.isViewRestricted = isViewRestricted;
    }

    public CodeJson() {
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public boolean isTimeRestricted() {
        return isTimeRestricted;
    }

    public boolean isViewRestricted() {
        return isViewRestricted;
    }

    public Integer getYear() {
        return Integer.parseInt(date.substring(0, 4));
    }

    public Integer getMonth() {
        return Integer.parseInt(date.substring(5, 7));
    }

    public Integer getDay() {
        return Integer.parseInt(date.substring(8, 10));
    }

    public Integer getHour() {
        return Integer.parseInt(date.substring(11, 13));
    }

    public Integer getMinute() {
        return Integer.parseInt(date.substring(14, 16));
    }

    public Integer getSecond() {
        return Integer.parseInt(date.substring(17, 19));
    }
}
