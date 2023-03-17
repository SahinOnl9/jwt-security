package org.websec.jwtsecurity.model.manytomany;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.websec.jwtsecurity.model.AbstractTimestampEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//@Data
//@EqualsAndHashCode(callSuper = false)
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "COURSE_VS_STUDENT")
public class CourseStudentMappeer extends AbstractTimestampEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8815399052960313698L;

	@Id
	@GeneratedValue
	private Long id;
}
