package com.snow.dao;

import com.snow.Domain.Item;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2017/12/30
 * Time: 下午2:52
 */
public interface IndexDao {
    long getItemCount();

    Item getItems(Integer id);
}
