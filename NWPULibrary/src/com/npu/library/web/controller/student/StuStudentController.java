package com.npu.library.web.controller.student;

import java.util.List;

import com.npu.library.constant.Constant;
import com.npu.library.dao.BaseDAO;
import com.npu.library.dao.domain.Student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/student/student")
public class StuStudentController {

    @Resource
    private BaseDAO<Student> studentDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        Integer id = (Integer) request.getSession().getAttribute(Constant.user_id);
        Student student = new Student();
        student.setId(id);
        student = studentDao.find("from com.npu.library.dao.domain.Student where id = "+id).get(0);
        model.addAttribute("username", username);
        model.addAttribute("student", student);
        return "student/student";
    }


    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public int update(ModelMap model, Student student) {
    	  System.out.println(student.toString());
    	List<Student> stus = studentDao.find("from com.npu.library.dao.domain.Student where id = "+student.getId());
        if (stus == null || stus.size() == 0) {
            return -1;
        }
        if (!stus.get(0).getPassword().equals(student.getPassword())) {
            return -2;
        }
        System.out.println(student.toString());
        stus.get(0).setPassword(student.getPasswordNew());
        stus.get(0).setSname(student.getSname());
        studentDao.update(stus.get(0)) ;
        return  1 ;
    }
}