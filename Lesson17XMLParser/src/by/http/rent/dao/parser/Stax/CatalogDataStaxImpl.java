package by.http.rent.dao.parser.Stax;

import static by.http.rent.dao.parser.DataTypeTransformUtil.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.http.rent.dao.CatalogData;
import by.http.rent.dao.parser.CatalogTagName;
import by.http.rent.domain.Catalog;
import by.http.rent.domain.Equipment;

public class CatalogDataStaxImpl implements CatalogData {

	private static final String XML_FILE_PATH = "resources/rent_station.xml";

	private static final char UNDERSCORE = '_';
	private static final char DASH = '-';

	private static final String ID = "id";

	@Override
	public Catalog readCatalog() {
		Catalog catalog = new Catalog();

		XMLInputFactory xmlIF = XMLInputFactory.newInstance();

		InputStream stream;
		try {
			stream = new FileInputStream(XML_FILE_PATH);
			XMLStreamReader reader = xmlIF.createXMLStreamReader(stream);

			List<Equipment> equipment = processReader(reader);
			catalog.setEquipments(equipment);
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

		return catalog;
	}

	private List<Equipment> processReader(XMLStreamReader reader) {
		List<Equipment> equipments = new ArrayList<>();
		CatalogTagName tag = null;
		Equipment equipment = null;

		try {
			while (reader.hasNext()) {
				int type = reader.next();
				switch (type) {
				case XMLStreamConstants.START_ELEMENT:
					tag = getTag(reader.getLocalName());
					switch (tag) {
					case EQUIPMENT:
						equipment = new Equipment();
						String id = reader.getAttributeValue(null, ID);
						equipment.setId(convertId(id));
						break;
					}
				case XMLStreamConstants.CHARACTERS:
					break;
				case XMLStreamConstants.END_ELEMENT:
					break;
				}
			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return equipments;
	}

	private CatalogTagName getTag(String localName) {
		String tag = localName.toUpperCase().replace(DASH, UNDERSCORE);
		CatalogTagName tagElement = CatalogTagName.valueOf(tag);
		return tagElement;
	}
}
