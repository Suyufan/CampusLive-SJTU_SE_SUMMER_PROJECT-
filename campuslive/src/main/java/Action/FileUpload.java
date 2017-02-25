package Action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by 昱凡 on 2016/7/13.
 */
public class FileUpload extends ActionSupport {
    private String name;
    //封装上传文件域的属性
    private File file;
    //封装上传文件类型的属性
    private String fileContentType;
    //封装上传文件名的属性
    private String fileFileName;
    //提示信息
    private String json_message;

    private String JSON_MESSAGE="json_message";

    public String getImagInfo() throws Exception {
        try {
            //设置文件保存目录
            String saveDir=getRootPath()+"/images";
            System.out.println(saveDir);
            File saveFile=new File(saveDir);
            //判断
            if(!saveFile.exists()){
                saveFile.mkdirs();
            }
            //时间戳
            String timeStr="campus"+System.currentTimeMillis();
            //获取文件后缀
            String fileSuffix=fileFileName.substring(fileFileName.indexOf("."));
            //拼接文件名
            String fileName=timeStr+fileSuffix;
            //创建文件输出流对象
            OutputStream fos=new FileOutputStream(saveDir+"/"+fileName);
            //创建文件输入流对象
            InputStream fis=new FileInputStream(file);
            //创建缓冲数组
            byte[] buffer=new byte[1024];
            int len=0;
            //从输入流中将数据写入到输出流中
            while((len=fis.read(buffer))!=-1){
                //写入
                fos.write(buffer,0,len);
            }
            //关闭资源
            fis.close();
            fos.close();
            //上传成功地址
            String successPath = "http://127.0.0.1:8080/campus" + "/images/"+fileName;
            System.out.println(successPath);

            json_message = "{\"result\":1,\"message\":\""+successPath+"\"}";

            HttpServletResponse response=ServletActionContext.getResponse();
            //设置响应的内容类型
            response.setContentType("text/html; charset=utf-8");
            //获取输出流
            PrintWriter pw = response.getWriter();
            pw.write(successPath);
            pw.flush();
            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
            json_message="{\"result\":0}";
        }

        System.out.println(json_message);
        return JSON_MESSAGE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getJson_message() {
        return json_message;
    }

    public void setJson_message(String json_message) {
        this.json_message = json_message;
    }

    public String getJSON_MESSAGE() {
        return JSON_MESSAGE;
    }

    public void setJSON_MESSAGE(String JSON_MESSAGE) {
        this.JSON_MESSAGE = JSON_MESSAGE;
    }

    /**
     * 返回服务器http地址
     * @return
     */
    private String getUrl(){
        HttpServletRequest request= ServletActionContext.getRequest();

        return "http://"+request.getRemoteHost()+request.getContextPath();
    }

    /**
     * 获取项目根目录
     * @return
     */
    private String getRootPath(){
        String filePath = Thread.currentThread().getContextClassLoader()
                .getResource("").toString();
        if (filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }
        if (filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }
        if (filePath.toLowerCase().indexOf("web-inf") > -1) {
            filePath = filePath.substring(0, filePath.length() - 9);
        }
        if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }

        if (filePath.endsWith("/"))
            filePath = filePath.substring(0, filePath.length() - 1);

        return filePath;
    }

}
