package com.npu.library.web.controller.admin;

import com.npu.library.constant.Constant;
import com.npu.library.dao.BaseDAO;
import com.npu.library.dao.domain.Book;
import com.npu.library.dao.domain.BookStudent;
import com.npu.library.dao.domain.Student;
import com.npu.library.web.bean.PageBean;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/return")
public class AdmReturnController {

    @Resource
    private BaseDAO<BookStudent> bookStudentDao;

    @Resource
    private BaseDAO<Student> studentDao;

    @Resource
    private BaseDAO<Book> bookDao;


    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "admin/return";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<BookStudent> list(ModelMap model, BookStudent bookStudent) {
        PageBean<BookStudent> data = new PageBean<BookStudent>();

        if (null == bookStudent.getSno() || "".equals(bookStudent.getSno())){
            data.setData(new ArrayList<BookStudent>());
            return data;
        }

        List<BookStudent> bookStudents = bookStudentDao.find("from com.npu.library.dao.domain.BookStudent");
        data.setData(bookStudents);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public int delete(ModelMap model, BookStudent bookStudent) {
        Student student = new Student();
        student.setSno(bookStudent.getSno());
        String sql = "from com.npu.library.dao.domain.Student where sno = ?";
        String[] para =  { bookStudent.getSno() };
        List<Student> students = studentDao.find(sql,para);
        if (students == null || students.size() == 0) {
            return -1;
        }

        Book book = new Book();
        book.setBno(bookStudent.getBno());
        String sql2 = "from com.npu.library.dao.domain.Book where bno = ?";
        String[] para2 =  { bookStudent.getBno() };
        List<Book> books = bookDao.find(sql2,para2);
        if (books == null || books.size() == 0) {
            return -2;
        }
        book = books.get(0);
        BookStudent bs1 = new BookStudent();
        bs1.setSid(student.getId());
        bs1.setBid(book.getId());
       /* List<BookStudent> bss1 = bookStudentDao.find(bs1);
        if (CollectionUtils.isEmpty(bss1)) {
            return -3;
        }*/

        bookStudent.setSid(student.getId());
        bookStudent.setBid(book.getId());
        bookStudentDao.delete(bookStudent);

        book.setRemain(book.getRemain() + 1);
        bookDao.update(book);


        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/findStudent", method = {RequestMethod.GET, RequestMethod.POST})
    public Student findStudent(Student student) {
    	String sql = "from com.npu.library.dao.domain.Student where sno = ?";
        String[] para =  { student.getSno() };
        List<Student> students = studentDao.find(sql,para);
        if (CollectionUtils.isEmpty(students)) {
            return null;
        } else {
            return students.get(0);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/findBook", method = {RequestMethod.GET, RequestMethod.POST})
    public Book findBook(Book book) {
    	String sql = "from com.npu.library.dao.domain.Book where bno = ?";
        String[] para =  { book.getBno() };
    	List<Book> books = bookDao.find(sql,para);
        if (CollectionUtils.isEmpty(books)) {
            return null;
        } else {
            return books.get(0);
        }
    }
}