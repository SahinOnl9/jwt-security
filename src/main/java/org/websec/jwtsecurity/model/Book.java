package org.websec.jwtsecurity.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private Long isbn;

	@NotNull(message = "A Books title can not be null.")
	@Size(min = 1, max = 50, message = "Invalid size of title, please keep it between 1 to 50")
	@Column(nullable = false, unique = true)
	private String title;

	@Size(min = 5, max = 50, message = "Invalid size of authors name, please keep it between 1 to 50")
	@NotNull(message = "A Books author can not be null.")
	private String author;

	@JsonIgnore
	private String createdBy;

	@JsonIgnore
	private String updatedBy;

}
