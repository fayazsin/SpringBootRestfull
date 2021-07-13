package restfullwebservice02;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SF02Controller {
	
	private SF02Service studentService;
	@Autowired
	SF02Controller (SF02Service studentService) {
		this.studentService=studentService;
	}
	
	@GetMapping(path="api/v1/students")
	public List<SF02StudentBean> getStudents1(){
		
		return studentService.listStudents();
	}
	@GetMapping(path="api/v1/students/{id}")
	public SF02StudentBean getStudentById(@PathVariable Long id) {
		return studentService.selectStudentById(id);
	}
	

}
