package net.jahhan.codecenter.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.frameworkx.annotation.Controller;

import lombok.extern.slf4j.Slf4j;
import net.jahhan.codecenter.constant.ConnectionMessageFormat;
import net.jahhan.codecenter.dao.TDbDao;
import net.jahhan.codecenter.dao.TDbSourceDao;
import net.jahhan.codecenter.dao.TServiceDao;
import net.jahhan.codecenter.dao.TServiceSourceConfDao;
import net.jahhan.codecenter.intf.ServiceIntf;
import net.jahhan.codecenter.pojo.TDb;
import net.jahhan.codecenter.pojo.TDbSource;
import net.jahhan.codecenter.pojo.TService;
import net.jahhan.codecenter.pojo.TServiceSourceConf;
import net.jahhan.codecenter.pojo.page.TDbSourcePage;
import net.jahhan.codecenter.pojo.page.TServicePage;
import net.jahhan.common.extension.utils.Assert;
import net.jahhan.jdbc.pojo.page.PagedResult;

@Controller
@Slf4j
public class ServiceController implements ServiceIntf {

	@Inject
	TDbSourceDao tDbSourceDao;
	
	@Inject
	TDbDao tDbDao;
	
	@Inject 
	TServiceDao tServiceDao;
	
	@Inject
	TServiceSourceConfDao tConfServiceSourceDao;

	@Override
	public void registDbSource(String source) {
		// TODO Auto-generated method stub
		TDbSourcePage page = new TDbSourcePage();
		page.setSourceName(source);
		PagedResult<TDbSource> pagedResultOfListTDbSource = tDbSourceDao.pagedResultOfListTDbSource(page);
		List<TDbSource> list = pagedResultOfListTDbSource.getList();
		TDbSource tDbSource = list.get(0);
		Assert.isNull(tDbSource, 999);
		
		tDbSource.setSourceName(source);
		tDbSourceDao.addTDbSource(tDbSource);
	}
	
	
	@Override
	public void registDbSourceConfig(String source, String dbEnvironment, String readDbAddress, String writeDbAddress, String writeAccount,
			String writePassword, String readAccount, String readPassword) {
		// TODO Auto-generated method stub
		TDbSourcePage page = new TDbSourcePage();
		page.setSourceName(source);
		PagedResult<TDbSource> pagedResultOfListTDbSource = tDbSourceDao.pagedResultOfListTDbSource(page);
		List<TDbSource> list = pagedResultOfListTDbSource.getList();
		TDbSource tDbSource = list.get(0);
		Assert.notNull(tDbSource, 999);
		
		String[] readParams = new String[2];
		readParams[0] = readDbAddress;
		readParams[1] = source;
		String readUrl = ConnectionMessageFormat.JdbcConnectionUrl.format(readParams);
		
		String[] writeParams = new String[2];
		writeParams[0] = writeDbAddress;
		writeParams[1] = source;
		String writeUrl = ConnectionMessageFormat.JdbcConnectionUrl.format(writeParams);

		TDb tDb = new TDb();
		tDb.setSourceId(tDbSource.getSourceId());
		tDb.setDbEnvironment(dbEnvironment);
		tDb.setDbName(source);
		tDb.setReadUser(readAccount);
		tDb.setReadPassword(readPassword);
		tDb.setReadUrl(readUrl);
		tDb.setWriteUser(writeAccount);
		tDb.setWritePassword(writePassword);
		tDb.setWriteUrl(writeUrl);
		tDbDao.addTDb(tDb);		
	}
	
	
	@Override
	public void registService(String source, String service) {
		// TODO Auto-generated method stub
		TServicePage page = new TServicePage();
		page.setServiceName(service);
		PagedResult<TService> pagedResultOfListTService = tServiceDao.pagedResultOfListTService(page);
		List<TService> list = pagedResultOfListTService.getList();
		TService tService = list.get(0);
		Assert.isNull(tService, 999);
		
		tService.setServiceName(service);
		tService.setCreateTime(new Timestamp(System.currentTimeMillis()));
		tService.setCreator(01L);
		tService.setStatus(0);
		tServiceDao.addTService(tService);
		
	}


	@Override
	public void registServiceDbConfig(String source, String service, String codePath) {
		// TODO Auto-generated method stub
		
		TDbSourcePage page = new TDbSourcePage();
		page.setSourceName(source);
		PagedResult<TDbSource> pagedResultOfListTDbSource = tDbSourceDao.pagedResultOfListTDbSource(page);
		List<TDbSource> list = pagedResultOfListTDbSource.getList();
		TDbSource tDbSource = list.get(0);
		Assert.notNull(tDbSource, 999);
		
		TServicePage page2 = new TServicePage();
		page2.setServiceName(service);
		PagedResult<TService> pagedResultOfListTService = tServiceDao.pagedResultOfListTService(page2);
		List<TService> list2 = pagedResultOfListTService.getList();
		TService tService = list2.get(0);
		Assert.notNull(tService, 999);
		
		TServiceSourceConf tConfServiceSource = new TServiceSourceConf();
		tConfServiceSource.setServiceId(tService.getServiceId());
		tConfServiceSource.setSourceId(tDbSource.getSourceId());
		tConfServiceSource.setCodePath(codePath);		
		tConfServiceSource.setIsDefault(1);
		tConfServiceSourceDao.addTServiceSourceConf(tConfServiceSource);
	}
	
	
	









	




}
