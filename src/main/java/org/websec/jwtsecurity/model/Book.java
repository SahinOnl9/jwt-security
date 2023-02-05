package org.websec.jwtsecurity.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class Book extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = 2102794404597870366L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long isbn;

	private String title;

	private String author;

	private String createdBy;

	private String updatedBy;

}
