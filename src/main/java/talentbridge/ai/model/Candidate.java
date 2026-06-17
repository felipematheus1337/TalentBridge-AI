package talentbridge.ai.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import talentbridge.ai.model.vo.Address;

import java.util.List;

@Document(collection = "candidates")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Candidate {

    @Id
    private String id;
    private String name;
    private int age;
    private Address address;
    private List<String> skills;
    private String resume;

}
