package com.nuesoft.Controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuesoft.Service.BookInfoService;
import com.nuesoft.Service.BookTypeService;
import com.nuesoft.Service.Impl.BookInfoServiceImpl;
import com.nuesoft.po.BookInfo;
import com.nuesoft.po.BookType;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@SessionAttributes({})
public class BookInfoController implements ServletContextAware {
    private ServletContext servletContext;
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }
    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private BookTypeService bookTypeService;
    @RequestMapping("toadd.do")
    public String toAdd(ModelMap map){
        List<BookType> bookTypes = bookTypeService.selectAll();
        map.put("bookTypes",bookTypes);
        return "add";
    }
    @RequestMapping("add.do")
    public void add(BookInfo bookInfo,HttpServletResponse response) throws IOException {
        int num = bookInfoService.insertSelective(bookInfo);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter=response.getWriter();
        if(num>0){
            printWriter.write("<script type='text/javascript'>alert('新增成功');location.href='querybook.do'</script>");
        }else{
            printWriter.write("<script type='text/javascript'>alert('新增失败');location.href='add.do'</script>");
        }
    }
    @RequestMapping("deletebyId.do")
    public void deleteById(int bookId,HttpServletResponse response) throws IOException {
        int num = bookInfoService.deleteByPrimaryKey(bookId);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter=response.getWriter();
        if(num>0){
            printWriter.write("<script type='text/javascript'>alert('删除成功');location.href='querybook.do'</script>");
        }else{
            printWriter.write("<script type='text/javascript'>alert('删除失败');location.href='querybook.do'</script>");
        }
    }
    @RequestMapping("deletebyIds.do")
    public void deleteByIds(String[] bookid,HttpServletResponse response) throws IOException {
        int num = bookInfoService.deleteByIds(bookid);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter=response.getWriter();
        if(num>0){
            printWriter.write("<script type='text/javascript'>alert('删除成功');location.href='querybook.do'</script>");
        }else{
            printWriter.write("<script type='text/javascript'>alert('删除失败');location.href='querybook.do'</script>");
        }
    }
    @RequestMapping("updateBookInfo.do")
    public void updateBookInfo(BookInfo bookInfo,HttpServletResponse response) throws IOException {
        int num = bookInfoService.updateByPrimaryKeySelective(bookInfo);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter=response.getWriter();
        if(num>0){
            printWriter.write("<script type='text/javascript'>alert('修改成功');location.href='querybook.do'</script>");
        }else{
            printWriter.write("<script type='text/javascript'>alert('修改失败');location.href='querybook.do'</script>");
        }
    }
    @RequestMapping("toupdate.do")
    public String toupdate(int bookId,ModelMap map){
        BookInfo bookInfo = bookInfoService.selectByPrimaryKey(bookId);
        List<BookType> bookTypes = bookTypeService.selectAll();
        map.put("bookInfo",bookInfo);
        map.put("bookTypes",bookTypes);
        return "update";
    }
    @RequestMapping("detailed.do")
    public String detailed(int bookId,ModelMap map){
        BookInfo bookInfo = bookInfoService.selectByPrimaryKey(bookId);
        List<BookType> bookTypes = bookTypeService.selectAll();
        map.put("bookInfo",bookInfo);
        map.put("bookTypes",bookTypes);
        return "detailed";
    }
    @RequestMapping("querybook.do")
    public String querybook( @RequestParam(value="bookTypeid",required=false,defaultValue="0") Integer bookTypeid,
                             @RequestParam(value="bookname",required=false,defaultValue="")String bookname,
                             @RequestParam(value="isborrow",required=false,defaultValue="-1") Integer borrow,
                             @RequestParam(value="now",required=false,defaultValue ="1")Integer now, ModelMap map){
        PageHelper.startPage(now,5);
        List<BookInfo> bookInfos = bookInfoService.selectAllBook(bookTypeid, bookname, borrow);
        PageInfo<BookInfo> pageInfo=new PageInfo<>(bookInfos);
        map.put("pageInfo",pageInfo);
        List<BookType> bookTypes = bookTypeService.selectAll();
        map.put("bookTypes", bookTypes);
        //将条件添加到map 作为回显
        map.put("bookTypeid", bookTypeid);
        map.put("bookname", bookname);
        map.put("borrow", borrow);
        return "index";
    }

    @RequestMapping(value = "imageupload.do",produces = "text/html;charset=UTF-8")
    @ResponseBody
   public String imageUpload(@RequestParam("fileImage") CommonsMultipartFile fileImage) throws UnsupportedEncodingException {
        //获取上传图片位置
        String path=servletContext.getRealPath("/resource/upload/");
        //创建文件名称
        String name=fileImage.getOriginalFilename();
        System.out.println(name);
       /* String name=new
                String(filename.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题*/
        //创建file对象写入
        File file =new File(path,name);
        try {
            fileImage.getFileItem().write(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将上传的图片路径json的方式返回客户端
        String imagePath="resource/upload/"+name;
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("imagePath",imagePath);
        //将对象转为json格式
        String json=jsonObject.toJSONString();
        System.out.println(json);
        return json;
   }
    @RequestMapping("download.do")
    public ResponseEntity<byte[]> download(String bookPash) throws IOException {
        String path = servletContext.getRealPath(bookPash);
        String[] split = path.split("\\\\");
        String name=split[split.length-1];
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        String fileName=new
                String(name.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}
