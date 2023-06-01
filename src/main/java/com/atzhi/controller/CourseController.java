package com.atzhi.controller;

import com.atzhi.pojo.Course;
import com.atzhi.pojo.Feedback;
import com.atzhi.pojo.User;
import com.atzhi.service.CourseService;
import com.atzhi.service.FeedbackService;
import com.atzhi.service.SchoolService;
import com.atzhi.pojo.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private Result result;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private FeedbackService feedbackService;

    //返回主页面
    @GetMapping("/main_page")
    public String main_page(HttpSession session)
    {
        //防止非法进入
        if(session.getAttribute("user")==null)
        {
            return "redirect:/user/login_page";
        }else{
            User user = (User) session.getAttribute("user");
            System.out.println(user);
            return "main";
        }
    }
    //新增课程页面
    @GetMapping("add_page")
    public String add_page(HttpSession session)
    {
        if(session.getAttribute("user")==null)
        {
            return "redirect:/user/login_page";
        }else{
            User user = (User) session.getAttribute("user");
            System.out.println(user);
            return "add";
        }
    }
    //修改课程页面
    @GetMapping("update_page")
    public String update_page(Integer id,HttpSession session)
    {
        session.removeAttribute("course");
        if(session.getAttribute("user")==null)
        {
            return "redirect:/user/login_page";
        }else{
            Course course=courseService.selectById(id);
            System.out.println(course);
            //System.out.println("1111111111111111111111111111111111111");
            session.setAttribute("course", course);
            return "update";
        }
    }


    //菜单界面
    @GetMapping("/menu_page")
    public String menu_page(){return "menu";}

    @GetMapping("/statistics_page")
    public String statistics_page() {
        return "statistics";
    }
    @GetMapping("/exportToExcel_page")
    public String exportToExcel_page() {
        return "exportToExcel";
    }

    @GetMapping("/about_page")
    public String about_page() {
        return "about";
    }
    @GetMapping("/chat_page")
    public String chat_page() {
        return "chat";
    }


    //获取课程的信息
    @GetMapping("/get_course")
    @ResponseBody
    public Course get_course(HttpSession session)
    {
        Course course = (Course) session.getAttribute("course");
        System.out.println(session.getAttribute("course")+"--------------");
//        session.removeAttribute("course");
//        System.out.println(session.getAttribute("course")+"--------------");
        return course;
    }


//   ------------------------------------------------分割线---------------------------------------------------------------------
    //返回课程表数据
    @GetMapping("/main_solve")
    @ResponseBody
    public Result main_solve()
    {
        List<Course>courses=courseService.selectAll();
        List<Map<String, Object>> discourse = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> courseMap = new HashMap<>();
            courseMap.put("id",course.getId());
            courseMap.put("image",course.getImage());
            courseMap.put("name",course.getName());
            courseMap.put("hours",course.getHours());
            courseMap.put("schools",schoolService.selectSchoolNameById(course.getSchools()).getSchoolName());
            discourse.add(courseMap);
        }
        System.out.println(discourse);
        result.setMsg("success");
        result.setCode(200);
        result.setData(discourse);
        return result;
    }


//


    //柱状图设计
//    @GetMapping("/highcharts_solve")
//    @ResponseBody
//    public String getHighchartsData() {
//        // 准备数据
//        Map<String, Object> data = new HashMap<>();
//        List<Map<String, Object>> series = new ArrayList<>();
//        List<String> categories = new ArrayList<>();
//
//        // 填充数据
//        List<Course> courses = courseService.selectAll();
//        Map<String, Object> lengthSeries = new HashMap<>();
//        lengthSeries.put("name", "Length");
//        lengthSeries.put("data", courses.stream().map(Course::getHours).collect(Collectors.toList()));
//        series.add(lengthSeries);
//        for (Course c : courses) {
//            categories.add(c.getName());
//        }
//
//        // 封装数据并转换成JSON格式
//        data.put("series", series);
//        data.put("categories", categories);
//        ObjectMapper mapper = new ObjectMapper();
//        String json = "";
//        try {
//            json = mapper.writeValueAsString(data);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(json);
//        // 返回JSON数据
//        return json;
//    }
    @GetMapping("/highcharts_solve")
    @ResponseBody
    public Result highcharts_solve()
    {
        System.out.println("-------------------------highcharts_solve-------------------------------------------");
        List<Course>courses=courseService.selectAll();
        List<Map<String, Object>> discourse = new ArrayList<>();
        for (Course course : courses) {
            Map<String, Object> courseMap = new HashMap<>();
            courseMap.put("name",course.getName());
            courseMap.put("hours",course.getHours());
            discourse.add(courseMap);
        }
        System.out.println(discourse);
        result.setMsg("success");
        result.setCode(200);
        result.setData(discourse);
        return result;
    }

    //导出至Excel表格中
    @GetMapping("/exportToExcel_solve")
    public Result exportToExcel() throws IOException {
        System.out.println("----------------------exportToExcel_solve--------------------");
        // 准备数据
        List<Course> courses = courseService.selectAll();

        // 创建工作簿和工作表
        Workbook workbook = WorkbookFactory.create(true); // true表示创建一个新的工作簿
        Sheet sheet = workbook.createSheet("Course Data");

        // 设置标题行的格式
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        headerStyle.setFont(headerFont);

        // 创建标题行
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("课程名");
        headerRow.createCell(2).setCellValue("课时");
        headerRow.getCell(0).setCellStyle(headerStyle);
        headerRow.getCell(1).setCellStyle(headerStyle);
        headerRow.getCell(2).setCellStyle(headerStyle);

        // 填充数据行
        CreationHelper creationHelper = workbook.getCreationHelper();
        CellStyle dataStyle = workbook.createCellStyle();
//        dataStyle.setDataFormat(creationHelper.createDataFormat().getFormat("0.00")); // 格式化为两位小数
        for (int i = 0; i < courses.size(); i++) {
            Row dataRow = sheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(courses.get(i).getId());
            dataRow.createCell(1).setCellValue(courses.get(i).getName());
            dataRow.createCell(2).setCellValue(courses.get(i).getHours());
            dataRow.getCell(2).setCellStyle(dataStyle);
        }
        System.out.println(courses);
        // 调整列宽（可选）
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

        // 将工作簿写入文件
        File file = new File("D:\\develop\\JAVA\\javaproject\\ssmTest3\\src\\main\\webapp\\xlsx\\courses.xlsx"); // 文件名可以自定义
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        //System.out.println(workbook);
        workbook.close();
        outputStream.close();
        System.out.println("----------------------exportToExcel_solve_OVER--------------------");
        return result;
    }

    //查询数据
    @PostMapping("/select_solve")
    @ResponseBody
    public Result select_solve(@RequestBody Course course)
    {
        System.out.println("-----------------------------select_solve-------------------------------------");
        List<Course>courses=courseService.selectByCondition(course);
        List<Map<String, Object>> discourse = new ArrayList<>();
        for (Course res : courses) {
            Map<String, Object> courseMap = new HashMap<>();
            courseMap.put("id",res.getId());
            courseMap.put("image",res.getImage());
            courseMap.put("name",res.getName());
            courseMap.put("hours",res.getHours());
            courseMap.put("schools",schoolService.selectSchoolNameById(res.getSchools()).getSchoolName());
            discourse.add(courseMap);
        }
        System.out.println(discourse);
        result.setMsg("success");
        result.setCode(200);
        result.setData(discourse);
        return result;
    }
    //删除课程
    @PostMapping("/delete_solve")
    @ResponseBody
    public String delete_solve(@RequestParam("id") int id)
    {
        System.out.println(id);
        courseService.deleteById(id);
        return "success";
    }
    //添加课程
    @PostMapping("/add_solve")
    @ResponseBody
    public Result add_solve(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam("name") String name,
                            @RequestParam("hours") Integer hours, @RequestParam("schools") Integer schools)
    {
        List<Course> courses=courseService.selectAll();
        Boolean success =true;
        for(Course res :courses)
        {
            if(res.getName().equals(name))
            {
                success=false;
                break;
            }
        }
        if(success.equals(true)) {
            try {
                String imageName=null;
                //是否添加课程图片
                if (ObjectUtils.isEmpty(imageFile) || imageFile.getSize() <= 0) {
                    imageName = "ce8860e1-71e9-426e-8d0a-2b7b1bde9a24.jpg";//默认图片
                    System.out.println(imageName+"是null");
                }else{
                    // 判断上传的文件是否为图片类型
                    if (!imageFile.getContentType().startsWith("image/")) {
                        result.setMsg("imageFail");
                        return result;
                    }else{
                        imageName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
                        // 生成图片文件名
                        String imagePath = "D:\\develop\\JAVA\\javaproject\\ssmTest3\\src\\main\\webapp\\image\\" + imageName;
                        // 将图片保存到磁盘
                        Path imageFilePath = Paths.get(imagePath);
                        Files.write(imageFilePath, imageFile.getBytes());
                        System.out.println(imageName+"不是null");
                    }
                }
                Course course =new Course(null,imageName,name,hours,schools);
                //执行插入操作，按照升序产生id
                courseService.insertAutoId(course);
                System.out.println("{'module':'course save success'}");
                result.setMsg("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("{'module':'course save fail'}");
            result.setMsg("error");
        }
        result.setCode(200);
        return result;
    }
    //修改课程
    @PostMapping ("/update_solve")
    @ResponseBody
    public Result update_solve(@RequestParam("id") Integer id,@RequestParam("imageFile") MultipartFile imageFile,
                               @RequestParam("name") String name, @RequestParam("hours") Integer hours,
                               @RequestParam("schools") Integer schools
    ){
        System.out.println("---------------------------update------------------------------------");
        List<Course> courses=courseService.selectAll();
        Boolean success =true;
        for(Course res :courses)
        {
            if(!res.getId().equals(id)&&res.getName().equals(name))
            {
                success=false;
                break;
            }
        }
        if(success.equals(true)) {
            try {
                String imageName=courseService.selectImage(id);
                //是否修改课程图片
                if (!ObjectUtils.isEmpty(imageFile) && imageFile.getSize() > 0) {
                    // 判断上传的文件是否为图片类型
                    if (!imageFile.getContentType().startsWith("image/")) {
                        result.setMsg("imageFail");
                        result.setCode(200);
                        return result;
                    }else{
                        imageName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
                        // 生成图片文件名
                        String imagePath = "D:\\develop\\JAVA\\javaproject\\ssmTest3\\src\\main\\webapp\\image\\" + imageName;
                        // 将图片保存到磁盘
                        Path imageFilePath = Paths.get(imagePath);
                        Files.write(imageFilePath, imageFile.getBytes());
                        System.out.println(imageName+"不是null");
                    }
                }
                Course course =new Course(id,imageName,name,hours,schools);
                System.out.println(course+"--------------------update");
                courseService.update(course);
                System.out.println(course+"{'module':'course update success'}");
                result.setMsg("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            result.setMsg("nameFail");
            System.out.println("{'module':'course update fail'}");
        }
        result.setCode(200);

        return result;
    }
}
//b4ab724d-7806-4dfd-9d0c-88b7f579c198.png
