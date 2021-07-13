package restfullwebservice01;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class SF01Controller {
	//1.Way
//	@RequestMapping(method=RequestMethod.GET , path="/getRequest" )
//	public String getMethod() {
//		return "Get Request is taken";	
//	}
	
	//2. Way Recommended
	@GetMapping (path="/getRequest" )
	public String getMethod1() {
		return "Get Request is taken";	
	}
	
	@GetMapping (path="/getBean" )
	public SF02ControllerBean getMethod2() {
		return new SF02ControllerBean("This is get request to retun message object...");	
	}
	
	@GetMapping(path="getParameter/{name}")
	public SF02ControllerBean getMethod3(@PathVariable String name) {
		return new SF02ControllerBean(String.format("Hey, %s  this is your page ... ",name));
		
	}
	@GetMapping (path="getList/{name}")
	public List<SF02ControllerBean> getMethod4(@PathVariable String name){
		return List.of(new SF02ControllerBean(String.format("Hi %s ",name )),
				new SF02ControllerBean(String.format("How are you %s ",name )),
				new SF02ControllerBean(String.format(" %s do you want to drink cofee",name ))
				) ;
	}

}
