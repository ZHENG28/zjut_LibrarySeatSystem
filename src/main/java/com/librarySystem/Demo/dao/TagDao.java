package com.librarySystem.Demo.dao;

import com.librarySystem.Demo.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao
{
    List<Tag> getAllTag();
}
