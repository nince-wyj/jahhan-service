package net.jahhan.codecenter;

import java.util.ArrayList;
import java.util.List;

import net.jahhan.codecenter.bean.ServiceInfo;
import net.jahhan.codecenter.bean.TableInfo;
import net.jahhan.codecenter.constant.ConnectionMessageFormat;
import net.jahhan.codecenter.constant.DbParam;
import net.jahhan.codecenter.maker.BaseMaker;
import net.jahhan.codecenter.maker.dao.AbstrDaoMaker;
import net.jahhan.codecenter.maker.dao.AbstrImplMaker;
import net.jahhan.codecenter.maker.dao.BeanMaker;
import net.jahhan.codecenter.maker.dao.CacheMaker;
import net.jahhan.codecenter.maker.dao.DaoMaker;
import net.jahhan.codecenter.maker.dao.IbatisMaker;
import net.jahhan.codecenter.maker.dao.ImplMaker;
import net.jahhan.codecenter.maker.dao.PageMaker;
import net.jahhan.codecenter.maker.dao.RepMaker;
import net.jahhan.codecenter.util.DbUtil;
import net.jahhan.codecenter.util.TableParaUtil;

/**
 * 单主键-主类
 */
public class CreateCode {

	private static List<BaseMaker> daoMakerList = new ArrayList<BaseMaker>();

	static {
		daoMakerList.add(new BeanMaker());
		daoMakerList.add(new PageMaker());
		daoMakerList.add(new IbatisMaker());
		daoMakerList.add(new AbstrDaoMaker());
		daoMakerList.add(new DaoMaker());
		daoMakerList.add(new AbstrImplMaker());
		daoMakerList.add(new ImplMaker());
		daoMakerList.add(new CacheMaker());
		daoMakerList.add(new RepMaker());
	}

	private static void exec(TableInfo tableInfo, ServiceInfo serviceInfo) {
		for (BaseMaker baseMaker : daoMakerList) {
			baseMaker.exec(tableInfo, serviceInfo);
		}
	}

	public static void main(String[] args) {

		String host = "172.16.112.1:3306";
		String sourceName = "code_center";
		String dbName = "code_center";
		String serviceName = "service-code-center";

		String url = ConnectionMessageFormat.JdbcConnectionUrl.format("mysql", host, dbName);
		String dbUser = "root";
		String dbPassword = "ledwaf";
		String codePackage = "net.jahhan.codecenter";
		
		
		
		DbParam db = new DbParam();
		db.setUser(dbUser);
		db.setPassword(dbPassword);
		db.setUrl(url);
		ServiceInfo serviceInfo = new ServiceInfo();
		serviceInfo.setServiceName(serviceName);
		serviceInfo.setCodePackage(codePackage);
		serviceInfo.setCodePara(TableParaUtil.getCodePara(serviceInfo.getCodePackage()));
		List<TableInfo> tableInfoList = DbUtil.dbManager(sourceName, dbName, db);
		BaseMaker.clean(serviceInfo);
		for (TableInfo tableInfo : tableInfoList) {
			CreateCode.exec(tableInfo, serviceInfo);
		}
	}

}
