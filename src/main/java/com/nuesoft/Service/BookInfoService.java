package com.nuesoft.Service;

import com.nuesoft.po.BookInfo;
import com.nuesoft.po.BookInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookInfoService {
    int deleteByPrimaryKey(Integer bookId);

    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    List<BookInfo> selectByExample(BookInfoExample example);

    BookInfo selectByPrimaryKey(Integer bookId);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKey(BookInfo record);

    List<BookInfo> selectAllBook(@Param("bookid") Integer bookid, @Param("bookname") String bookname, @Param("borrow") Integer borrow);

    int deleteByIds(String[] bookId);
}
