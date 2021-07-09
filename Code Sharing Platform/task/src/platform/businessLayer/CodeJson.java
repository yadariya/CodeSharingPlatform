package platform.businessLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "code_json")
public class CodeJson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private String date;

    public CodeJson(String code, String data) {
        this.code = code;
        this.date = data;
    }

    public CodeJson() {
    }

    public String getCode() {
        return code;
    }

    public String getDate() {
        return date;
    }


    public Integer getId() {
        return id;
    }
}
