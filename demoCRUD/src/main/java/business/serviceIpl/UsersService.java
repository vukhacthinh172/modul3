package business.serviceIpl;

import business.dao.IUserDao;
import business.daoIpl.UserDao;
import business.model.Users;
import business.service.IUsersService;

import java.util.List;

public class UsersService implements IUsersService {
    private  static  final IUserDao userDao = new UserDao();
    @Override
    public List<Users> fingAll() {
        return userDao.findAll();
    }

    @Override
    public Users findById(Long id) {
        return userDao.fingById(id);
    }

    @Override
    public void add(Users u) {
          userDao.save(u);
    }

    @Override
    public void update(Users u) {
            userDao.save(u);
    }

    @Override
    public void delete(Long id) {
          userDao.delete(id);
    }
}
