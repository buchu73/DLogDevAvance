import org.example.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    public void testAdd(){
        // GIVEN
        Calculator target = new Calculator();
        // WHEN
        int result = target.add(1, 2);
        // THEN
        Assertions.assertEquals(3, result);
    }

    @Test
    public void test_Div(){
        Calculator target = new Calculator();
        int result = target.div(9,3);
        Assertions.assertEquals(3, result);
    }

    @Test
    public void test_Div_ByZero(){
        Calculator target = new Calculator();
        try {
            int result = target.div(9,0);
            Assertions.fail();
        }
        catch (ArithmeticException e)
        {
            Assertions.assertEquals("/ by zero", e.getMessage());
        }
        catch (Exception ex){
            Assertions.fail();
        }
    }

    @Test
    public void test_Div_ByZero_Assert(){
        Calculator target = new Calculator();
        Exception exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int result = target.div(9,0);
        });

        Assertions.assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    public void test_Div_String(){
        Calculator target = new Calculator();
        String result = target.divString(9,3);
        Assertions.assertEquals("3",result);
    }

    @Test
    public void test_Div_String_ByZero(){
        Calculator target = new Calculator()    ;
        String result = target.divString(9,0);
        Assertions.assertEquals("Cannot divide by zero", result);
    }

    @Test
    public void test_Div_Double() {
        Calculator target = new Calculator();
        double result = target.divDouble(10, 3.0);
        Assertions.assertEquals(3.33333, result, 0.00001);
    }
}
