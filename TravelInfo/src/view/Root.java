package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Root extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("旅游信息管理系统");
	DefaultMutableTreeNode flight = new DefaultMutableTreeNode("航班信息");
	DefaultMutableTreeNode flightInfo = new DefaultMutableTreeNode("查询和修改航班信息");
	DefaultMutableTreeNode Addflight = new DefaultMutableTreeNode("添加航班信息");
	DefaultMutableTreeNode hotel = new DefaultMutableTreeNode("宾馆信息");
	DefaultMutableTreeNode hotelInfo = new DefaultMutableTreeNode("查询和修改宾馆信息");
	DefaultMutableTreeNode Addhotel = new DefaultMutableTreeNode("添加宾馆信息");
	DefaultMutableTreeNode car = new DefaultMutableTreeNode("租车信息");
	DefaultMutableTreeNode carInfo = new DefaultMutableTreeNode("查询和修改租车信息");
	DefaultMutableTreeNode Addcar = new DefaultMutableTreeNode("添加租车信息");
	DefaultMutableTreeNode customer = new DefaultMutableTreeNode("顾客信息");
	DefaultMutableTreeNode customerInfo = new DefaultMutableTreeNode("查询和修改顾客信息");
	DefaultMutableTreeNode Addcustomer = new DefaultMutableTreeNode("添加顾客信息");
	DefaultMutableTreeNode reservation = new DefaultMutableTreeNode("预定信息");
	DefaultMutableTreeNode reservationInfo = new DefaultMutableTreeNode("查询预订信息");
	DefaultMutableTreeNode Addreservation = new DefaultMutableTreeNode("添加预订信息");
	DefaultMutableTreeNode RouteInfo = new DefaultMutableTreeNode("查询游客旅行线路");
	JTree tree = new JTree(root);
	JScrollPane jsp = new JScrollPane(tree);// 为JTree创建滚动窗体
	private JLabel jlRoot = new JLabel();
	private JSplitPane jsplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);// 创建分割窗体对象
	private JPanel jp = new JPanel();// 创建JPanel对象 为其设置CardLayout布局 放置窗体分割的右边
	CardLayout cl = new CardLayout();// 获取卡片布局管理器引用
	AddReservation ar = new AddReservation();
	RouteInfo ri = new RouteInfo();
	public Root() {
		jlRoot.setText("<html><font size='8' face='Courier'>欢迎使用旅游信息管理系统 </font><br><br>"
				+ "<font size='5' face='Courier'>版权所有    SA18225168    李安民</font></html>");
		this.setTitle("旅游信息管理系统_SA18225168_李安民");
		this.initJPane(); // 初始化卡片布局面板
		this.addTreeListener();// 为树节点注册事件监听器

		// 完善树 为每个节点添加子节点
		flight.add(flightInfo);
		flight.add(Addflight);
		hotel.add(hotelInfo);
		hotel.add(Addhotel);
		car.add(carInfo);
		car.add(Addcar);
		customer.add(customerInfo);
		customer.add(Addcustomer);
		reservation.add(reservationInfo);
		reservation.add(Addreservation);
		root.add(flight);
		root.add(hotel);
		root.add(car);
		root.add(customer);
		root.add(reservation);
		root.add(RouteInfo);
		tree.setShowsRootHandles(true);// 设置显示根节点的控制图标
		// 将分割窗体添加进来
		this.add(jsplitPane);
		jsplitPane.setLeftComponent(tree);
		// 为jp设置大小位置并添加进右边的子窗口
		jp.setBounds(0, 0, 700, 700);
		jsplitPane.setRightComponent(jp);

		jsplitPane.setDividerLocation(230);// 设置分隔条的初始位置
		jsplitPane.setDividerSize(4);// 设置分隔条的宽度

//		jlRoot.setFont(new Font("Courier", Font.PLAIN, 30));
		jlRoot.setHorizontalAlignment(JLabel.CENTER);
		jlRoot.setVerticalAlignment(JLabel.CENTER);
		
		// 设置窗体的关闭动作，标题，大小，位置及可见性
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗体首次出现的大小和位置--自动居中
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width / 2;
		int centerY = screenSize.height / 2;
		int w = 1100;// 本窗体宽度
		int h = 800;// 本窗体高度
		this.setBounds(centerX - w / 2, centerY - h / 2, w, h);// 设置窗体出现在屏幕中央

		this.setVisible(true);// 设置窗体可见
	}

	// 添加 卡片
	public void initJPane() {
		jp.setLayout(cl);
		// jp.add()....
		jp.add(jlRoot, "root");// 添加根结点显示信息
		jp.add(new FlightInfo(), "flightInfo");
		jp.add(new AddFlight(), "addFlight");
		jp.add(new HotelInfo(), "hotelInfo");
		jp.add(new AddHotel(), "addHotel");
		jp.add(new CarInfo(), "carInfo");
		jp.add(new AddCar(), "addCar");
		jp.add(new CustomerInfo(), "customerInfo");
		jp.add(new AddCustomer(), "addCustomer");
		jp.add(new ReservationInfo(), "reservationInfo");
		
		jp.add(ar, "addReservation");
		jp.add(ri, "routeInfo");
		jp.setBackground(new Color(240, 248,255));
		
	}

	public void addTreeListener() {
		tree.addTreeSelectionListener(e -> {
			DefaultMutableTreeNode cdmtn = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
			String nodeValue = (String) cdmtn.getUserObject();
			if (nodeValue.equals("旅游信息管理系统")) {
				cl.show(jp, "root");
			} else if (nodeValue.equals("查询和修改航班信息")) {
				FlightInfo.updateModel();
				cl.show(jp,"flightInfo");
			} else if (nodeValue.equals("添加航班信息")) {
				cl.show(jp,"addFlight");
			} else if (nodeValue.equals("查询和修改宾馆信息")) {
				HotelInfo.updateModel();
				cl.show(jp,"hotelInfo");
			} else if (nodeValue.equals("添加宾馆信息")) {
				cl.show(jp,"addHotel");
			} else if (nodeValue.equals("查询和修改租车信息")) {
				CarInfo.updateModel();
				cl.show(jp,"carInfo");
			} else if (nodeValue.equals("添加租车信息")) {			
				cl.show(jp, "addCar");
			} else if (nodeValue.equals("查询和修改顾客信息")) {
				CustomerInfo.updateModel();	
				cl.show(jp,"customerInfo");
			} else if (nodeValue.equals("添加顾客信息")) {				
				cl.show(jp,"addCustomer");
			} else if (nodeValue.equals("查询预订信息")) {
				ReservationInfo.updateModel();
				cl.show(jp,"reservationInfo");
			} else if (nodeValue.equals("添加预订信息")) {
				ar.updateModel();	
				cl.show(jp,"addReservation");
			}else if(nodeValue.equals("查询游客旅行线路")) {
				ri.updateModel();
				cl.show(jp,"routeInfo");
			}
		});
	}

	public static void main(String[] args) {
		new Root();
	}
}
