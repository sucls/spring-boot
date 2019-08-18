package com.sucl.convert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootConvertApplicationTests {

    @Test
    public void contextLoads() {

    }

    public static void main(String[] args) {
        TypeDescriptor ptype = TypeDescriptor.valueOf(Pa.class);
        TypeDescriptor stype = TypeDescriptor.valueOf(Sa.class);

        // son -> parent
        System.out.println(Pa.class.isAssignableFrom(Sa.class));
        System.out.println(stype.isAssignableTo(ptype));
    }

    public class Pa{ }

    public class Sa extends Pa{ }

}
