package com.example.solution.repository;

import com.example.solution.entity.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.repository.query.*;

@Mapper
public interface EmployeeRepositoty {

    @Insert("Insert into Employee(name,processId,flowId, idempotenceUuid,cv_file) values (#{name},#{processId},#{flowId},#{idempotenceUuid},#{cv_file})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(Employee employee);

    @Update("Update Employee set name= #{name}, processId= #{processId}" +
            ", flowId= #{flowId}, idempotenceUuid= #{idempotenceUuid}, cv_file= #{cv_file} " +
            "  where id=#{id}")
    @Options(useGeneratedKeys = true, keyProperty = "name", keyColumn = "name")
    void update(Employee employee);

    @Result(column = "id", property = "id")
    @Select("SELECT * FROM Employee WHERE id = #{id}")
    Employee getById(@Param("id") Long id);


//
//        @Delete("Delete from Song where id=#{id}")
//        void deleteById(Long id);
//
//        @Delete("Delete from Song where name=#{name}")
//        void deleteByName(String name);
//

//
//        @Select("SELECT * FROM Song")
//        List<Song> finedAllSongs();
//
//        @Select("SELECT * FROM Song WHERE name = #{name}")
//        Song findByName(@org.springframework.data.repository.query.Param("name") String name);
//
//        @Select("SELECT EXISTS(SELECT * FROM Song WHERE name = #{name})")
//        Boolean isExistByName(@org.springframework.data.repository.query.Param("name") String name);
//
//        @Select("SELECT EXISTS(SELECT * FROM Song WHERE name = #{name} or notes = #{notes})")
//        Boolean isExistByNameOrNotes(@org.springframework.data.repository.query.Param("name") String name
//                , @Param("notes") String notes);
//
//
//        @Select("SELECT source.path FROM Song CROSS JOIN Source ON Source.song_id=song.id WHERE Song.name=#{name} LIMIT 1;")
//        SourceMetadata findResourceBySongName(String name);//TODO запрос sql
}


