package com.myBatis;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myBatis.mappers.StudentMapper;
import com.myBatis.model.Student;

@SpringBootTest
class MyBatisApplicationTests {

	@Autowired
    private StudentMapper studentMapper;

    @Test
    public void testHello1() {
        List<Student> students = studentMapper.findAll();
        students.forEach(System.out::println);
        assertTrue(students!=null&&students.size()>0);
        
        List<Student> students1 = studentMapper.findById("2");
        System.out.println(students1.get(0).getName());
        assertTrue(students1!=null&&students1.size()>0);
        
        List<Student> students2 = studentMapper.findByMultipleParams("1", "aaa");
        System.out.println(students2.get(0).getName());
        assertTrue(students2!=null&&students2.size()>0);
        
        Map<String, String> parms = new HashMap<String, String>();
        parms.put("table", "tbl_student");
        parms.put("criteria", "2");
        List<Student> students3 = studentMapper.findByMapParams(parms);
        System.out.println(students3.get(0).getName());
        assertTrue(students3!=null&&students3.size()>0);
        
    }

}
