package com.librarySystem.Demo.dao;

import com.librarySystem.Demo.entity.Layout;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LayoutDao
{
    List<Layout> getLayout();
}
