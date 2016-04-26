package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the project_type database table.
 * 
 */
@Entity
@Table(name="project_type")
@NamedQuery(name="ProjectType.findAll", query="SELECT p FROM ProjectType p")
public class ProjectType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="project_id", unique=true, nullable=false)
	private int projectId;

	@Column(name="project_type", length=256)
	private String projectType;

	public ProjectType() {
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

}