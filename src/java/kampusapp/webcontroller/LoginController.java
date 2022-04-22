/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kampusapp.webcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kampusapp.dao.KampusDAO;
import kampusapp.dao.KampusDAOProses;
import kampusapp.model.LoginModel;
import kampusapp.model.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author piryandyp
 */
@Controller
public class LoginController {
        KampusDAO mhsDao=null;
        public LoginController(){
        mhsDao=new KampusDAOProses();
 }
        
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public ModelAndView Login(HttpServletRequest req,HttpServletResponse Res){
        if(req.getParameter("message")!= null){
            ModelAndView modelandview= new ModelAndView();
            modelandview.addObject("message", req.getParameter("message"));
            return modelandview;
        }
        return new ModelAndView();
    }
    @RequestMapping(value="/login/save")
     public ModelAndView save(@ModelAttribute("emp") LoginModel emp,HttpServletRequest req){
         ModelAndView modelandview=new ModelAndView("redirect:/login.html");
            UserLogin user=mhsDao.getLogin(emp.getUsername(),emp.getPassword());
            if(user!=null){
                modelandview.addObject("user", user);
                HttpSession session=req.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(30*60);
                if(user.getRole().equalsIgnoreCase("admin")){
                    return new ModelAndView("redirect:/home.html");
                }else{
                   return new ModelAndView("redirect:/member.html");
                }                 
                }else {
                modelandview.addObject("message", "username dan password salah");
            }
           return modelandview; 
     }  
}
