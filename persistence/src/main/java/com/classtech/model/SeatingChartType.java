package com.classtech.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "seating_chart_type")
public class SeatingChartType extends BaseType {

	private Short row;

	private Short col;

	public Short getRow() {
		return row;
	}

	public void setRow(Short row) {
		this.row = row;
	}

	public Short getCol() {
		return col;
	}

	public void setCol(Short col) {
		this.col = col;
	}

}
