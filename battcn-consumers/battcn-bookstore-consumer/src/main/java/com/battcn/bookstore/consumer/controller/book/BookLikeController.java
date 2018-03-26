package com.battcn.bookstore.consumer.controller.book;

import com.alibaba.dubbo.config.annotation.Reference;
import com.battcn.book.facade.BookLikeService;
import com.battcn.book.pojo.po.BookLike;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Levin
 * @since 2018/3/23 0023
 */
@RestController
@RequestMapping("/app/likes")
@Api(value = "图书管理", description = "点赞管理", tags = "2.0")
public class BookLikeController {

    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:20880", timeout = 10000)
    private BookLikeService bookLikeService;

    @PutMapping("/{book_no}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "将喜欢的书籍点赞", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void giveLike(@PathVariable("book_no") String bookNo) {
        BookLike record = new BookLike();
        record.setBookNo(bookNo);
        record.setMemberNo("battcn");
        this.bookLikeService.insertSelective(record);
    }


}
