package com.company.pss.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.company.pss.dao.IChartDao;
import com.company.pss.domain.OrderBillChartVO;
import com.company.pss.domain.OrderBillGroupBy;
import com.company.pss.domain.SaleAccountChartVO;
import com.company.pss.domain.SaleAccountGroupBy;
import com.company.pss.query.ChartQueryObject;
import com.company.pss.query.QueryObject;
import com.company.pss.query.SaleAccountChartQueryObject;

@SuppressWarnings("all")
public class ChartDaoImpl implements IChartDao {

	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public static void main(String[] args) {
		OrderBillGroupBy[] obj = OrderBillGroupBy.values();
		for (OrderBillGroupBy item : obj) {
			// System.out.println(item.getKey()+"--->"+item.getValue());
		}
	}

	public List<OrderBillChartVO> queryOrderBillChart(QueryObject queryObject) {
		// 获取session
		Session session = sessionFactory.getCurrentSession();

		ChartQueryObject qo = (ChartQueryObject) queryObject;
		// 根据前台传递的字符串找到对应的枚举对象
		OrderBillGroupBy obj = OrderBillGroupBy.valueOf(qo.getGroupType());

		StringBuilder sb = new StringBuilder(80);
		sb.append("select new OrderBillChartVO(");
		sb.append(obj.getGroupTypeByName());
		sb.append(",sum(item.number),sum(item.amount)) from OrderBillItem item ");
		// 高级查询
		sb.append(qo.getQuery());
		sb.append(" group by ");
		sb.append(obj.getGroupType());
		Query query = session.createQuery(sb.toString());
		// 设置参数
		setParameters(qo, query);
		List<OrderBillChartVO> list = query.list();
		return list;
	}



	public List<SaleAccountChartVO> querySaleAccountChart(QueryObject qo) {
		// 获取session
		Session session = sessionFactory.getCurrentSession();

		SaleAccountChartQueryObject sqo = (SaleAccountChartQueryObject) qo;
		// 根据前台传递的字符串找到对应的枚举对象
		SaleAccountGroupBy obj = SaleAccountGroupBy.valueOf(sqo.getGroupType());

		StringBuilder sb = new StringBuilder(80);
		sb.append("select new SaleAccountChartVO(")
		.append(obj.getGroupTypeByName())
		.append(",sum(sa.number),sum(sa.totalSaleAmount),sum(sa.totalSaleAmount - sa.totalCostAmount)) from SaleAccount sa ")
		// 高级查询
		.append(sqo.getQuery())
		.append(" group by ")
		.append(obj.getGroupType());
		// 高级查询
		Query query = session.createQuery(sb.toString());
		// 设置参数
		setParameters(sqo, query);
		return query.list();
	}
	
	private void setParameters(QueryObject qo, Query query) {
		for (int i = 0; i < qo.getParams().size(); i++) {
			query.setParameter(i, qo.getParams().get(i));
		}
	}
}
