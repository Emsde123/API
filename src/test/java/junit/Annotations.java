package junit;


import org.junit.*;

public class Annotations {

    // @BeforeClass
    // runs once before every class

    // @AfterClass
    // runs once after every class
    // Note: Methods with @

    // @Before

    // @After
    // runs after every test

    // @Test
    // @Ignore


    @Test
    public void test1(){
        System.out.println("Test1");
    }

    @Test
    public void test2(){
        System.out.println("Test2");
    }

    @Ignore // written above @Test annotation, ignores the test.
    @Test
    public void test3(){
        System.out.println("Test3");
    }

    @After // runs after every test case
    public void test4(){
        System.out.println("Test4");
    }

    @Before // runs before every test case
    public void test5(){
        System.out.println("Test5");
    }

    @BeforeClass // runs before each execution of the class (runs only once)
    public void test6(){
        System.out.println("Test6");
    }

    @AfterClass // runs before each execution of the class (only runs once)
    public void test7(){
        System.out.println("Test7");
    }

}
