package lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Category;
import org.junit.jupiter.api.Tags;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PetModel {
    private Integer id;
    private String name, status;
    private List<String> photoUrls;
    @JsonProperty("tags")
    private List<Tags> tags;
    private Category category;
}

