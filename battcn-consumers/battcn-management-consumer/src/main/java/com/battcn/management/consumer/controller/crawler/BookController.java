package com.battcn.management.consumer.controller.crawler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.book.facade.BookService;
import com.battcn.book.pojo.po.Book;
import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.framework.webmagic.downloader.CrawlerDownloader;
import com.battcn.management.consumer.annotation.BattcnLog;
import com.battcn.management.consumer.controller.BaseController;
import com.battcn.management.consumer.util.ApiResult;
import com.battcn.management.consumer.webmagic.pageprocessor.BookProcessor;
import com.battcn.management.consumer.webmagic.pipeline.BookPipeline;
import com.battcn.system.facade.ProxyPoolService;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;
import us.codecraft.webmagic.Spider;

import java.util.Optional;
import java.util.concurrent.Executors;

import static com.battcn.framework.exception.CustomException.notFound;


/**
 * @author Levin
 */
@Controller
@RequestMapping("/crawler/books")
@Api(value = "资源管理")
@ApiIgnore
public class BookController extends BaseController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private BookService bookService;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880")
    private ProxyPoolService proxyPoolService;

    private final BookPipeline bookPipeline;

    @Autowired
    public BookController(BookPipeline bookPipeline) {
        this.bookPipeline = bookPipeline;
    }


    @GetMapping("/list")
    public String list() {
        return "crawler/book/list";
    }

    @GetMapping(value = "/query")
    @ResponseBody
    @ApiOperation(value = "根据分页条件查询图书信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功"), @ApiResponse(code = 404, message = "地址错误"),
            @ApiResponse(code = 500, message = "系统错误,请联系管理人员")})
    public PageInfo<Book> query(DataGrid grid) {
        return this.bookService.listForDataGrid(grid);
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id) {
        if (id != null) {
            request.setAttribute("dto", Optional.ofNullable(this.bookService.selectById(id)).orElseThrow(() -> notFound("该数据已失效")));
        }
        return "crawler/book/edit";
    }

    @BattcnLog(module = "资源管理", method = "保存图书", description = "添加/修改书籍信息")
    @PostMapping(value = "/save")
    @ResponseBody
    public ApiResult<String> save(Book book) {
        return ApiResult.SUCCESS;
    }

    @BattcnLog(module = "资源管理", method = "移除图书", description = "删除菜单信息")
    @PostMapping(value = "/remove")
    @ResponseBody
    public ApiResult<String> del(Long[] ids) {
        Lists.newArrayList(ids).forEach(this.bookService::deleteById);
        return ApiResult.SUCCESS;
    }

    @GetMapping("/crawler")
    @ResponseBody
    public ApiResult<String> crawler() {
        Executors.newSingleThreadExecutor().execute(() -> Spider.create(new BookProcessor()).setDownloader(new CrawlerDownloader()).addUrl("http://www.biquge5200.com/0_844").addPipeline(bookPipeline).thread(5).run());
        return ApiResult.getSuccess("已进入后台处理,先干点别的吧");
    }


}
