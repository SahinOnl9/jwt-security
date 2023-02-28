package org.websec.jwtsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {

	private String searchTerm;
	private String category;
	private Integer rating;
	private Integer minReviewPoint;
	private Integer maxReviewPoint;

}
