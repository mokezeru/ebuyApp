package cs544.project.controller;

import cs544.project.model.User;
import cs544.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import java.util.Collection;
import java.util.List;

@Controller
public class UserControlller {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(@AuthenticationPrincipal final UserDetails userDetails, Model model) {
    	
    	String userName = userDetails.getUsername();
    	String pw = userDetails.getPassword();
    	
    	Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
    	GrantedAuthority  theRole = roles.stream().findFirst().get(); 
    	String role = theRole.toString();
    	
    	if(role.equalsIgnoreCase("ROLE_ADMIN")) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/userList";
    	}
    	else {
    		return "redirect:/home";
    	}
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/addUser";
    }
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("errors",result.getAllErrors());
            return "user/addUser";
        }
        user.setRole("USER");
        userService.add(user);
        
       // return "redirect:/users";
        
        return "redirect:/products";
    }

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable Long id, Model model) {
        //System.out.println("Edit");
        model.addAttribute("user", userService.get(id));
        //productService.add(user);
        return "user/updateUser";
    }
}
