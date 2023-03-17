package org.websec.jwtsecurity.model.manytomany;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;
import org.websec.jwtsecurity.model.AbstractTimestampEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Course extends AbstractTimestampEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3850538999067498823L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long courseId;

//	@NaturalId
	private String courseName;

	@OneToMany(mappedBy = "course")
	private Set<CourseRating> ratings;

}
