package de.tum.cs.i1.pse;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class StructuralTest {
	private String clazzName;
	private JSONObject expectedClazz;

	// Each parameter should be placed as an argument here
	// Every time runner triggers, it will pass the arguments
	// from parameters we defined in primeNumbers() method
	public StructuralTest(String clazzName, JSONObject expectedClazz) {
		this.clazzName = clazzName;
		this.expectedClazz = expectedClazz;
	}

	@Parameterized.Parameters(name = "Checking class {0}")
	public static Collection<Object[]> clazzes() throws IOException {
		List<Object[]> testData = new ArrayList<Object[]>();
		String jsonString = toString(StructuralTest.class
				.getResource("test.json"));
		if (jsonString != null) {
			JSONArray classesArray = new JSONArray(jsonString);
			for (int i = 0; i < classesArray.length(); i++) {
				JSONObject jsonObject = (JSONObject) classesArray.get(i);
				String clazzName = jsonObject.getString("class");
				testData.add(new Object[] { clazzName, jsonObject });
			}
		}
		return testData;
	}

	@Test
	public void testHierarchy() throws ClassNotFoundException {
		Class<?> clazz = Class.forName(StructuralTest.class.getPackage()
				.getName() + "." + clazzName);
		try {
			String superClazz = expectedClazz.getString("superclass");
			assertEquals("Does not extend class " + superClazz + ".",
					superClazz, clazz.getSuperclass().getSimpleName());
		} catch (JSONException e) {
			// The parameter is optional, so this is ok just don't do
			// anything
		}

		try {
			JSONArray expectedInterfaces = expectedClazz
					.getJSONArray("interfaces");
			Class<?>[] actualInterfaces = clazz.getInterfaces();

			for (int i = 0; i < expectedInterfaces.length(); i++) {
				String expectedInterface = (String) expectedInterfaces.get(i);
				boolean implementsInterface = false;
				for (Class<?> actualInterface : actualInterfaces) {
					if (expectedInterface.equals(actualInterface
							.getSimpleName())) {
						implementsInterface = true;
					}
					assertEquals("Does not implement interface "
							+ expectedInterface + ".", true,
							implementsInterface);
				}
				if (actualInterfaces.length == 0) {
					assertEquals("Does not implement interface "
							+ expectedInterface + ".", true,
							implementsInterface);
				}
			}
		} catch (JSONException e) {
			// The parameter is optional, so this is ok just don't do
			// anything
		}
	}

	@Test
	public void testConstructors() throws ClassNotFoundException {
		Class<?> clazz = Class.forName(StructuralTest.class.getPackage()
				.getName() + "." + clazzName);
		try {
			JSONArray expectedConstructors = expectedClazz
					.getJSONArray("constructors");
			checkConstructors(clazz, expectedConstructors);
		} catch (JSONException e) {
			// The parameter is optional, so this is ok just don't do
			// anything
		}
	}

	@Test
	public void testFields() throws ClassNotFoundException,
			NoSuchFieldException {
		Class<?> clazz = Class.forName(StructuralTest.class.getPackage()
				.getName() + "." + clazzName);
		try {
			JSONObject fields = expectedClazz.getJSONObject("fields");
			checkFields(clazz, fields);
		} catch (JSONException e) {
			// The parameter is optional, so this is ok just don't do
			// anything
		}
	}

	@Test
	public void testMethods() throws ClassNotFoundException,
			NoSuchMethodException {
		Class<?> clazz = Class.forName(StructuralTest.class.getPackage()
				.getName() + "." + clazzName);
		try {
			JSONObject jsonMethods = expectedClazz.getJSONObject("methods");
			checkMethods(clazz, jsonMethods);
		} catch (JSONException e) {
			// The parameter is optional, so this is ok just don't do
			// anything
		}
	}

	private void checkConstructors(Class<?> clazz,
			JSONArray expectedConstructors) {
		Constructor<?>[] constructors = clazz.getConstructors();
		for (int j = 0; j < expectedConstructors.length(); j++) {
			JSONObject expectedConstructor = (JSONObject) expectedConstructors
					.get(j);
			JSONArray expectedModifiers = expectedConstructor
					.getJSONArray("modifiers");
			JSONArray expectedParameters = expectedConstructor
					.getJSONArray("parameters");

			boolean constructorExists = false;
			for (Constructor<?> constructor : constructors) {
				boolean isCorrectConstructor = true;
				checkModifiers(constructor.getModifiers(), expectedModifiers);

				Class<?>[] parameterTypes = constructor.getParameterTypes();
				for (int k = 0; k < expectedParameters.length(); k++) {
					if (!expectedParameters.get(k).equals(
							parameterTypes[k].getSimpleName())) {
						isCorrectConstructor = false;
					}
				}
				if (isCorrectConstructor) {
					constructorExists = true;
					break;
				}
			}
			if (!constructorExists) {
				assertEquals("Expected a constructor with the parameters "
						+ expectedParameters + ".", true, constructorExists);
			}
		}
	}

	private static String toString(URL url) throws IOException {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			char[] buffer = new char[8192];
			int len;
			StringBuilder result = new StringBuilder();
			while ((len = in.read(buffer, 0, buffer.length)) != -1) {
				result.append(buffer, 0, len);
			}
			return result.toString();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	private void checkFields(Class<?> clazz, JSONObject expectedFields)
			throws NoSuchFieldException {
		Iterator<?> expectedFieldsIterator = expectedFields.keys();

		while (expectedFieldsIterator.hasNext()) {
			String expectedFieldName = (String) expectedFieldsIterator.next();
			Field actualField = clazz.getDeclaredField(expectedFieldName);

			String expectedFieldType = expectedFields
					.getString(expectedFieldName);
			checkType(expectedFieldType, actualField);
		}
	}

	private void checkType(String expectedType, Field actualField) {
		if (expectedType.contains("<")) {
			String expectedMainType = expectedType.split("<")[0];
			String expectedGenericType = expectedType.split("<")[1].replace(
					">", "");

			assertEquals(expectedMainType, actualField.getType()
					.getSimpleName());

			Type genericType = actualField.getGenericType();
			if (genericType instanceof ParameterizedType) {
				Type actualType = ((ParameterizedType) actualField
						.getGenericType()).getActualTypeArguments()[0];
				String actualTypeString = actualType.toString().substring(
						actualType.toString().lastIndexOf(".") + 1);
				assertEquals(expectedGenericType, actualTypeString);
			}
		}
	}

	//  Overloaded methods not supported: Currently this method just picks
	// the first mehtod, that has the proper name. This might lead to a test,
	// that does not find a method even though it is present.
	private void checkMethods(Class<?> clazz, JSONObject jsonMethods)
			throws NoSuchMethodException {
		Iterator<?> methodsIterator = jsonMethods.keys();
		while (methodsIterator.hasNext()) {
			String methodName = (String) methodsIterator.next();
			JSONObject expectedMethod = jsonMethods.getJSONObject(methodName);

			Method actualMethod = null;
			Method[] methods = clazz.getMethods();
			for (Method tempMethodData : methods) {
				if (tempMethodData.getName().equals(methodName)) {
					actualMethod = tempMethodData;
				}
			}

			if (actualMethod == null) {
				throw new NoSuchMethodException(methodName);
			}

			try {
				JSONArray expectedModifiers = expectedMethod
						.getJSONArray("modifiers");
				checkModifiers(actualMethod.getModifiers(), expectedModifiers);
			} catch (JSONException e) {
				// The parameter is optional, so this is ok just don't do
				// anything
			}

			JSONArray expectedParameters = (JSONArray) expectedMethod
					.get("parameters");

			Class<?>[] parameterTypes = actualMethod.getParameterTypes();
			for (int j = 0; j < parameterTypes.length; j++) {
				String parameterType = expectedParameters.getString(j);
				assertEquals("Wrong parameters in method " + methodName + ".",
						parameterType, parameterTypes[j].getSimpleName());
			}
			String returnType = expectedMethod.getString("returnType");
			assertEquals("Wrong return type in method " + methodName + ".",
					returnType, actualMethod.getReturnType().getSimpleName());
		}
	}

	private void checkModifiers(int actualModifiers, JSONArray expectedModifiers) {
		String actualModifiersString = Modifier.toString(actualModifiers);
		for (int i = 0; i < expectedModifiers.length(); i++) {
			String expectedModifier = (String) expectedModifiers.get(i);
			String[] modifiers = actualModifiersString.split(" ");
			boolean modifierIsSet = false;
			for (String modifier : modifiers) {
				if (expectedModifier.equals(modifier)) {
					modifierIsSet = true;
				}
			}
			assertEquals("Expected modifiers " + expectedModifiers
					+ " but were " + actualModifiersString + ".", true,
					modifierIsSet);
		}
	}

}
