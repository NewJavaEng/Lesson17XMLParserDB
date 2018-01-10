package by.http.rent.dao.parser.sax;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.http.rent.dao.CatalogData;
import by.http.rent.domain.Catalog;
import by.http.rent.domain.Equipment;

public class CatalogDataSaxImpl implements CatalogData {

	private static final String XML_FILE_PATH = "resources/rent_station.xml";

	@Override
	public Catalog readCatalog() {

		Catalog catalog = new Catalog();
		try {

//			interface org.xml.sax.XMLReader
			CatalogDataHandler handler = new CatalogDataHandler();
			XMLReader reader = XMLReaderFactory.createXMLReader();
//			org.xml.sax.ContentHandler–обработчик событий документа
//			Класс, реализующий этот интерфейс можно зарегистрировать в анализаторе с помощью методов setContentHandler();
			reader.setContentHandler(handler);
//			to analyse doc use parse() method from interface org.xml.sax.XMLReader
			reader.parse(XML_FILE_PATH);

			List<Equipment> equipments = handler.getEquipments();

			catalog.setEquipments(equipments);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return catalog;
	}

}
