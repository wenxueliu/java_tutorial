package org.wenxueliu;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.Iterator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.TestFactory;

@RunWith(JUnitPlatform.class)
public class TranslationEngineTest {

  private TranslatorEngine translatorEngine;

  @BeforeEach
  public void setUp() {
    translatorEngine = new TranslatorEngine();
  }

  @Test
  public void testTranlsateHello() {
    assertEquals("Bonjour", translatorEngine.tranlate("Hello"));
  }

  @Test
  public void testTranlsateYes() {
    assertEquals("Oui", translatorEngine.tranlate("Yes"));
  }

  @Test
  public void testTranlsateNo() {
    assertEquals("Non", translatorEngine.tranlate("No"));
  }

  public void translateDynamicTests() {
    List<String> inPhrases = new ArrayList<>(Arrays.asList("Hello", "Yes", "No"));
    List<String> outPhrases = new ArrayList<>(Arrays.asList("Bonjour", "Oui", "Non"));
    Collection<DynamicTest> dynamicTests = new ArrayList<>();

    for (int i = 0; i < inPhrases.size(); i++) {
      String phr = inPhrases.get(i);
      String outPhr = outPhrases.get(i);

      // create an test execution
      Executable exec = () -> assertEquals(outPhr, translatorEngine.tranlate(phr));

      // create a test display name
      String testName = " Test tranlate " + phr;
      // create dynamic test
      DynamicTest dTest = DynamicTest.dynamicTest(testName, exec);

      // add the dynamic test to collection
      dynamicTests.add(dTest);
    }
  }

  @TestFactory
  public Collection<DynamicTest> translateDynamicTestFactory() {

    List<String> inPhrases =
        new ArrayList<>(Arrays.asList("Hello", "Yes", "No", "Goodbye", "Good night", "Thank you"));
    List<String> outPhrases =
        new ArrayList<>(Arrays.asList("Bonjour", "Oui", "Non", "Au revoir", "Bonne nuit", "Merci"));

    Collection<DynamicTest> dynamicTests = new ArrayList<>();

    for (int i = 0; i < inPhrases.size(); i++) {

      String phr = inPhrases.get(i);
      String outPhr = outPhrases.get(i);

      // create an test execution
      Executable exec = () -> assertEquals(outPhr, translatorEngine.tranlate(phr));

      // create a test display name
      String testName = "Test tranlate " + phr;
      // create dynamic test
      DynamicTest dTest = DynamicTest.dynamicTest(testName, exec);

      // add the dynamic test to collection
      dynamicTests.add(dTest);
    }
    return dynamicTests;
  }

  @TestFactory
  public Stream<DynamicTest> translateDynamicTestsFromStream() {

    List<String> inPhrases =
        new ArrayList<>(Arrays.asList("Hello", "Yes", "No", "Goodbye", "Good night", "Thank you"));
    List<String> outPhrases =
        new ArrayList<>(Arrays.asList("Bonjour", "Oui", "Non", "Au revoir", "Bonne nuit", "Merci"));

    return inPhrases.stream().map(phrs -> DynamicTest.dynamicTest("Test translate " + phrs, () -> {
      int idx = inPhrases.indexOf(phrs);
      assertEquals(outPhrases.get(idx), translatorEngine.tranlate(phrs));
    }));
  }

 @TestFactory
  public Iterable<DynamicTest> translateDynamicTestsFromIterate() {

    List<String> inPhrases =
        new ArrayList<>(Arrays.asList("Hello", "Yes", "No", "Goodbye", "Good night", "Thank you"));
    List<String> outPhrases =
        new ArrayList<>(Arrays.asList("Bonjour", "Oui", "Non", "Au revoir", "Bonne nuit", "Merci"));

    return inPhrases.stream().map(phrs -> DynamicTest.dynamicTest("Test translate " + phrs, () -> {
      int idx = inPhrases.indexOf(phrs);
      assertEquals(outPhrases.get(idx), translatorEngine.tranlate(phrs));
    })).collect(Collectors.toList());
  }

 @TestFactory
  public Iterator<DynamicTest> translateDynamicTestsFromIterator() {

    List<String> inPhrases =
        new ArrayList<>(Arrays.asList("Hello", "Yes", "No", "Goodbye", "Good night", "Thank you"));
    List<String> outPhrases =
        new ArrayList<>(Arrays.asList("Bonjour", "Oui", "Non", "Au revoir", "Bonne nuit", "Merci"));

    return inPhrases.stream().map(phrs -> DynamicTest.dynamicTest("Test translate " + phrs, () -> {
      int idx = inPhrases.indexOf(phrs);
      assertEquals(outPhrases.get(idx), translatorEngine.tranlate(phrs));
    })).iterator();
  }
}
