package com.npu.library.web.controller.admin;

import com.npu.library.constant.Constant;
import com.npu.library.dao.BaseDAO;
import com.npu.library.dao.domain.Book;
import com.npu.library.dao.domain.BookStudent;
import com.npu.library.dao.domain.Setting;
import com.npu.library.dao.domain.Student;
import com.npu.library.util.DateUtils;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/borrow")
public class AdmBorrowController {

    @Resource
    private BaseDAO<BookStudent> bookStudentDao;

    @Resource
    private BaseDAO<Student> studentDao;

    @Resource
    private BaseDAO<Book> bookDao;

    @Resource
    private BaseDAO<Setting> settingDao;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "admin/borrow";
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
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public int save(ModelMap model, BookStudent bookStudent) {

        Student student = new Student();
        student.setSno(bookStudent.getSno());
        String sql = "from com.npu.library.dao.domain.Student where sno = ? ";
        String[] para =  { bookStudent.getSno()};
        List<Student> students = studentDao.find(sql,para);
        if (students == null || students.size() == 0) {
            return -1;
        }
        student = students.get(0);
        Book book = new Book();
        book.setBno(bookStudent.getBno());
        String sql2 = "from com.npu.library.dao.domain.Book where bno = ? ";
        String[] para2 =  { bookStudent.getBno()};
        List<Book> books = bookDao.find(sql2,para2);
        if (book == null|| books.size() == 0){
            return -2;
        }
        book = books.get(0);
        if (book.getRemain() <= 0) {
            return -3;
        }

        BookStudent bs1 = new BookStudent();
        bs1.setSid(student.getId());
        bs1.setBid(book.getId());
        /*List<BookStudent> bss1 = bookStudentDao.find("from com.npu.library.dao.domain.BookStudent where id = " + bs1.getId());
        if (!CollectionUtils.isEmpty(bss1)) {
            return -4;
        }*/


        Setting setting1 = new Setting();
        setting1.setId(Constant.keep_book_max_amount_id);
        setting1 = settingDao.find("from com.npu.library.dao.domain.Setting where id = " + setting1.getId()).get(0);

        BookStudent bs2 = new BookStudent();
        bs2.setSid(student.getId());
        List<BookStudent> bss2 = bookStudentDao.find("from com.npu.library.dao.domain.BookStudent where sid ="+bs2.getId());
        if (!CollectionUtils.isEmpty(bss2) && bss2.size() > setting1.getValue()) {
            return -5;
        }

        Setting setting2 = new Setting();
        setting2.setId(Constant.keep_book_max_days_id);
        setting2 = settingDao.find("from com.npu.library.dao.domain.Setting where id =" + setting2.getId()).get(0);

        bookStudent.setSid(student.getId());
        bookStudent.setBid(book.getId());
        bookStudent.setBdate(new Date());
        bookStudent.setRdate(DateUtils.addDay(new Date(), setting2.getValue()));
        bookStudent.setAuthor(book.getAuthor());
        bookStudent.setBname(book.getBname());
        bookStudent.setBno(book.getBno());
        bookStudent.setPrice(book.getPrice());
        bookStudent.setSname(student.getSname());
        bookStudentDao.save(bookStudent);


        book.setRemain(book.getRemain() - 1);
        bookDao.update(book);
        return 1;
    }

}