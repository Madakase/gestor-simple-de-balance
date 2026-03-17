package model;

public class Balance extends Transaccion{
	Subcategoria subcategoria;
	
	public Subcategoria getSubcategoria() {
		return subcategoria;
	}
	
	public void setSubCat(Subcategoria subCat) {
		this.subcategoria = subCat;
	}
}
