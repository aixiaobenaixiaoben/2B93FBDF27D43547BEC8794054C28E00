package com.snow.service;

import com.snow.Domain.Item;
import com.snow.dao.IndexDao;
import com.snow.main.ConException;
import com.snow.util.SpringContextUtil;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2017/12/29
 * Time: 上午9:27
 */
@Service
public class IndexService {

    @Resource
    IndexDao indexDao;

    @Transactional(rollbackFor = Exception.class)
    public String index() {
        return "hello world" + indexDao.getItemCount();
    }

    /**
     * 编程式事务
     * @throws ConException
     */
    public void index2() throws ConException {

        DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) SpringContextUtil.getBean("transactionManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);//事务隔离级别，开启新事物，与A类B类不使用同一个事务
        TransactionStatus status = transactionManager.getTransaction(def);//获得事务状态

        try {
            String res = "hello world" + indexDao.getItemCount();
            if (res.length() == 0) {
                throw new ConException("some message");
            }


            transactionManager.commit(status);

        } catch (ConException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw new ConException("some message", e);
        }
    }

    public Item getItem() {
        return indexDao.getItems(1);
    }
}
