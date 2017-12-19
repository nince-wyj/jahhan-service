package net.jahhan.codecenter.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.frameworkx.annotation.Controller;

import lombok.extern.slf4j.Slf4j;
import net.jahhan.codecenter.dao.TDbScriptInfoDao;
import net.jahhan.codecenter.dao.TDbSourceDao;
import net.jahhan.codecenter.intf.CodeIntf;
import net.jahhan.codecenter.pojo.TDbScriptInfo;
import net.jahhan.codecenter.pojo.TDbSource;
import net.jahhan.codecenter.pojo.page.TDbSourcePage;
import net.jahhan.common.extension.utils.Assert;
import net.jahhan.jdbc.pojo.page.PagedResult;

@Controller
@Slf4j
public class CodeController implements CodeIntf {
	
	@Inject
	TDbScriptInfoDao tDbScriptInfoDao;
	
	@Inject
	TDbSourceDao tDbSourceDao;
	
	@Override
	public void uploadSql(String service, String source, String version, String sql) {
		// TODO Auto-generated method stub
		TDbSourcePage page = new TDbSourcePage();
		page.setSourceName(source);
		PagedResult<TDbSource> pagedResultOfListTDbSource = tDbSourceDao.pagedResultOfListTDbSource(page);
		List<TDbSource> list = pagedResultOfListTDbSource.getList();
		TDbSource tDbSource = list.get(0);
		Assert.notNull(tDbSource, 999);

		TDbScriptInfo tDbScriptInfo = new TDbScriptInfo();
		tDbScriptInfo.setCreator(01L);
		tDbScriptInfo.setContent(sql);
		tDbScriptInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
		tDbScriptInfo.setSourceId(tDbSource.getSourceId());
		tDbScriptInfo.setStatus(0);
		tDbScriptInfo.setVersion(version);
		tDbScriptInfoDao.addTDbScriptInfo(tDbScriptInfo);
	}

	@Override
	public void excuteSql(String scriptId) {
		// TODO Auto-generated method stub
		
	}




}
