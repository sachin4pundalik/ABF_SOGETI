package com.sogeti.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sogeti.db.models.Contract;
import com.sogeti.db.models.FixedCost;

public class FixedDT {
	private String description;

	private BigDecimal price;
	private int active;
	
	//bi-directional many-to-one association to Contract
	@ManyToOne
	@JoinColumn(name="contract_id")
	private Contract contract;

	//bi-directional many-to-one association to FixedCost
	@ManyToOne
	@JoinColumn(name="fixed_cost_id")
	private FixedCost fixedCost;
}
