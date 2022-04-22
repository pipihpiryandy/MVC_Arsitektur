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
import kampusapp.model.Mahasiswa;
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
public class MahasiswaController {
    KampusDAO mhsDao = null;
    public MahasiswaController(){
        mhsDao=new KampusDAOProses();
    }
    @RequestMapping(value="/mahasiswa-form")
    public ModelAndView index(HttpServletRequest req, HttpServletResponse res){
        String action=req.getParameter("action");
        ModelAndView modelandview;
        if(action == null){ action = "ADD";}
        
        HttpSession session=req.getSession(false);
        if(session==null){
            return new ModelAndView("redirect:/login.html");
        }
        switch(action.toUpperCase()){
            case "EDIT":
                modelandview = editMahasiswa(req,res);
                break;
            case "DELETE":
                modelandview = deleteMahasiswa(req,res);
                break;
            case "LOGOUT":
                session.invalidate();
                return new ModelAndView("redirect:/login.html");
            default:
                modelandview = addMahasiswa(req,res);
                break;    
        }
        return modelandview;
    }
    
    @RequestMapping(value="/mahasiswa-formedit")
    public ModelAndView indexedit(HttpServletRequest req, HttpServletResponse res){
        String action=req.getParameter("action");
        ModelAndView modelandview;
        if(action == null){
            action = "ADD";
        }
        switch(action.toUpperCase()){
            case "EDIT":
                modelandview = editMahasiswa(req,res);
                break;
            case "DELETE":
                modelandview = deleteMahasiswa(req,res);
                break;
            default:
                modelandview = addMahasiswa(req,res);
                break;    
        }
        return modelandview;
    }
    
    
    @RequestMapping(value="/mahasiswa-form", method=RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("mhs") Mahasiswa mh){
        ModelAndView modelandview=new ModelAndView("redirect:/mahasiswa-form.html");   
            if(mhsDao.save(mh)){
                return new ModelAndView("redirect:/home.html");  
            }else {
                modelandview.addObject("message", "Error Save");
            }
        
        return modelandview;
    }
    
    @RequestMapping(value="/mahasiswa-formedit", method=RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("mhs") Mahasiswa mh){
        ModelAndView modelandview=new ModelAndView("redirect:/mahasiswa-formedit.html");
            if(mhsDao.update(mh)){
                return new ModelAndView("redirect:/home.html");  
            }else {
                modelandview.addObject("message", "Error Save");
            }
        
        return modelandview;
    }

    private ModelAndView editMahasiswa(HttpServletRequest req, HttpServletResponse res) {
        String id=req.getParameter("id");
        Mahasiswa mah=mhsDao.getSinggle(Integer.parseInt(id));
        ModelAndView modelandview=new ModelAndView();
        modelandview.addObject("mhs", mah);
        return modelandview;
    }

    private ModelAndView deleteMahasiswa(HttpServletRequest req, HttpServletResponse res) {
        String id=req.getParameter("id");
        if(mhsDao.delete(Integer.parseInt(id))){
            return new ModelAndView("redirect:/home.html");
        }else{
            req.setAttribute("message","gagal delete");
            return addMahasiswa(req,res);
        }
    }
    

    private ModelAndView addMahasiswa(HttpServletRequest req, HttpServletResponse res) {
        if(req.getParameter("message") !=null){
            ModelAndView modelandview = new ModelAndView();
            modelandview.addObject("message", req.getParameter("message"));
            return modelandview;
        }
        return new ModelAndView();
    }
}
