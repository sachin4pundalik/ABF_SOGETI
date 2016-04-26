package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fixed_cost_name database table.
 * 
 */
@Entity
@Table(name="fixed_cost_name")
@NamedQuery(name="FixedCostName.findAll", query="SELECT f FROM FixedCostName f")
public class FixedCostName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fixedcost_id", unique=true, nullable=false)
	private int fixedcostId;

	@Column(name="fixedcost_description", length=256)
	private String fixedcostDescription;

	@Column(name="fixedcost_name", length=256)
	private String fixedcostName;

	//bi-directional many-to-one association to Fixed
	@OneToMany(mappedBy="fixedCostName")
	private List<Fixed> fixeds;

	public FixedCostName() {
	}

	public int getFixedcostId() {
		return this.fixedcostId;
	}

	public void setFixedcostId(int fixedcostId) {
		this.fixedcostId = fixedcostId;
	}

	public String getFixedcostDescription() {
		return this.fixedcostDescription;
	}

	public void setFixedcostDescription(String fixedcostDescription) {
		this.fixedcostDescription = fixedcostDescription;
	}

	public String getFixedcostName() {
		return this.fixedcostName;
	}

	public void setFixedcostName(String fixedcostName) {
		this.fixedcostName = fixedcostName;
	}

	public List<Fixed> getFixeds() {
		return this.fixeds;
	}

	public void setFixeds(List<Fixed> fixeds) {
		this.fixeds = fixeds;
	}

	public Fixed addFixed(Fixed fixed) {
		getFixeds().add(fixed);
		fixed.setFixedCostName(this);

		return fixed;
	}

	public Fixed removeFixed(Fixed fixed) {
		getFixeds().remove(fixed);
		fixed.setFixedCostName(null);

		return fixed;
	}

}