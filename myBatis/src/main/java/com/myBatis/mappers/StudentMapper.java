package com.myBatis.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.myBatis.model.Student;

public interface StudentMapper {
	List<Student> findAll();
	
	List<Student> findById(String id);
	
	List<Student> findByMultipleParams(@Param("id") String id, @Param("name") String name);
	
	List<Student> findByMapParams(Map<String, String> params);
}
