package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author Fatih Totrakanlı
 *
 */

//Controller sınıfı
//Temel map olarak /index atanmıştır. Proje ' localhost:8080/index ' adresinde çalışmaktadır. 
@Controller
@RequestMapping("/index")
public class IndexController {

	
	private IUserService userService;
	
	//Spring Setter Injection
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	//Boş value değeri temel Mapping'i yani /index'i kullanacağını göstermektedir.
	@RequestMapping(value="", method=RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("list", userService.listAllUsers());
        return model;
    }
	
		//View katmanından gelen "id" değeri @PathVariable anotasyonu ile alınmaktadır.
	   @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	    public ModelAndView deleteUsers(@PathVariable long id) {
		   	userService.deleteUser(id);
	        return new ModelAndView("redirect:/index");
	    }
	   
	  
	//@ModelAttribute anotasyonu View katmanından gelen kullanıcı bilgilerini sınıf tipinde getirir.
	//@RequestParam anotasyonuda View katmanından bir istenen değeri getirir. @ModelAttribute daha kullanışlıdır.
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView userRegister(@ModelAttribute("user")User user){
    	ModelAndView model = new ModelAndView("index");
    	if(user!=null){
    	userService.saveUser(user);
    	model.addObject("warning", "User Registration Success");
    	
    	}
    	else{
    		model.addObject("danger","Something Going Bad" );
    		
    	}
    	return new ModelAndView("redirect:/index");
    }
    	  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    	    public ModelAndView edit(@PathVariable("id") long id) {
    		  ModelAndView model = new ModelAndView("edit");
    	        User user = userService.getUserById(id);
    	        model.addObject("user", user);
    	        return model;
    	    }
    	  
    	  //Bu methodda @RequestParam kullanılışı gösterilmiştir. Veriler tek tek alınmıştır.
    	  //Bunun yerine @ModelAttribute kullanılıp "user" tipindeki nesnenin get methoduyla "id" bilgisi alınıp kaydedilebilirdi.
    	   @RequestMapping(value = "/update", method = RequestMethod.POST)
    	    public ModelAndView update(@RequestParam("id") long id,
    	                               @RequestParam("name") String name,@RequestParam("surname") String surname,
    	    						   @RequestParam("adress") String adress) {
    	        User user = userService.getUserById(id);
    	        user.setName(name);
    	        user.setSurname(surname);
    	        user.setAdress(adress);
    	        userService.saveUser(user);
    	        return new ModelAndView("redirect:/index");
    	    }
   
}
