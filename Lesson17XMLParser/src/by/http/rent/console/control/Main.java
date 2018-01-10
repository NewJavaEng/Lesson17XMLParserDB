package by.http.rent.console.control;

import by.http.rent.dao.CatalogData;
import by.http.rent.dao.db.CatalogDataMySQLImpl;
import by.http.rent.dao.parser.dom.CatalogDataDomImpl;
import by.http.rent.dao.parser.sax.CatalogDataSaxImpl;
import by.http.rent.domain.Catalog;

public class Main {

	public static void main(String[] args) {


//		CatalogData dao = new CatalogDataSaxImpl();
//		CatalogData dao = new CatalogDataDomImpl();
		CatalogData dao = new CatalogDataMySQLImpl();
		Catalog catalog = dao.readCatalog();
		
		System.out.println(catalog);

	}

}
