package business.service;

import business.model.Users;

import java.util.List;

public interface IUsersService {
    List<Users> fingAll();
    Users findById(Long id);
    void  add(Users u);
    void  update(Users u);
    void delete(Long id) ;
}
