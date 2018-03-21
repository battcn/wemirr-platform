package com.battcn.management.consumer.controller.crawler;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.book.facade.BookChapterService;
import com.battcn.book.facade.BookService;
import com.battcn.book.pojo.po.Book;
import com.battcn.book.pojo.po.BookChapter;
import com.battcn.framework.exception.CustomException;
import com.battcn.framework.mybatis.pojo.DataGrid;
import com.battcn.framework.webmagic.downloader.CrawlerDownloader;
import com.battcn.framework.webmagic.downloader.CrowProxyProvider;
import com.battcn.management.consumer.annotation.BattcnLog;
import com.battcn.management.consumer.controller.BaseController;
import com.battcn.management.consumer.util.ApiResult;
import com.battcn.management.consumer.webmagic.pageprocessor.BookProcessor;
import com.battcn.management.consumer.webmagic.pipeline.BookPipeline;
import com.battcn.system.facade.ProxyPoolService;
import com.battcn.system.pojo.po.ProxyPool;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.proxy.Proxy;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static com.battcn.framework.exception.CustomException.notFound;
import static java.util.stream.Collectors.toList;


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
            url = "dubbo://localhost:20880", timeout = 10000)
    private BookService bookService;

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 10000)
    private BookChapterService bookChapterService;

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
        CrawlerDownloader crawlerDownloader = new CrawlerDownloader();
        final List<ProxyPool> proxyPools = this.proxyPoolService.listAll();
        if (CollectionUtils.isNotEmpty(proxyPools)) {
            List<Proxy> proxies = proxyPools.stream().map(proxy -> new Proxy(proxy.getHost(), proxy.getPort(), null, null)).collect(toList());
            crawlerDownloader.setProxyProvider(new CrowProxyProvider(proxies));
        }
        Executors.newSingleThreadExecutor().execute(() -> Spider.create(new BookProcessor()).setDownloader(crawlerDownloader).addUrl(BookProcessor.ALL_BOOK_LINK).addPipeline(bookPipeline).thread(30).run());
        return ApiResult.getSuccess("已进入后台处理,先干点别的吧");
    }

    @GetMapping("/chapter/list")
    @ApiOperation(value = "根据图书编号查询书籍章节信息")
    public String chapters(@RequestParam("book_no") String bookNo) {
        request.setAttribute("bookNo", bookNo);
        return "crawler/chapter/list";
    }

    @GetMapping("/{book_no}/chapters")
    @ResponseBody
    @ApiOperation(value = "根据图书编号查询书籍章节信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageInfo<BookChapter> query(@PathVariable("book_no") String bookNo, DataGrid grid) {
        BookChapter record = new BookChapter();
        record.setBookNo(bookNo);
        return this.bookChapterService.listForDataGrid(grid, record);
    }

    @ResponseBody
    @RequestMapping("/{book_no}/generate")
    public ApiResult<String> generateTemplate(@PathVariable("book_no") String bookNo) {
        BookChapter record = new BookChapter();
        record.setBookNo(bookNo);
        final List<BookChapter> chapters = Optional.ofNullable(bookChapterService.select(record)).orElseThrow(() -> CustomException.badRequest("未检索到章节信息"));
        final List<String> sources = chapters.stream().map(BookChapter::getSource).collect(toList());
        String[] array = sources.toArray(new String[0]);
        Spider.create(new BookProcessor()).addUrl(array).setDownloader(new CrawlerDownloader()).addPipeline(bookPipeline).thread(20).run();
        return ApiResult.SUCCESS;
    }

}
