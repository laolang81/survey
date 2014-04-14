package com.sniper.survey.struts2.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xml.sax.InputSource;

import sun.awt.image.ByteArrayImageSource;

import com.sniper.survey.model.WebUser;
import com.sniper.survey.service.impl.WebUserService;

/**
 * 数据导出
 * @author laolang
 *
 */
//加注解
@Controller
@Scope("prototype")
public class CollectionWebUserAction extends BaseAction<WebUser> {

	private static final long serialVersionUID = 1L;

	@Resource
	private WebUserService webUserService;

	private Map<String, List<WebUser>> result = new HashMap<>();
	
	public InputStream is;
	
	public Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@JSON(serialize = false)
	public Map<String, List<WebUser>> getResult() {
		return result;
	}

	public void setResult(Map<String, List<WebUser>> result) {
		this.result = result;
	}

	/**
	 * 用户列表
	 * 
	 * @return
	 */
	public String list() {

		List<WebUser> users = webUserService.getUserList();
		System.out.println(users);
		return SUCCESS;
	}
	
	public InputStream getIs()
	{
		try {
			
			List<WebUser> list = webUserService.findEntityByHQL("from WebUser");
			HSSFWorkbook wb = new HSSFWorkbook();
			
			HSSFSheet sheet = wb.createSheet("搜集调查");
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
			WebUser user = null;
			for(int i = 0;i < list.size(); i++){
				user = list.get(i);
				cell = row.createCell(i);
				cell.setCellValue(user.getNickName());
			}
			//输出
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

}