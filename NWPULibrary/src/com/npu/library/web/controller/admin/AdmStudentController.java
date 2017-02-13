package com.npu.library.web.controller.admin;

import com.npu.library.constant.Constant;
import com.npu.library.dao.BaseDAO;
import com.npu.library.dao.domain.Student;
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
@RequestMapping("/admin/student")
public class AdmStudentController {

    @Resource
    private BaseDAO<Student> studentDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "admin/student";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<Student> list(ModelMap model, Student student) {
        List<Student> students = studentDao.find("from com.npu.library.dao.domain.Student");
        PageBean<Student> data = new PageBean<Student>();
        data.setData(students);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    public Student findById(ModelMap model, Student student) {
        List<Student> students = studentDao.find("from com.npu.library.dao.domain.Student where id = "+student.getId());
        if (students == null || students.size() == 0)
        	return null;
        return students.get(0);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public int save(ModelMap model, Student student) throws ParseException {
    	String sql = "from com.npu.library.dao.domain.Student where sno = ?";
        String[] para =  { student.getSno() };
        List<Student> stus = studentDao.find(sql,para);
        if (stus.size() > 0) {
            return -1;
        }
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Date cdate = d.parse(d.format(new Date()));
        student.setCdate(cdate);
        studentDao.save(student);
        return   1 ;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean update(ModelMap model, Student student) {
        		studentDao.update(student);
        	       return  true;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean delete(ModelMap model, Student student) {
        studentDao.delete(student);
        return true;
    }
}