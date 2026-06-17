package talentbridge.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobCandidateRequest {

    private String idCandidate;
    private String idJob;
}
