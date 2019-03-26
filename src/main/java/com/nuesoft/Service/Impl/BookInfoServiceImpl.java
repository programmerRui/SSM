package com.nuesoft.Service.Impl;

import com.nuesoft.Service.BookInfoService;
import com.nuesoft.dao.BookInfoMapper;
import com.nuesoft.po.BookInfo;
import com.nuesoft.po.BookInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookInfoServiceImpl implements BookInfoService {
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Override
    public int deleteByPrimaryKey(Integer bookId) {
        return bookInfoMapper.deleteByPrimaryKey(bookId);
    }

    @Override
    public int insert(BookInfo record) {
        return bookInfoMapper.insertSelective(record);
    }

    @Override
    public int insertSelective(BookInfo record) {
        return bookInfoMapper.insertSelective(record);
    }

    @Override
    public List<BookInfo> selectByExample(BookInfoExample example) {
        return bookInfoMapper.selectByExample(example);
    }

    @Override
    public BookInfo selectByPrimaryKey(Integer bookId) {
        return bookInfoMapper.selectByPrimaryKey(bookId);
    }

    @Override
    public int updateByPrimaryKeySelective(BookInfo record) {
        return bookInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BookInfo record) {
        return bookInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BookInfo> selectAllBook(Integer bookid, String bookname, Integer borrow) {
        return bookInfoMapper.selectAllBook(bookid,bookname,borrow);
    }

    @Override
    public int deleteByIds(String[] bookId) {
        return bookInfoMapper.deleteByIds(bookId);
    }
}
