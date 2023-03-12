package org.websec.jwtsecurity.model.manytomany;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.websec.jwtsecurity.model.AbstractTimestampEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student extends AbstractTimestampEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8305493221533113175L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long studentId;
	
	private String firstName;
	
	private String lastName;
	
	@OneToMany(mappedBy = "student")
    private Set<CourseRating> ratings;
}
