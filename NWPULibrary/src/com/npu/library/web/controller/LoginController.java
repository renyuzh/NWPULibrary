package com.npu.library.web.controller;

import java.io.IOException;
import java.util.List;

import com.npu.library.constant.Constant;
import com.npu.library.dao.BaseDAO;
import com.npu.library.dao.domain.Admin;
import com.npu.library.dao.domain.Student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {

    @Resource
    private BaseDAO<Student> studentDao;

    @Resource
    private BaseDAO<Admin> adminDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public int studentLogin(HttpServletRequest request, String username, String password, int role) {
        if (role == 1) {//学生身份
            Student student = new Student();
            student.setSno(username);
            //字符串连接必须这样写，只有整数id才能用=
            String sql = "from com.npu.library.dao.domain.Student where sno = ?";
            String[] para =  { username };
            List<Student> stus = studentDao.find(sql,para);
            if (stus == null || stus.size() == 0) {
                return -1;//无数据
            } else {
                if (password.equals(stus.get(0).getPassword())) {
                    request.getSession().setAttribute(Constant.student_login_session_name, stus.get(0).getSno());
                    request.getSession().setAttribute(Constant.user_id, stus.get(0).getId());
                    request.getSession().setAttribute(Constant.user_name, stus.get(0).getSname());
                    return 1;//成功
                } else {
                    return -2;//密码不正确
                }
            }
        } else if (role == 2) {//管理员身份
            String sql = "from com.npu.library.dao.domain.Admin where ano = ?";
            String[] para = { username };
            List<Admin> adms = adminDao.find(sql,para);
            if (adms == null || adms.size() == 0) {
                return -1;//无数据
            } else {
                if (password.equals(adms.get(0).getPassword())) {
                    request.getSession().setAttribute(Constant.admin_login_session_name, adms.get(0).getAno());
                    request.getSession().setAttribute(Constant.user_id, adms.get(0).getId());
                    request.getSession().setAttribute(Constant.user_name,adms.get(0).getAname());
                    return 1;//成功
                } else {
                    return -2;//密码不正确
                }
            }
        } else {
            return 0;
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.getSession().setAttribute(Constant.student_login_session_name, null);
        request.getSession().setAttribute(Constant.admin_login_session_name, null);
        response.sendRedirect(request.getContextPath());
    }
}
