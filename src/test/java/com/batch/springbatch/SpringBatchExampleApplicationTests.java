package com.batch.springbatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBatchExampleApplicationTests {
Calculator calculator = new Calculator();
    @Test
void itShouldAddNumbers(){
        // given
        int numberOne = 15;
        int numberTwo = 10;

        // when
        int result = calculator.add(numberOne,numberTwo);

        // then

        assertThat(result).isEqualTo(25);
}

class Calculator{
    int add(int a, int b){
        return a+b;
    }
}
}
