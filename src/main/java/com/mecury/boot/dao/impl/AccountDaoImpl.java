package com.mecury.boot.dao.impl;

import com.mecury.boot.dao.IAccountDAO;
import com.mecury.boot.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @param :
 * @author: kenny [411316753@qq.com]
 * @Date: 2018/7/10-11:34
 * @Description:
 * @return:
 */
@Repository
public class AccountDaoImpl implements IAccountDAO {
   @Autowired
   private JdbcTemplate jdbcTemplate;
   @Override
   public int add(Account account) {
	  return  jdbcTemplate.update("insert into account(name,money) VALUES(?,?)",
		  account.getName(),account.getMoney());
   }

   @Override
   public int update(Account account) {
	  return jdbcTemplate.update("UPDATE account set name=?, money=? WHERE id=?",
		  account.getName(),account.getMoney(),account.getId());
   }

   @Override
   public int delete(int id) {
	  return jdbcTemplate.update("DELETE FROM  TABLE  account where id=?",id);
   }

   @Override
   public Account findAccountById(int id) {
	  List<Account> list = jdbcTemplate.query(
	  	"SELECT * from account where id =? ",new Object[]{id},new BeanPropertyRowMapper<>(Account.class));
	  if (list!=null&&list.size()>0){
	     Account account =list.get(0);
	     return  account;
	  }else{
	     return null;
	  }
   }

   @Override
   public List<Account> findAccountList() {
	  List<Account> list = jdbcTemplate.query("select * from account", new Object[]{}, new BeanPropertyRowMapper(Account.class));
	  if(list!=null && list.size()>0){
		 return list;
	  }else{
		 return null;
	  }
   }
}
