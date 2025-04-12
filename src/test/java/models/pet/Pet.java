package models.pet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private long id;
    private String name;
    private String status;
    private List<String> photoUrls;
    private List<Tag> tags;
    private Category category;

    @Data
    public static class Tag {
        private int id;
        private String name;
    }

    @Data
    public static class Category {
        private int id;
        private String name;
    }
}


