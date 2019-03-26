package com.nuesoft.Service.Impl;

import com.nuesoft.Service.BookTypeService;
import com.nuesoft.dao.BookInfoMapper;
import com.nuesoft.dao.BookTypeMapper;
import com.nuesoft.po.BookType;
import com.nuesoft.po.BookTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeServiceImpl implements BookTypeService {
    @Autowired
    private BookTypeMapper bookTypeService;
    @Override
    public int deleteByPrimaryKey(Integer typeId) {
        return bookTypeService.deleteByPrimaryKey(typeId);
    }

    @Override
    public int insert(BookType record) {
        return bookTypeService.insert(record);
    }

    @Override
    public int insertSelective(BookType record) {
        return bookTypeService.insertSelective(record);
    }

    @Override
    public List<BookType> selectByExample(BookTypeExample example) {
        return bookTypeService.selectByExample(example);
    }

    @Override
    public BookType selectByPrimaryKey(Integer typeId) {
        return bookTypeService.selectByPrimaryKey(typeId);
    }

    @Override
    public int updateByPrimaryKeySelective(BookType record) {
        return bookTypeService.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BookType record) {
        return bookTypeService.updateByPrimaryKey(record);
    }

    @Override
    public List<BookType> selectAll() {
        return bookTypeService.selectAll();
    }
}
