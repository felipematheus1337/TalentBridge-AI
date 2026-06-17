package talentbridge.ai.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "jobs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Job {

    @Id
    private String id;
    private String companyName;
    private String description;
    private BigDecimal baseSalary;


}
