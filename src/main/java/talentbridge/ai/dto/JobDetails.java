package talentbridge.ai.dto;

import talentbridge.ai.dto.enumerations.FittingStatus;

public record JobDetails(Integer score, FittingStatus status) {
}
