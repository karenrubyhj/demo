package com.example.demo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.PageBean;
import com.example.demo.domain.USER;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Resource
    PageBean pageBean;
    @Override
    public List<USER> getAllUser() {
        return userDao.getAllUser();
    }




    //获取全部用户条数

    @Override
    public int   getTotalCount(){

        return  userDao.getTotalCount();

    }



    //获取全部用户分页
    @Override
    public  List<USER> UserListForPage(int index, int currentCount){


        return  userDao.UserListForPage(index,currentCount);


    }

    //根据名称获取用户分页
    @Override
    public  List<USER> getbynameforpage(String name,int index, int currentCount){


        return  userDao.getbynameforpage(name,index,currentCount);



    }



    //获取分页数据
    @Override
    public PageBean findPageBean(String name,int currentPage, int currentCount) throws SQLException {

        System.out.println("页码："+currentPage+","+currentCount);

        // 目的：想办法封装一个PageBean 并返回

        //1、当前页private int currentPage;
        pageBean.setCurrentPage(currentPage);
        //2、当前页显示的条数private int currentCount;
        pageBean.setCurrentCount(currentCount);
        //3、总条数private int totalCount;

        int totalCount;

        if(name.equals("")) {
            totalCount = userDao.getTotalCount();
        }else{

            totalCount=userDao.getTotalCountByName(name);

            System.out.println("查询总数："+totalCount);

        }

        pageBean.setTotalCount(totalCount);

        //4、总页数private int totalPage;
        /*
         * 总条数  当前页显示的条数    总页数
         *  10      4               3
         *  11      4               3
         *  12      4               3
         *  13      4               4
         *
         *  公式：总页数=Math.ceil(总条数/当前显示的条数)
         *
         */
        //ceil向上取整 floor向下取整
        //1.0*是因为两个整型相除，得到的还是整型，如果此时向上取整得不到正确的数，所以在这里先*1.0变成double型
        int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);

        System.out.println("查询后页数："+totalPage);

        pageBean.setTotalPage(totalPage);
        //5、每页显示的数据private List<T> productList = new ArrayList<T>();
        /*
         *页数与limit起始索引的关系
         *例如：每页显示4条
         *页数    起始索引    每页显示条数
         * 1    0       4
         * 2    4       4
         * 3    8       4
         * 4    12      4
         *  1页 limit 0,4
         *  2页 limit 4,4
         *  3页 limit 8,4
         *
         *      索引index = (当前页数-1)*每页显示的条数
         *
         */
        int index = (currentPage-1)*currentCount;


        List<USER> productList;

        if(name.equals("")) {

            System.out.println("11111111111111111111111111");
            productList = userDao.UserListForPage(index, currentCount);

        }else{


            System.out.println("222222222222222222222222222");
            productList=userDao.getbynameforpage(name,index,currentCount);

        }


        //封装数据到pageBean
        pageBean.setUserlist(productList);

        return   pageBean;
    }



//--------------------------------------------


    @Override
    public void insert(USER user) {
        userDao.insert(user);
    }


    @Override

    public List<USER> getbyName(String name) { return userDao.getbyName(name);}

    @Override
    public void delete(int id){

        userDao.delete(id);


    }


    @Override
    public void update(USER user) {
        userDao.update(user);
    }


}
