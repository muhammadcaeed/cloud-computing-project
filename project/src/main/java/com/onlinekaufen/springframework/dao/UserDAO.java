package com.onlinekaufen.springframework.dao;

import com.onlinekaufen.springframework.dto.UserDTO;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

@UseStringTemplate3StatementLocator
@RegisterMapperFactory(BeanMapperFactory.class)
public interface UserDAO {

    @SqlQuery("SELECT * FROM users WHERE email_id = :userName AND enabled = 1")
    UserDTO getUserByUsername(@Bind("userName") String userName);

    @SqlUpdate("UPDATE users SET first_name = :firstName, last_name = :lastName, phone_no = :phoneNo, address = :address, city = :city, state = :state, country = :country, post_code = :postCode WHERE email_id = :emailId")
    void updateUser(@BindBean UserDTO userDTO);

    @SqlQuery("SELECT id, first_name, last_name, phone_no, address, enabled, email_id FROM users WHERE role = 'ROLE_USER'")
    List<UserDTO> getAllUsers();

    @SqlUpdate("UPDATE users SET enabled = 0 WHERE id = :id")
    void banUser(@Bind("id") String id);

    @SqlUpdate("UPDATE users SET enabled = 1 WHERE id = :id")
    void unbanUser(@Bind("id") String id);

    @SqlUpdate("UPDATE users SET password = :password WHERE emailID = :userName")
    void changePassword(@Bind("password") String password, @Bind("userName") String userName);

    @SqlUpdate("INSERT INTO users(email_id, password, first_name, last_name, phone_no, address, city, state, country, post_code, registration_date, role, alias) VALUES (:emailId, :password, :firstName, :lastName, :phoneNo, :address, :city, :state, :country, :postCode, NOW(), :role, :alias)")
    int addUser(@BindBean UserDTO user);

    @SqlQuery("SELECT * FROM users WHERE email_id = :emailID")
    UserDTO getUser(@Bind("emailID") String emailID);

    @SqlUpdate("DELETE FROM user WHERE user_id = :userId")
    void deleteUser(@Bind("userId") Long userId);
}
