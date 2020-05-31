package com.lnsf.tmall.controller;

import com.lnsf.tmall.pojo.*;
import com.lnsf.tmall.service.*;
import com.lnsf.tmall.util.Commons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class Fore_Common_Controller {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductimageService productimageService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyvalueService propertyvalueService;
    @Autowired
    private  ReviewService reviewService;
    @Autowired
    private UserService userService;
   @Autowired
    private  OrderitemService orderitemService;
    @Autowired
    private  OrdersService ordersService;


     /*商品详情页*/
    @RequestMapping(value = "/products/{pid}/details",method = RequestMethod.GET)
    public String productDetails(Model m, @PathVariable Integer pid){
        /*获取产品*/
        Product product = productService.selectProductById(pid);
        product.setSalecount(productService.saleCount(product));
        product.setReviewcount(productService.reviewCount(product));
        /*获取产品类别*/
        Category category = categoryService.selectCategoryById(product.getCid());
        /*获取图片*/
        List<Productimage> list = productimageService.selectByPid(pid);
        product.setProductimage(list.get(0));
        List<Productimage> type_single = productimageService.imgtype("type_single", pid);
        List<Productimage> type_detail = productimageService.imgtype("type_detail", pid);
        /*获取评论*/
        List<Review> reviews = reviewService.selectBypid(pid);
        for (Review r:reviews) {
            User user = userService.selectUserById(r.getUid());
            user.setName(userService.setNameToAnonymous(user.getName()));
            r.setUser(user);
        }
        /*获取属性*/
        List<Propertyvalue> propertyvalues = propertyvalueService.selectBypid(pid);
        for (Propertyvalue propertyvalue : propertyvalues){
            Property p = propertyService.selectPropertyById(propertyvalue.getPtid());
            propertyvalue.setProperty(p);
        }
        m.addAttribute("pvs",propertyvalues);
        m.addAttribute("reviews",reviews);
        m.addAttribute("c",category);
        m.addAttribute("p",product);
        m.addAttribute("type_single",type_single);
        m.addAttribute("type_detail",type_detail);
        return "fore/product";

    }


    /*加入购物车*/
    @RequestMapping(value = "foreaddCart",method = RequestMethod.POST)
    public @ResponseBody String foreaddCart(Orderitem orderitem, HttpServletRequest request){
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        orderitem.setUid(user.getUid());//给要加入购物车的货物加上uid标识
        List<Orderitem> ois = orderitemService.selectByuid(user.getUid());//获取该用户加入购物车的产品（包括已购买）
        for (Orderitem o:ois) {//遍历ois，如果用户已加入过相同的产品，更新加入购物车的数量
            if (o.getPid().equals(orderitem.getPid())&&o.getOid()==null){
                o.setNumber(orderitem.getNumber()+o.getNumber());//更新数量
                int i = orderitemService.updateOrderitemById(o);
                if (i!=0){
                    int countCart = orderitemService.countCart(user.getUid());
                    session.setAttribute("cartTotalItemNumber",countCart);
                    return "success";
                }
            }
        }
        //已遍历过已加入购物车的产品，没有相同的，插入数据
        int i = orderitemService.insert(orderitem);
        if (i!=0){
            int countCart = orderitemService.countCart(user.getUid());
            session.setAttribute("cartTotalItemNumber",countCart);
            return "success";
        }
        return "fail";
    }

    /*查看购物车*/
    @RequestMapping(value = "{uid}/forecart",method = RequestMethod.GET)
    public String forecart(Model m,@PathVariable Integer uid){
        List<Orderitem> ois=new ArrayList<Orderitem>();
        if (uid!=null){
            List<Orderitem> orderitems = orderitemService.selectByuid(uid);
            for (Orderitem o : orderitems){
                if (o.getOid()==null) {
                    Product product = productService.selectProductById(o.getPid());
                    productService.FirstImage(product);
                    o.setProduct(product);
                    ois.add(o);
                }
            }
            m.addAttribute("ois",ois);
            return "fore/cart";
        }else {
            return "redirect:/signup";
        }

    }

    /*在购物车改变所选商品的数量*/
    @RequestMapping(value = "changenumber",method = RequestMethod.POST)
    public @ResponseBody String changenumber(@RequestParam Integer pid,@RequestParam Integer number,HttpServletRequest request) {
        int i=0;
        System.out.println("uuuuu");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        List<Orderitem> orderitems = orderitemService.selectByuid(user.getUid());
        for (Orderitem orderitem:orderitems ) {
            if (orderitem.getPid().equals(pid)){
                orderitem.setNumber(number);
                 i = orderitemService.updateOrderitemById(orderitem);
            }
        }
        if (i!=0){
            return "success";
        }else {
            return "fail";
        }
    }
    /*删除购物车里的内容*/
    @RequestMapping(value = "deleteOrderItem",method = RequestMethod.POST)
    public @ResponseBody String deleteOrderItem(@RequestParam Integer oiid){
        int i = orderitemService.deleteOrderitemById(oiid);
        if (i!=0)
            return "success";
        else
            return "fail";
    }

    /*直接在商品页面下单*/
    @RequestMapping(value = "orderone",method = RequestMethod.GET)
    public String forebuyone(Model m,Integer pid,Integer num,HttpServletRequest request){
        List<Orderitem> orderitems=new ArrayList<Orderitem>();
        Orderitem o=new Orderitem();
        o.setNumber(num);
        o.setPid(pid);
        orderitems.add(o);
        Product product = productService.selectProductById(pid);
        productService.FirstImage(product);
        orderitems.get(0).setProduct(product);
        DecimalFormat df = new DecimalFormat("########.00");
        double total=Double.parseDouble(df.format(product.getPromoteprice()*num));
        HttpSession session = request.getSession();
        session.setAttribute("orderitems",orderitems);
        m.addAttribute("total",total);
        return "fore/confirm_pay";
    }

    /*在购物车里多选，下单*/
    @RequestMapping(value = "ordermany",method = RequestMethod.GET)
    public String ordermany(Model m,Integer[] oiid,String total,HttpServletRequest request){
        List<Orderitem> orderitems=new ArrayList<>();
        for (Integer id:oiid) {
            System.out.println(id);
            Orderitem orderitem = orderitemService.selectOrderitemById( id);
            Product product = productService.selectProductById(orderitem.getPid());
            productService.FirstImage(product);
            orderitem.setProduct(product);
            orderitems.add(orderitem);
        }
        DecimalFormat df = new DecimalFormat("########.00");
        double t=Double.parseDouble(df.format(Double.parseDouble(total)));
        HttpSession session = request.getSession();
        session.setAttribute("orderitems",orderitems);
        m.addAttribute("total",t);
        return "fore/confirm_pay";
    }


    /*确认订单时，需要填各种信息*/
    @RequestMapping(value = "comfirmOrder" ,method = RequestMethod.POST)
    public String comfirmOrder(Model m,Orders orders,HttpServletRequest request){
        int i1=0;
        Date date = new Date();
        orders.setCreatedate(date);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        orders.setUid(user.getUid());
        orders.setStatus("waitPay");//未付款
        String ordercode = Commons.ordercode();
        orders.setOrdercode(ordercode);
        DecimalFormat df = new DecimalFormat("########.00");
        orders.setTotal(Double.parseDouble(df.format(orders.getTotal())));
        int i = ordersService.insert(orders);
        List<Orderitem> orderitems = (List<Orderitem>) session.getAttribute("orderitems");
        if (i!=0){
            for (Orderitem orderitem : orderitems){
                orderitem.setOid(orders.getOid());
                if (orderitem.getUid()==null) {
                    orderitem.setUid(user.getUid());
                    i1 = orderitemService.insert(orderitem);
                }else {
                     i1 = orderitemService.updateOrderitemById(orderitem);
                }
            }
        }
        m.addAttribute("orders",orders);
        if (i1!=0) {
            int countCart = orderitemService.countCart(user.getUid());
            session.setAttribute("cartTotalItemNumber", countCart);
            return "fore/alipay";
        }
        else
            return "ok";
    }
    /*在支付时退出，重新在订单里找到改订单，直接支付*/
    @RequestMapping(value = "paydirectry" ,method = RequestMethod.GET)
    public String paydirectry(Model m,Integer oid,double total,HttpServletRequest request){
        int i=0;
        Date date = new Date();
        Orders orders = ordersService.selectOrdersById(oid);
        m.addAttribute("orders",orders);
        return "fore/alipay";
    }

    /*成功支付后的订单*/
    @RequestMapping(value = "successpayed",method = RequestMethod.GET)
    public String successpayed(Model m,Integer oid){
        Date date = new Date();
        Orders orders = ordersService.selectOrdersById(oid);
        orders.setStatus("waitDelivery");
        orders.setPaydate(date);
        int i = ordersService.updateOrdersitemById(orders);
        Orders o = ordersService.selectOrdersById(oid);
        m.addAttribute("o",orders);
        return "fore/successpayed";
    }

    /*查看一个用户所有订单*/
    @RequestMapping(value = "{uid}/orders",method = RequestMethod.GET)
    public String Userorders(Model m,@PathVariable Integer uid){
        List<Orders> ordersList = ordersService.selectByuid(uid);
        for (Orders o:ordersList) {
            List<Orderitem> orderitems = orderitemService.selectByoid(o.getOid());
            for (Orderitem oi : orderitems){
                Product product = productService.selectProductById(oi.getPid());
                productService.FirstImage(product);
                oi.setProduct(product);
            }
            o.setNumberforone(orderitems.size());
            o.setOrderitems(orderitems);

        }
        m.addAttribute("ordersList",ordersList);
        return "fore/oders";
    }

    /*删除订单，不删除orderitem，后台作为数据*/
    @RequestMapping(value = "deleteOrder",method = RequestMethod.POST)
    public @ResponseBody String deleteOrder(@RequestParam Integer oid){
        int i = ordersService.deleteOrdersById(oid);
        if (i!=0)
            return "success";
        else
            return "fail";
    }

   /*确认收货页面*/
    @RequestMapping(value = "confirmorder",method = RequestMethod.GET)
    public String confirm_order(Model m,Integer oid){
        Orders orders = ordersService.selectOrdersById(oid);
        m.addAttribute("o",orders);
        return "fore/confirm_order" ;
    }

    /*确认收货后跳转到收货成功页面*/
    @RequestMapping(value = "orderconfirmed",method = RequestMethod.GET)
    public String confirm_order(Integer oid){
        Orders orders = ordersService.selectOrdersById(oid);
        orders.setConfirmdate(new Date());
        orders.setStatus("waitReview");
        int i = ordersService.updateOrdersitemById(orders);
        if (i!=0)
            return "fore/orderConfirmed";
        else
            return null ;
    }

    /*确认收货后评价---没实现*/


   }



