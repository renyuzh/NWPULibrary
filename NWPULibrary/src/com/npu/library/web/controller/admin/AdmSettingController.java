package com.npu.library.web.controller.admin;

import com.npu.library.constant.Constant;
import com.npu.library.dao.BaseDAO;
import com.npu.library.dao.domain.Setting;
import com.npu.library.web.bean.PageBean;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
@RequestMapping("/admin/setting")
public class AdmSettingController {

    @Resource
    private BaseDAO<Setting> settingDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        String username = (String) request.getSession().getAttribute(Constant.user_name);
        model.addAttribute("username", username);
        return "admin/setting";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageBean<Setting> list(ModelMap model, Setting setting) {
        List<Setting> settings = settingDao.find("from com.npu.library.dao.domain.Setting");
        PageBean<Setting> data = new PageBean<Setting>();
        data.setData(settings);
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    public Setting findById(ModelMap model, Setting setting) {
    	List<Setting> settings = settingDao.find("from com.npu.library.dao.domain.Setting where id = "+setting.getId());
        if (settings == null || settings.size() == 0)
        	return null;
        return settings.get(0);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public Boolean update(ModelMap model, Setting setting) {
    	List<Setting> settings = settingDao.find("from com.npu.library.dao.domain.Setting where id = "+setting.getId());
        if (settings == null || settings.size() == 0)
        	return null;
        Setting s = settings.get(0);
        s.setValue(setting.getValue());
    	settingDao.update(s);
        return true;
    }

}