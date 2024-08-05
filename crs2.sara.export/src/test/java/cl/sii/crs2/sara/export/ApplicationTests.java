package cl.sii.crs2.sara.export;

import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.crs.v2.CRSOECD;
import cl.sii.crs2.sara.export.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
@Slf4j
class ApplicationTests {
	@Autowired
	ExportService pruebaService;

	@Test
	public void crs() {
		pruebaService.updateRelationships();
		//pruebaService.getSaraSubmissions();
		//pruebaService.process();
	}

	@Test
	public void printOECDObject(){
		CRSOECD crsoecd= new CRSOECD();

		//VERSION 1
		//cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1.CRSOECD crsoecd= new cl.sii.crs2.sara.export.domain.crs.v1.oecd.ties.crs.v1.CRSOECD();

		printObjectStructure(crsoecd,0);
	}

	public  void printObjectStructure(Object obj, int indentLevel) {
		if (obj == null) {
			printIndent(indentLevel);
			System.out.println("null");
			return;
		}

		Class<?> objClass = obj.getClass();
		printIndent(indentLevel);
		System.out.println("Class: " + objClass.getName());

		Field[] fields = objClass.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			printIndent(indentLevel);
			System.out.print(field.getName() + " (" + field.getType().getSimpleName() + "): ");
			try {
				Object fieldValue = field.get(obj);
				if (fieldValue == null) {
					System.out.println("null");
				} else if (isPrimitiveOrWrapper(fieldValue.getClass()) || fieldValue instanceof String) {
					System.out.println(fieldValue);
				} else if (fieldValue instanceof Collection<?>) {
					System.out.println("Collection of " + field.getType().getSimpleName());
					for (Object item : (Collection<?>) fieldValue) {
						printObjectStructure(item, indentLevel + 2);
					}
				} else {
					System.out.println();
					printObjectStructure(fieldValue, indentLevel + 2);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private  boolean isPrimitiveOrWrapper(Class<?> type) {
		return type.isPrimitive() ||
				type == Boolean.class || type == Integer.class || type == Character.class ||
				type == Byte.class || type == Short.class || type == Double.class ||
				type == Long.class || type == Float.class;
	}

	private  void printIndent(int indentLevel) {
		for (int i = 0; i < indentLevel; i++) {
			System.out.print("  ");
		}
	}
}
