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
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("������Ϣ����ϵͳ");
	DefaultMutableTreeNode flight = new DefaultMutableTreeNode("������Ϣ");
	DefaultMutableTreeNode flightInfo = new DefaultMutableTreeNode("��ѯ���޸ĺ�����Ϣ");
	DefaultMutableTreeNode Addflight = new DefaultMutableTreeNode("��Ӻ�����Ϣ");
	DefaultMutableTreeNode hotel = new DefaultMutableTreeNode("������Ϣ");
	DefaultMutableTreeNode hotelInfo = new DefaultMutableTreeNode("��ѯ���޸ı�����Ϣ");
	DefaultMutableTreeNode Addhotel = new DefaultMutableTreeNode("��ӱ�����Ϣ");
	DefaultMutableTreeNode car = new DefaultMutableTreeNode("�⳵��Ϣ");
	DefaultMutableTreeNode carInfo = new DefaultMutableTreeNode("��ѯ���޸��⳵��Ϣ");
	DefaultMutableTreeNode Addcar = new DefaultMutableTreeNode("����⳵��Ϣ");
	DefaultMutableTreeNode customer = new DefaultMutableTreeNode("�˿���Ϣ");
	DefaultMutableTreeNode customerInfo = new DefaultMutableTreeNode("��ѯ���޸Ĺ˿���Ϣ");
	DefaultMutableTreeNode Addcustomer = new DefaultMutableTreeNode("��ӹ˿���Ϣ");
	DefaultMutableTreeNode reservation = new DefaultMutableTreeNode("Ԥ����Ϣ");
	DefaultMutableTreeNode reservationInfo = new DefaultMutableTreeNode("��ѯԤ����Ϣ");
	DefaultMutableTreeNode Addreservation = new DefaultMutableTreeNode("���Ԥ����Ϣ");
	DefaultMutableTreeNode RouteInfo = new DefaultMutableTreeNode("��ѯ�ο�������·");
	JTree tree = new JTree(root);
	JScrollPane jsp = new JScrollPane(tree);// ΪJTree������������
	private JLabel jlRoot = new JLabel();
	private JSplitPane jsplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);// �����ָ�����
	private JPanel jp = new JPanel();// ����JPanel���� Ϊ������CardLayout���� ���ô���ָ���ұ�
	CardLayout cl = new CardLayout();// ��ȡ��Ƭ���ֹ���������
	AddReservation ar = new AddReservation();
	RouteInfo ri = new RouteInfo();
	public Root() {
		jlRoot.setText("<html><font size='8' face='Courier'>��ӭʹ��������Ϣ����ϵͳ </font><br><br>"
				+ "<font size='5' face='Courier'>��Ȩ����    SA18225168    ���</font></html>");
		this.setTitle("������Ϣ����ϵͳ_SA18225168_���");
		this.initJPane(); // ��ʼ����Ƭ�������
		this.addTreeListener();// Ϊ���ڵ�ע���¼�������

		// ������ Ϊÿ���ڵ�����ӽڵ�
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
		tree.setShowsRootHandles(true);// ������ʾ���ڵ�Ŀ���ͼ��
		// ���ָ����ӽ���
		this.add(jsplitPane);
		jsplitPane.setLeftComponent(tree);
		// Ϊjp���ô�Сλ�ò���ӽ��ұߵ��Ӵ���
		jp.setBounds(0, 0, 700, 700);
		jsplitPane.setRightComponent(jp);

		jsplitPane.setDividerLocation(230);// ���÷ָ����ĳ�ʼλ��
		jsplitPane.setDividerSize(4);// ���÷ָ����Ŀ��

//		jlRoot.setFont(new Font("Courier", Font.PLAIN, 30));
		jlRoot.setHorizontalAlignment(JLabel.CENTER);
		jlRoot.setVerticalAlignment(JLabel.CENTER);
		
		// ���ô���Ĺرն��������⣬��С��λ�ü��ɼ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ô����״γ��ֵĴ�С��λ��--�Զ�����
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = screenSize.width / 2;
		int centerY = screenSize.height / 2;
		int w = 1100;// ��������
		int h = 800;// ������߶�
		this.setBounds(centerX - w / 2, centerY - h / 2, w, h);// ���ô����������Ļ����

		this.setVisible(true);// ���ô���ɼ�
	}

	// ��� ��Ƭ
	public void initJPane() {
		jp.setLayout(cl);
		// jp.add()....
		jp.add(jlRoot, "root");// ��Ӹ������ʾ��Ϣ
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
			if (nodeValue.equals("������Ϣ����ϵͳ")) {
				cl.show(jp, "root");
			} else if (nodeValue.equals("��ѯ���޸ĺ�����Ϣ")) {
				FlightInfo.updateModel();
				cl.show(jp,"flightInfo");
			} else if (nodeValue.equals("��Ӻ�����Ϣ")) {
				cl.show(jp,"addFlight");
			} else if (nodeValue.equals("��ѯ���޸ı�����Ϣ")) {
				HotelInfo.updateModel();
				cl.show(jp,"hotelInfo");
			} else if (nodeValue.equals("��ӱ�����Ϣ")) {
				cl.show(jp,"addHotel");
			} else if (nodeValue.equals("��ѯ���޸��⳵��Ϣ")) {
				CarInfo.updateModel();
				cl.show(jp,"carInfo");
			} else if (nodeValue.equals("����⳵��Ϣ")) {			
				cl.show(jp, "addCar");
			} else if (nodeValue.equals("��ѯ���޸Ĺ˿���Ϣ")) {
				CustomerInfo.updateModel();	
				cl.show(jp,"customerInfo");
			} else if (nodeValue.equals("��ӹ˿���Ϣ")) {				
				cl.show(jp,"addCustomer");
			} else if (nodeValue.equals("��ѯԤ����Ϣ")) {
				ReservationInfo.updateModel();
				cl.show(jp,"reservationInfo");
			} else if (nodeValue.equals("���Ԥ����Ϣ")) {
				ar.updateModel();	
				cl.show(jp,"addReservation");
			}else if(nodeValue.equals("��ѯ�ο�������·")) {
				ri.updateModel();
				cl.show(jp,"routeInfo");
			}
		});
	}

	public static void main(String[] args) {
		new Root();
	}
}
