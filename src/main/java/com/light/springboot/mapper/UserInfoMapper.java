package com.light.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.light.springboot.mapper.Bean.UserInfo;

@Mapper
public interface UserInfoMapper {

	@Select("select id,companyId,content,mapId from tblMaster where Id= #{id}")
	@Results({
		@Result(property="username",column="companyId"),
		@Result(property="password",column="content")
	})
	UserInfo getUserById(Integer id);

	@Select("SELECT * FROM tblMaster")
	@Results({
		@Result(property="username",column="companyId"),
		@Result(property="password",column="content")
	})
	public List<UserInfo> getUserList();

	@Insert("insert into tblMaster(id,companyId,content,mapId) values(#{id}, #{username}, #{password}, now())")
	public int add(UserInfo user);

	@Update("UPDATE tblMaster SET username = #{user.username} , age = #{user.age} WHERE id = #{id}")
	public int update(@Param("id") Integer id, @Param("user") UserInfo user);

	@Delete("DELETE from tblMaster where id = #{id} ")
	public int delete(Integer id);
}