package com.npu.library.web.controller.admin;

import com.npu.library.constant.Constant;
import com.npu.library.dao.BaseDAO;
import com.npu.library.dao.domain.Admin;
import com.npu.library.web.bean.PageBean;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/admin")
public class AdmAdminController {

    @Resource
    private BaseDAO<Admin> adminDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "admin/admin";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<Admin> list(ModelMap model, Admin admin) {
    	
        List<Admin> admins = adminDao.find("from com.npu.library.dao.domain.Admin");
        PageBean<Admin> data = new PageBean<Admin>();
        data.setData(admins);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    public Admin findById(ModelMap model, Admin admin) {
    	System.out.println("findById"+admin.toString());
        List<Admin> admins =  adminDao.find("from com.npu.library.dao.domain.Admin where id = " + admin.getId());
        if(admins == null || admins.size() == 0)
        	return null;
        return admins.get(0);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public int save(ModelMap model, Admin admin) throws ParseException {
        String sql = "from com.npu.library.dao.domain.Admin where ano = ?";
                String[] para =  { admin.getAno() };
        List<Admin> adms = adminDao.find(sql, para);
        		if (adms != null && adms.size() > 0) {
            return -1;
        }
        		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
                Date cdate = d.parse(d.format(new Date()));
                admin.setCdate(cdate);
        		adminDao.save(admin);
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public boolean update(ModelMap model, Admin admin) {
         adminDao.update(admin);
         return true;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public boolean delete(ModelMap model, Admin admin) {
         adminDao.delete(admin);
         return true;
    }
}