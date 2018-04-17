package com.battcn.bookstore.consumer.controller.book;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.battcn.book.facade.BookChapterService;
import com.battcn.book.pojo.po.BookChapter;
import com.battcn.bookstore.consumer.enums.BookRecommendEnum;
import com.battcn.bookstore.consumer.enums.BookRedisEnum;
import com.battcn.framework.commons.entity.DataGrid;
import com.battcn.framework.commons.entity.PageResult;
import com.battcn.framework.security.annotation.IgnoreAuthenticate;
import com.battcn.search.facade.BookService;
import com.battcn.search.pojo.po.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author Levin
 */
@RestController
@RequestMapping(value = "/app/books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "图书管理", description = "图书管理", tags = "2.0")
public class BookController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "${dubbo.registry.address}")
    private BookService bookService;
    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "${dubbo.registry.address}")
    private BookChapterService bookChapterService;

    private final RedisTemplate<String, String> redisCacheTemplate;

    @Autowired
    public BookController(RedisTemplate<String, String> redisCacheTemplate) {
        this.redisCacheTemplate = redisCacheTemplate;
    }


    @IgnoreAuthenticate
    @GetMapping
    @ApiOperation(value = "根据分页条件查询图书信息")
    public PageResult<Book> query(DataGrid grid, @RequestParam(required = false) String type, @RequestParam String content) {
        return this.bookService.searchBook(content);
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
    @PatchMapping("/{book_no}/click")
    @ApiOperation(value = "图书点击量")
    public void bookClick(@PathVariable("book_no") String bookNo) {
        redisCacheTemplate.opsForZSet().incrementScore(BookRedisEnum.BOOKS_CLICK_RANKINGS.getKey(), bookNo, 1);
    }

    @IgnoreAuthenticate
    @GetMapping(value = "/hots")
    @ApiOperation(value = "热门推荐")
    public List<Book> queryHots() {
        return this.bookService.findByRecommendOrderByLastModifiedTime((byte) BookRecommendEnum.TWO.getType());
    }

    @IgnoreAuthenticate
    @GetMapping(value = "/recommends")
    @ApiOperation(value = "首页推荐/与总点击榜")
    public JSONObject queryRecommends() {
        JSONObject result = new JSONObject();
        // TODO 查询 Redis 总点击榜
        final Set<String> bookClickRankings = redisCacheTemplate.opsForZSet().reverseRange(BookRedisEnum.BOOKS_CLICK_RANKINGS.getKey(), 0, 9);
        if (!CollectionUtils.isEmpty(bookClickRankings)) {
            final List<Book> rankings = bookClickRankings.stream().map(bookNo -> this.bookService.selectByBookNo(bookNo)).collect(Collectors.toList());
            result.put("rankings", rankings);
        }
        result.put("recommends", this.bookService.findByRecommendOrderByLastModifiedTime((byte) BookRecommendEnum.ONE.getType()));
        return result;
    }

}
