package com.journaldev.ivanium.model;

import javax.persistence.Entity;

public class FactIngredientDTO {
	private String PK_Frac_Ingredients;
	private String FK_Well;
	private String FK_Supplier;
	private String Purpose;
	private String Trade_Name;
	private String Ingredient_Name;
	private String CAS_Number;
	private String Percentage_Of_Total_Fluid;
	private String Ingredient_Mass;

	public String getPK_Frac_Ingredients() {
		return PK_Frac_Ingredients;
	}

	public void setPK_Frac_Ingredients(String pK_Frac_Ingredients) {
		PK_Frac_Ingredients = pK_Frac_Ingredients;
	}

	public String getFK_Well() {
		return FK_Well;
	}

	public void setFK_Well(String fK_Well) {
		FK_Well = fK_Well;
	}

	public String getFK_Supplier() {
		return FK_Supplier;
	}

	public void setFK_Supplier(String fK_Supplier) {
		FK_Supplier = fK_Supplier;
	}

	public String getPurpose() {
		return Purpose;
	}

	public void setPurpose(String purpose) {
		Purpose = purpose;
	}

	public String getTrade_Name() {
		return Trade_Name;
	}

	public void setTrade_Name(String trade_Name) {
		Trade_Name = trade_Name;
	}

	public String getIngredient_Name() {
		return Ingredient_Name;
	}

	public void setIngredient_Name(String ingredient_Name) {
		Ingredient_Name = ingredient_Name;
	}

	public String getCAS_Number() {
		return CAS_Number;
	}

	public void setCAS_Number(String cAS_Number) {
		CAS_Number = cAS_Number;
	}

	public String getPercentage_Of_Total_Fluid() {
		return Percentage_Of_Total_Fluid;
	}

	public void setPercentage_Of_Total_Fluid(String percentage_Of_Total_Fluid) {
		Percentage_Of_Total_Fluid = percentage_Of_Total_Fluid;
	}

	public String getIngredient_Mass() {
		return Ingredient_Mass;
	}

	public void setIngredient_Mass(String ingredient_Mass) {
		Ingredient_Mass = ingredient_Mass;
	}

}
