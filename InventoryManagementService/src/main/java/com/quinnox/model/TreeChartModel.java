package com.quinnox.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeChartModel {

	private String name;
	private List<TreeChartModel> children;

	public TreeChartModel(String name) {
		super();
		this.name = name;
	}

}
