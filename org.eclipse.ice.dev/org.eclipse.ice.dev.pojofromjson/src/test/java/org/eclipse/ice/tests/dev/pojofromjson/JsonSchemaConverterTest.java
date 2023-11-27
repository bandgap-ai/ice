package org.eclipse.ice.tests.dev.pojofromjson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.ice.dev.pojofromjson.InvalidFileNameException;
import org.eclipse.ice.dev.pojofromjson.JsonSchemaConverter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JsonSchemaConverterTest {
	
	public static final String JSON_FILE = "TestJson.json";
	
	public static final String JSON_OUTPUT_PREFIX = "TestJson";
	
	public static final String JSON_RESULT = "TestJson_result.json";
	
	public static final String GENERATED_INTERFACE = "TestJsonFields.java";
	
	public static final String GENERATED_IMPLEMENTATION = "TestJsonFieldsImplementation.java";
	
	public static final String GENERATED_PROP_INTERFACE = "Properties.java";
	
	public static final String GENERATED_PROP_IMPLEMENTATION = "PropertiesImplementation.java";
	
	public static String jsonResource;
	
	public static Path jsonResourceDir;
	
	@BeforeAll
	public static void setup() {
		jsonResource = JsonSchemaConverterTest.class.getClassLoader().getResource(JSON_FILE).getPath();
		jsonResourceDir = Path.of(jsonResource).getParent();
	}
	
	@Test
	void schemaConversionTest() throws FileNotFoundException, IOException {
		
		// Setup test paths
		Path jsonResultPath = jsonResourceDir.resolve(JSON_RESULT);
		Path genInterfacePath = jsonResourceDir.resolve(GENERATED_INTERFACE);
		Path genImplPath = jsonResourceDir.resolve(GENERATED_IMPLEMENTATION);
		Path genPropInterfacePath = jsonResourceDir.resolve(GENERATED_PROP_INTERFACE);
		Path genPropImplPath = jsonResourceDir.resolve(GENERATED_PROP_IMPLEMENTATION);
		
		// Generate the class files
		InputStream jsonFile = new FileInputStream(jsonResource);
		JsonSchemaConverter.setWriteFile(true);
		JsonSchemaConverter.handleInputJson(jsonFile, jsonResourceDir, JSON_OUTPUT_PREFIX);
		
		// JSON output test
		assertTrue(Files.exists(jsonResultPath));
		// Generated interface test
		assertTrue(Files.exists(genInterfacePath));
		// Generated implementation file test
		assertTrue(Files.exists(genImplPath));
		// Generated properties properties test
		assertTrue(Files.exists(genPropInterfacePath));
		// Generated properties implementation test
		assertTrue(Files.exists(jsonResourceDir.resolve(genPropImplPath)));
		
		// Clean up
		Files.delete(jsonResultPath);
		Files.delete(genInterfacePath);
		Files.delete(genImplPath);
		Files.delete(genPropInterfacePath);
		Files.delete(genPropImplPath);
	}
	
	@Test
	void badFileNameTest() throws InvalidFileNameException {
		// Invalid character
		assertThrows(InvalidFileNameException.class, () -> {
			JsonSchemaConverter.formatFileName("Test#.Json.json");
		});
		// ?
		assertThrows(InvalidFileNameException.class, () -> {
			JsonSchemaConverter.formatFileName("...............");
		});
		// Invalid format and character
		assertThrows(InvalidFileNameException.class, () -> {
			JsonSchemaConverter.formatFileName(",IsThisValid.json,");
		});
		// Empty string
		assertThrows(InvalidFileNameException.class, () -> {
			JsonSchemaConverter.formatFileName("");
		});
		// Something stupid - removes periods and file json.
		assertEquals(JsonSchemaConverter.formatFileName("json.json.json.json"), "jsonjsonjson");
		// Extract "valid" from "valid.json"
		assertEquals(JsonSchemaConverter.formatFileName("valid.json"), "valid");
		// Same thing but with "a?"
		assertEquals(JsonSchemaConverter.formatFileName("a.json"), "a");
		// Make sure something without ".json" is left alone
		assertEquals(JsonSchemaConverter.formatFileName("a"), "a");
	}
}
