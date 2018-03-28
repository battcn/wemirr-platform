package com.battcn.bookstore.consumer.controller.book;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.book.facade.BookChapterService;
import com.battcn.book.facade.BookService;
import com.battcn.book.pojo.po.Book;
import com.battcn.book.pojo.po.BookChapter;
import com.battcn.book.pojo.po.BookType;
import com.battcn.framework.mybatis.pojo.DataGrid;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Levin
 */
@RestController
@RequestMapping(value = "/app/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "图书管理", description = "图书管理", tags = "2.0")
public class BookController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 10000)
    private BookService bookService;
    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 10000)
    private BookChapterService bookChapterService;



    @GetMapping
    @ApiOperation(value = "根据分页条件查询图书信息")
    public PageInfo<Book> query(DataGrid grid, @RequestParam(required = false) String type) {
        Book record = new Book();
        record.setType(type);
        return this.bookService.listForDataGrid(grid, record);
    }

    @GetMapping("/{book_no}")
    @ApiOperation(value = "根据图书编号查询书籍信息")
    public Book queryBooks(@PathVariable("book_no") String bookNo) {
        return this.bookService.selectById(bookNo);
    }

    @GetMapping("/{book_no}/chapters")
    @ApiOperation(value = "根据图书编号查询书籍章节信息")
    public List<BookChapter> queryChaptersByBookNo(@PathVariable("book_no") String bookNo) {
        return this.bookChapterService.queryChapterForList(bookNo);
    }

    @GetMapping("/types")
    @ApiOperation(value = "查询书籍分类")
    public List<BookType> queryBookTypeByGroup() {
        return this.bookService.queryBookTypeForList();
    }


    @GetMapping(value = "/hots")
    @ApiOperation(value = "热门")
    public List<String> queryHots() {
        return null;
    }

    @GetMapping(value = "/recommends")
    @ApiOperation(value = "首页推荐/与总点击榜")
    public PageInfo<Book> queryRecommends() {
        // TODO 查询 Redis 总点击榜
        //final List<Serializable> list = cacheService.getList("rankings");

        DataGrid grid = new DataGrid();
        grid.setPageSize(10);
        grid.setSort("gmt_modified");
        Book record = new Book();
        record.setRecommend(true);
        // TODO 查询推荐的数据



        // TODO 组装数据报文
        return this.bookService.listForDataGrid(grid, record);
    }

}
