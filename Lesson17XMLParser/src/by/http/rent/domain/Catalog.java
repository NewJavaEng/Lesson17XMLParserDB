package by.http.rent.domain;

import java.util.List;

public class Catalog {
	
	private List <Equipment> equipments;

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	@Override
	public String toString() {
		return "Catalog [equipments=" + equipments + "]";
	}
	


}
