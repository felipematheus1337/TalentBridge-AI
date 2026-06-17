package talentbridge.ai.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "job_candidates")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobCandidate {

    @Id
    private String id;

    private String idCandidate;

    private String idJob;
}
