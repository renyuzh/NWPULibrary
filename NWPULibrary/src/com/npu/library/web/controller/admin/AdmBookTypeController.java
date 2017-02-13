package com.npu.library.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.npu.library.constant.Constant;
import com.npu.library.dao.BaseDAO;
import com.npu.library.dao.domain.BookType;
import com.npu.library.web.bean.PageBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/bookType")
public class AdmBookTypeController {

    @Resource
    private BaseDAO<BookType> bookTypeDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "admin/book-type";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<BookType> list(ModelMap model, BookType bookType) {
 
        List<BookType> bookTypes = bookTypeDao.find("from com.npu.library.dao.domain.BookType");
        PageBean<BookType> data = new PageBean<BookType>();
        data.setData(bookTypes);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    public BookType findById(ModelMap model, BookType bookType) {
        List<BookType> bookTypes = bookTypeDao.find("from com.npu.library.dao.domain.BookType where id = "+bookType.getId() );
        if (CollectionUtils.isEmpty(bookTypes)) {
            return null;
        } else {
            return bookTypes.get(0);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public int save(ModelMap model, BookType bookType) throws ParseException {
       	String sql = "from com.npu.library.dao.domain.BookType where tname = ?";
        String[] para =  { bookType.getTname()};
    	
    	List<BookType> bookTypes = bookTypeDao.find(sql,para );
        if (!CollectionUtils.isEmpty(bookTypes)) {
            return -1;
        }
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Date cdate = d.parse(d.format(new Date()));
        bookType.setCdate(cdate);
        bookTypeDao.save(bookType);
        return  1;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public int update(ModelMap model, BookType bookType) {
        BookType bt = new BookType();
        bt.setTname(bookType.getTname());
       	String sql = "from com.npu.library.dao.domain.BookType where tname = ?";
        String[] para =  { bookType.getTname()};
    	
        List<BookType> bookTypes = bookTypeDao.find(sql,para);
        if (!CollectionUtils.isEmpty(bookTypes)) {
            if (bookType.getId() != bookTypes.get(0).getId()) {
                return -1;
            }
        }
        bookTypeDao.update(bookType);
        return  1 ;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean delete(ModelMap model, BookType bookType) {
        		bookTypeDao.delete(bookType);
                return  true;
    }


}