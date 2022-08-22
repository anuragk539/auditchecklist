package com.project.auditChecklist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="AuditQuestions")
public class Questions {
	@Id
	@GeneratedValue
	@Column(name="questionId")
	private Integer questionId;

	@Column(name="auditType")
	private String auditType;

	@Column(name="questions")
	private String question;

	@Column(name="response")
	private String response;
}
