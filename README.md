
## 单元测试概述

单元测试是编写测试代码，用来检测特定的、明确的、细颗粒的功能。单元测试并不一定保证程序功能是正确的，更不保证整体业务是准备的。

单元测试不仅仅用来保证当前代码的正确性，更重要的是用来保证代码修复、改进或重构之后的正确性。

一般来说，单元测试任务包括

    接口功能测试：用来保证接口功能的正确性。
    局部数据结构测试（不常用）：用来保证接口中的数据结构是正确的
        比如变量有无初始值
        变量是否溢出
    边界条件测试
        变量没有赋值（即为NULL）
        变量是数值（或字符)
            主要边界：最小值，最大值，无穷大（对于DOUBLE等）
            溢出边界（期望异常或拒绝服务）：最小值-1，最大值+1
            临近边界：最小值+1，最大值-1
        变量是字符串
            引用“字符变量”的边界
            空字符串
            对字符串长度应用“数值变量”的边界
        变量是集合
            空集合
            对集合的大小应用“数值变量”的边界
            调整次序：升序、降序
        变量有规律
            比如对于Math.sqrt，给出n^2-1，和n^2+1的边界
    所有独立执行通路测试：保证每一条代码，每个分支都经过测试
        代码覆盖率
            语句覆盖：保证每一个语句都执行到了
            判定覆盖（分支覆盖）：保证每一个分支都执行到
            条件覆盖：保证每一个条件都覆盖到true和false（即if、while中的条件语句）
            路径覆盖：保证每一个路径都覆盖到
        相关软件
            Cobertura：语句覆盖
            Emma: Eclipse插件Eclemma
    各条错误处理通路测试：保证每一个异常都经过测试

Mock 和 Stub 是两种测试代码功能的方法。Mock 测重于对功能的模拟。Stub 测重于对功能的测试重现。
比如对于List接口，Mock会直接对List进行模拟，而Stub会新建一个实现了List的TestList，在其中编写测试的代码。
强烈建议优先选择Mock方式，因为Mock方式下，模拟代码与测试代码放在一起，易读性好，而且扩展性、灵活性都比Stub好。

比较流行的Mock有：

    JMock
    EasyMock
    Mockito
    powermock

其中EasyMock和Mockito对于Java接口使用接口代理的方式来模拟，对于Java类使用继承的方式来模拟（也即会创建一个新的Class类）。Mockito支持spy方式，可以对实例进行模拟。但它们都不能对静态方法和final类进行模拟，powermock通过修改字节码来支持了此功能。


Sonar ＋ Jacoco 

## Junit

mvn archetype:generate -DgroupId=org.wenxueliu -DartifactId=java-tutorial -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


## Junit 5

Just for clarification: The JUnitPlatform runner is a means to execute tests via the JUnit
Platform using JUnit Vintage's JUnitCore. It is intended to bridge the gap for IDEs and
build tools that don't support the new JUnit Platform.

Keep in mind that the JUnitPlatform Runner was only ever intended as a temporary work-around
until IDEs and build tools supported launching the JUnit Platform natively. So, since IntelliJ
IDEA (and beta support in Eclipse 4.7) already support the platform natively, the JUnitPlatform
runner should no longer be used.

If people want a declarative means for configuring a suite on the platform (e.g., like the
annotations supported by the JUnitPlatform runner), that should be addressed as a separate
first-class feature of the JUnit Platform.

### introduction

JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

JUnit Platform

To be able to launch junit tests, IDEs, build tools or plugins need to include and extend platform APIs.
It defines the TestEngine API for developing new testing frameworks that runs on the platform.
It also provides a Console Launcher to launch the platform from the command line and build plugins for Gradle and Maven.

JUnit Jupiter

It includes new programming and extension models for writing tests. It has all new junit annotations and TestEngine implementation to run tests written with these annotations.


JUnit Vintage

It primary purpose is to support running JUnit 3 and JUnit 4 written tests on the JUnit 5 platform. It’s there are backward compatibility.

### Annotations

    JUnit 5 Annotations 	Descriptions
    @BeforeEach 	        The annotated method will be run before each test method(annotated with @Test) in the current class.
    @AfterEach 	            The annotated method will be run after each test method (annotated with @Test) in the current class.
    @BeforeAll 	            The annotated method will be run before all test methods in the current class. This method must be static.
    @AfterAll 	            The annotated method will be run after all test methods in the current class. This method must be static.
    @Test 	                Declares a test method
    @DisplayName 	        Define custom display name for a test class or test method; different from @UseTechnicalNames
    @Disable 	            Is used to disable or ignore a test class or method.
    @Nested 	            Used to declare nested test classes
    @Tag 	                Declare tags for test discovering and filtering
    @TestFactory 	        Denotes a method is a test factory for dynamic tests in JUnit 5

### Assert

AssertAll, AssertNotNull, and AssertEquals

AssertNull

ExpectThrows and AssertThrows

AssertTrue and AssertFalse

AssertSame, AssertNotSame, and Fail

### Disable or Ignore A Test

To continue the series of the JUnit 5 tutorials, I’d like to share how to disable
or ignore a test in JUnit 5. During the development, you may want to temporarily
ignore or disable a test, a group of tests or even disable a test class for some
reasons. There are several ways for you to do that. You can either comment out a
test method, or delete the @Test annotation on that method. However, the test
runners will not report such a test on the test results. This may lead us to miss
some test cases because of forgetting to fix them. Fortunately, JUnit 5 provides
us an annotation to disable or ignore a test but still include it in the test
report, and it is @Disabled. Note that with JUnit 4, we can use @Ignore annotation
to achieve the same purpose. We will try an example in the following parts.

### Assume

 Note that if the assumption is false, then all the tests under the assumeTrue command, will be skipped.

## Test Suit

JUnit 5 Test Suite

@SelectPackages :  specify the names of packages to be selected  when running a test suite.

@SelectClasses : specify the classes to be selected when running a test suite.

@Nested

@IncludePackages and @ExcludePackages to filter packages

@IncludeClassNamePatterns and @ExcludeClassNamePatterns to filter test classes

@IncludeTags and @ExcludeTags to filter test methods

some functions and several of them have complex business domain logic. Normally,
we can put on the tests for all methods of that class in the same test class.
However, if we want to group all the test methods for those several complex
methods together, or if we want to group all related test methods by features,
we can use the JUnit 5 @Nested annotation to achieve those purposes.


## Assertions

assertEquals()
assertNotEquals()


## Static Tests vs Dynamic Tests

### Static Test

They are are static in the sense that they are fully specified at compile-time,
and their behavior cannot be changed at run-time.


### Dynamic Test

In contrary to the Static Tests which allow us to define statically number of
fixed test cases at the compile time, the Dynamic Tests allow us to define the
tests case dynamically in the run-time.

Dynamic tests can be generated by a factory method annotated with @TestFactory which is a new annotation of JUnit 5
@TestFactory method must return a Stream, Collection, Iterable, or Iterator of DynamicTest instances
@TestFactory methods must not be private or static and may optionally declare parameters to be resolved by ParameterResolvers

## document

https://github.com/howtoprogram/junit5-examples //源码
https://howtoprogram.xyz/2017/01/17/read-file-and-resource-in-junit-test/
https://howtoprogram.xyz/2017/09/17/integrate-junit-5-with-jacoco-and-sonarqube-in-maven-builds/
http://junit.org/junit5/docs/current/user-guide/#overview
http://junit.org/junit5/docs/current/api/index.html
https://howtoprogram.xyz/java-technologies/junit-5-tutorial/
https://howtoprogram.xyz/2016/08/10/junit-5-vs-junit-4/
http://blog.thihy.info/post/103

## Junit4 VS Juni5

###Java version

    JUnit 4             JUnit 5
    Java 5 or higher    Java 8 or higher

### Annotations


    Features                                                                                            JUnit 5         JUnit 4
    Declares a test method 	@Test 	@Test
    Denotes that the annotated method will be executed before all test methods  in the current class 	@BeforeAll 	    @BeforeClass
    Denotes that the annotated method will be executed after all test methods  in the current class 	@AfterAll 	    @AfterClass
    Denotes that the annotated method will be executed before each test method 	                        @BeforeEach 	@Before
    Denotes that the annotated method will be executed after each test method 	                        @AfterEach 	    @After
    Disable a test method or a test class 	                                                            @Disable 	    @Ignore
    Denotes a method is a test factory for dynamic tests in JUnit 5 	                                @TestFactory 	N/A
    Denotes that the annotated class is a nested, non-static test class 	                            @Nested 	    N/A
    Declare tags for filtering tests 	                                                                @Tag 	        @Category
    Register custom extensions in JUnit 5 	                                                            @ExtendWith 	N/A
    Repeated Tests in JUnit 5 	                                                                        @RepeatedTest 	N/A

### Assertions

    JUnit 4             JUnit 5
    fail 	            fail
    assertTrue 	        assertTrue
    assertThat 	        N/A
    assertSame 	        assertSame
    assertNull 	        assertNull
    assertNotSame 	    assertNotSame
    assertNotEquals 	assertNotEquals
    assertNotNull 	    assertNotNull
    assertFalse 	    assertFalse
    assertEquals 	    assertEquals
    assertArrayEquals 	assertArrayEquals
                        assertAll
                        assertThrows

Essential notes

Except for 2 last annotations (assertAll, assertThrows) only existing in JUnit 5, almost remaining annotations are the same between
JUnit 5 vs JUnit 4. However, there is some difference in method signatures  as below:

#### Difference  in the position of optional assertion message parameter

JUnit 4.

```java
assertEquals( "The optional assertion message.",1, 1);
```

The optional assertion message is the first parameter applied for all assertion methods support it.

JUnit 5.

```java
assertEquals(1, 1, "The optional assertion message.");
```

The optional assertion message is the last parameter applied for all assertion methods support it.

#### Assert methods in JUnit 5 can be used with Java 8 Lambdas.

For examples:

```
assertTrue(1 == 1, () -> "Assertion messages can be provided by Java 8 Lambdas ");

 Throwable exception = expectThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Invalid age.");
        });
```

### Assumptions

    JUnit 4             JUnit 5
    assumeFalse 	    assumeFalse
    assumeNoException
    assumeNotNull
    assumeThat 	        assumeThat
    assumeTrue 	        assumeTrue

JUnit 5 provides fewer assumptions than JUnit 4. Note that JUnit 5 overloads each its assumption method to be used with Java 8 Lambda Expressions, for ex:


```java
assumeTrue("QA".equals(System.getenv("ENV")),
            () -> "Should run on QA environment");
```

#### Tagging and Filtering (JUnit 5) vs Label and Group tests (JUnit 4)

JUnit 4

Use @category annotation for label and group tests.

Example:

```java
public interface FastTests { /* category marker */ }
public interface SlowTests { /* category marker */ }

public class A {
  @Test
  public void a() {
    fail();
  }

  @Category(SlowTests.class)
  @Test
  public void b() {
  }
}

@Category({SlowTests.class, FastTests.class})
public class B {
  @Test
  public void c() {

  }
}

@RunWith(Categories.class)
@IncludeCategory(SlowTests.class)
@SuiteClasses( { A.class, B.class }) // Note that Categories is a kind of Suite
public class SlowTestSuite {
  // Will run A.b and B.c, but not A.a
}
```
JUnit 5

Use @tag annotation for tagging and filtering.

```java
@Tag("fast")
@Tag("model")
class TaggingDemo {
    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }
}
```

## Parameterized Tests

JUnit 5 supports Parameterized Tests by default. This feature allows us to run a test multiple times with different arguments.

For example, let’s see the following test:

```java
@ParameterizedTest
@ValueSource(strings = { "Hello", "World" })
void testWithStringParameter(String argument) {
    assertNotNull(argument);
}
```

The @ParameterizedTest and @ValueSource annotations make the test can run with each value provided by the @ValueSource annotation.
For instance, the ConsoleLauncher will print output similar to the following:

```
testWithStringParameter(String) ✔
├─ [1] Hello ✔
└─ [2] World ✔
```

Besides the @ValueSource, JUnit 5 provides many kinds of sources can be used with Parameterized Tests such as:

* @CsvFileSource: lets us use CSV files from the classpath. Each line from a CSV file results in one invocation of the parameterized test.
* @MethodSource: allows us to refer to one or multiple methods of the test class. Each method must return a Stream, an Iterable, an Iterator, or an array of arguments.
* @ArgumentsSource: can be used to specify a custom, reusable ArgumentsProvider.
* @EnumSource: provides a convenient way to use Enum constants.

### Repeated Tests

A new feature in JUnit5 which allows us to repeat a test in a specified number of times is Repeated Tests.
Let's see an example which declares a test that will be repeated in 100 times:

```java
@RepeatedTest(100)
void repeatedTest() {
    // ...
}
```
## Dynamic Tests

JUnit 5 introduces the concept of Dynamic Tests which are tests that can be generated at runtime by a factory method.
Let's see an example which we generate 2 tests at runtime:

```java
@TestFactory
Collection<DynamicTest> dynamicTestsFromCollection() {
    return Arrays.asList(
        dynamicTest("1st dynamic test", () -> assertTrue(true)),
        dynamicTest("2nd dynamic test", () -> assertEquals(4, 2 * 2))
    );
}
```

You can refer to another article: JUnit 5 Dynamic Tests – Generate Tests at Run-time for more detail.


