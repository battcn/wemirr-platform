package com.battcn.bookstore.consumer.controller.book;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.book.facade.BookChapterService;
import com.battcn.book.pojo.po.BookChapter;
import com.battcn.framework.commons.entity.DataGrid;
import com.battcn.framework.commons.entity.PageResult;
import com.battcn.framework.security.annotation.IgnoreAuthenticate;
import com.battcn.search.facade.BookService;
import com.battcn.search.pojo.po.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
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
            url = "dubbo://localhost:20883", timeout = 10000)
    private BookService bookService;
    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20882", timeout = 10000)
    private BookChapterService bookChapterService;


    @IgnoreAuthenticate
    @GetMapping
    @ApiOperation(value = "根据分页条件查询图书信息")
    public PageResult<Book> query(DataGrid grid, @RequestParam(required = false) String type, @RequestParam String content) {
        final Page<Book> bookPage = this.bookService.searchBook(content);
        return PageResult.of(bookPage.getTotalElements(), bookPage.getContent());
    }

    @IgnoreAuthenticate
    @GetMapping("/{book_no}")
    @ApiOperation(value = "根据图书编号查询书籍信息")
    public Book queryBooks(@PathVariable("book_no") String bookNo) {
        return this.bookService.selectByBookNo(bookNo);
    }

    @IgnoreAuthenticate
    @GetMapping("/{book_no}/chapters")
    @ApiOperation(value = "根据图书编号查询书籍章节信息")
    public List<BookChapter> queryChaptersByBookNo(@PathVariable("book_no") String bookNo) {
        return this.bookChapterService.queryChapterForList(bookNo);
    }

    @IgnoreAuthenticate
    @GetMapping(value = "/hots")
    @ApiOperation(value = "热门")
    public List<String> queryHots() {
        return null;
    }

    @IgnoreAuthenticate
    @GetMapping(value = "/recommends")
    @ApiOperation(value = "首页推荐/与总点击榜")
    public List<Book> queryRecommends() {
        // TODO 查询 Redis 总点击榜
        //final List<Serializable> list = cacheService.getList("rankings");

        return this.bookService.selectRecommend();
    }

}
