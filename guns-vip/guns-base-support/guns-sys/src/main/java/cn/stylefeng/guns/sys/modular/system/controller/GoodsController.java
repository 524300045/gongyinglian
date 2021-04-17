package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.enums.CodeExpressEnum;
import cn.stylefeng.guns.sys.modular.system.entity.Category;
import cn.stylefeng.guns.sys.modular.system.entity.Goods;
import cn.stylefeng.guns.sys.modular.system.model.params.CategoryParam;
import cn.stylefeng.guns.sys.modular.system.model.params.GoodsParam;
import cn.stylefeng.guns.sys.modular.system.model.result.CategoryResult;
import cn.stylefeng.guns.sys.modular.system.model.result.GoodsResult;
import cn.stylefeng.guns.sys.modular.system.service.CategoryService;
import cn.stylefeng.guns.sys.modular.system.service.CodeService;
import cn.stylefeng.guns.sys.modular.system.service.GoodsService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.google.common.base.Strings;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 商品表控制器
 *
 * @author zx
 * @Date 2021-03-15 17:16:02
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    private String PREFIX = "/modular/system/goods";

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CodeService codeService;

    /**
     * 跳转到主页面
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("")
    public String index(Model model) {

        CategoryParam categoryParam=new CategoryParam();
        categoryParam.setYn(1);
        List<CategoryResult> categoryResultList=categoryService.findListBySpec(categoryParam);
        categoryResultList=categoryResultList.stream().filter(p->p.getLevel().equals(1)).collect(Collectors.toList());
        model.addAttribute("categoryList", categoryResultList);


        return PREFIX + "/goods.html";
    }

    /**
     * 新增页面
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/goods_add.html";
    }

    /**
     * 编辑页面
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/goods_edit.html";
    }

    /**
     * 新增接口
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(GoodsParam goodsParam) {

        CategoryParam categoryParam=new CategoryParam();
        categoryParam.setCategoryCode(goodsParam.getTwoCategoryCode());
        Category twoCategory=categoryService.selectCategoryByCode(categoryParam);
        if (twoCategory==null)
            return ResponseData.error("查询不到分类信息");

        if (Strings.isNullOrEmpty(twoCategory.getParentCode())||twoCategory.getParentCode().equals("0"))
        {
            return ResponseData.error("只能选择末级分类");
        }

        String code=this.codeService.generateCode(
                CodeExpressEnum.skuCode, null);
        if (Strings.isNullOrEmpty(code))
        {
            return ResponseData.error("商品编码为空");
        }

        CategoryParam categoryParamTwo=new CategoryParam();
        categoryParamTwo.setCategoryCode(twoCategory.getParentCode());

        Category category=categoryService.selectCategoryByCode(categoryParamTwo);

        goodsParam.setSkuCode(code);
        goodsParam.setCategoryCode(category.getCategoryCode());
        goodsParam.setCategoryName(category.getCategoryName());
        goodsParam.setYn(1);
        goodsParam.setStatus(1);
        goodsParam.setCreateUser(LoginContextHolder.getContext().getUser().getUsername());
        goodsParam.setCreateTime(new Date());
        goodsParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        goodsParam.setUpdateTime(new Date());
        this.goodsService.add(goodsParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(GoodsParam goodsParam) {


        CategoryParam categoryParam=new CategoryParam();
        categoryParam.setCategoryCode(goodsParam.getTwoCategoryCode());
        Category twoCategory=categoryService.selectCategoryByCode(categoryParam);
        if (twoCategory==null)
            return ResponseData.error("查询不到分类信息");

        if (Strings.isNullOrEmpty(twoCategory.getParentCode())||twoCategory.getParentCode().equals("0"))
        {
            return ResponseData.error("只能选择末级分类");
        }
        CategoryParam categoryParamTwo=new CategoryParam();
        categoryParamTwo.setCategoryCode(twoCategory.getParentCode());

        Category category=categoryService.selectCategoryByCode(categoryParamTwo);

        goodsParam.setCategoryCode(category.getCategoryCode());
        goodsParam.setCategoryName(category.getCategoryName());
        goodsParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        goodsParam.setUpdateTime(new Date());
        this.goodsService.update(goodsParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(GoodsParam goodsParam) {
        goodsParam.setUpdateUser(LoginContextHolder.getContext().getUser().getUsername());
        goodsParam.setUpdateTime(new Date());
        goodsParam.setYn(0);
        this.goodsService.update(goodsParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author zx
     * @Date 2021-03-15
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(GoodsParam goodsParam) {
        Goods detail = this.goodsService.getById(goodsParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author zx
     * @Date 2021-03-15
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(GoodsParam goodsParam) {
        return this.goodsService.findPageBySpec(goodsParam);
    }


    @RequestMapping(value = "download",method = RequestMethod.POST)
    public void demoDownload(HttpServletResponse response,GoodsParam query) throws FileNotFoundException {

        // SXSSFWorkbook支持excel2010
        SXSSFWorkbook sw = new SXSSFWorkbook();
        // 表名
        SXSSFSheet sheet = sw.createSheet("成绩表");
        // 创建行 【第一行  从零开始】
        SXSSFRow row = sheet.createRow(0);
        // 创建列 【第一列  从零开始】
        SXSSFCell cell = row.createCell(0);

        //设置单元格内容
        cell.setCellValue("学员考试成绩一览表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

        // 创建样式
        CellStyle cellStyle = sw.createCellStyle();

        // 设置单元格的横向和纵向对齐方式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 给指定列设置样式
        cell.setCellStyle(cellStyle);

        SXSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("姓名");
        row1.createCell(1).setCellValue("语文");
        row1.createCell(2).setCellValue("数学");
        row1.createCell(3).setCellValue("外语");

        SXSSFRow row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("张三");
        row2.createCell(1).setCellValue("89");
        row2.createCell(2).setCellValue("84");
        row2.createCell(3).setCellValue("83");

        SXSSFRow row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("李四");
        row3.createCell(1).setCellValue("88");
        row3.createCell(2).setCellValue("89");
        row3.createCell(3).setCellValue("81");
        // 新建导出流
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\grade.xlsx");
        try
        {
            // 将文件写出
            sw.write(fileOutputStream);

            fileOutputStream.flush();

            fileOutputStream.close();

            sw.close();
        }
        catch (Exception e)
        {

        }


    }


    @RequestMapping(value = "export",method = RequestMethod.GET)
    public void export(HttpServletResponse response,GoodsParam query) {


        try {

            List<GoodsResult> dataList = goodsService.findListBySpec(query);

            String filename = "商品数据.xlsx";

            String[] titles = new String[] { "一级分类", "二级分类", "商品编码", "商品名称", "规格",
                    "单位","税率"};
            OutputStream os = null;
            try {
                os = response.getOutputStream();
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(filename.getBytes("GB2312"), "ISO8859-1") + "\"");

                SXSSFWorkbook sw = new SXSSFWorkbook();

                SXSSFSheet sheet = sw.createSheet("成绩表");

                SXSSFRow row = sheet.createRow(0);

               // SXSSFCell cell = row.createCell(0);

     /*           cell.setCellValue("学员考试成绩一览表");
                //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
                sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));*/

                row.createCell(0).setCellValue("一级分类");
                row.createCell(1).setCellValue("二级分类");
                row.createCell(2).setCellValue("商品编码");
                row.createCell(3).setCellValue("商品名称");
                row.createCell(4).setCellValue("规格");
                row.createCell(5).setCellValue("单位");
                row.createCell(6).setCellValue("税率");



                if (!org.springframework.util.CollectionUtils.isEmpty(dataList)) {

                     int i=1;
                    for (GoodsResult goods : dataList) {

                        SXSSFRow row2 = sheet.createRow(i);
                        row2.createCell(0).setCellValue(goods.getCategoryName());
                        row2.createCell(1).setCellValue(goods.getTwoCategoryName());
                        row2.createCell(2).setCellValue(goods.getSkuCode());
                        row2.createCell(3).setCellValue(goods.getGoodsName());
                        row2.createCell(4).setCellValue(goods.getGoodsModel());
                        row2.createCell(5).setCellValue(goods.getUnitName());
                        row2.createCell(6).setCellValue(goods.getTaxRate().toString());
                        i=i+1;
                    }
                }

                sw.write(os);
                os.flush();
                sw.close();
            } catch (Exception e) {

                System.out.println(e.getMessage());
            } finally {
                try {

                    if (os != null) {
                        os.close();
                    }
                ;
                } catch (IOException e) {

                }
            }

        } catch (Exception e) {

        }
    }

}


