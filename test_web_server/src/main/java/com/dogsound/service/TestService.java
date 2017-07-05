package com.dogsound.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogsound.domain.DatasVO;
import com.dogsound.persistence.MapperFactory;
import com.dogsound.persistence.TestMapper;
import com.dogsound.util.DBDataTypeUtil;

@Service
public class TestService implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(TestService.class);
	
	private static final int DEFAULT_REPEAT = 10;
	
	@Autowired
	private MapperFactory mapperFactory;
	private TestMapper mapper;
	
	
	//INSERT
	public int insert(String count, String[] datas) throws Exception {
		
		logger.info("count : " + count + ", datas : " + Arrays.toString(datas));		
		//HashMap<String,Object> paramMap = convertDatas(datas);		
		DatasVO vo = convertDatasVO(datas);
		
		logger.info( vo.toString() );		
		if( count.equals("1") ) {			
			return mapper.insertOne(vo);			
		} else if(count.equals("10")) {			
			return mapper.insertMultiple( getListFromVO(vo) );
		} else {
			int ret = 0;
			List<DatasVO> list = getListFromVO(vo);
			for(int i=0; i<10; i++) {
				ret += mapper.insertMultiple(list);
			}
			return ret;
		}		
	}
			
	// UPDATE
	public int updateAll() throws Exception {
		return mapper.updateAll();
	}		
	
	// DELETE
	public int deleteAll() {
		return mapper.deleteAll();
	}
	
	// SELECT
	public int selectAll() {
		List<DatasVO> list = mapper.selectAll();
		return list.size();
	}
	
	
	
	//query
	public int executeQuery(String query) throws Exception {
		char firstCh = query.trim().charAt(0);
		int result = -1;
		
		switch( firstCh ) {
		case 'i':
			result = mapper.insertQuery(query);
			break;
		case 'u':
			result = mapper.updateQuery(query);
			break;
		case 'd':
			result = mapper.deleteQuery(query);
			break;
		case 's':
			result = mapper.selectQuery(query).size();
			break;
		default :
			throw new UnsupportedOperationException("구현되지 않은 쿼리문");
		}
		
		return result;
	}
	
	
	
	// private
	private List<DatasVO> getListFromVO(DatasVO vo) {
		List<DatasVO> list = new ArrayList<DatasVO>();
		for(int i=0; i<DEFAULT_REPEAT; i++) {
			list.add(vo);
		}
		return list;
	}
	
	private DatasVO convertDatasVO(String[] datas) {
		DatasVO vo = new DatasVO();
		if( datas == null || datas.length == 0) {
			return vo;
		}
		
		Set<String> keySet = new HashSet<String>(datas.length+10,0.999999f);
		
		Object[] dataValues = DBDataTypeUtil.dataValues;
		
		for( String data : datas ) {
			keySet.add(data);
		}
		
		if( keySet.contains("varchar2_col") ) {
			vo.setStringCol((String)dataValues[0]);
		}
		
		if( keySet.contains("boolean_col") ) {
			vo.setBooleanCol((Boolean)dataValues[1]);
		}
		
		if( keySet.contains("short_col") ) {
			vo.setShortCol((Short)dataValues[2]);
		}
		
		if( keySet.contains("integer_col") ) {
			vo.setIntegerCol((Integer)dataValues[3]);
		}
		
		if( keySet.contains("long_col") ) {
			vo.setLongCol((Long)dataValues[4]);
		}
		
		if( keySet.contains("float_col") ) {
			vo.setFloatCol((Float)dataValues[5]);
		}
		
		if( keySet.contains("double_col") ) {
			vo.setDoubleCol((Double)dataValues[6]);
		}
		
		if( keySet.contains("bigdecimal_col") ) {
			vo.setBigdecimalCol((BigDecimal)dataValues[7]);
		}
		
		if( keySet.contains("timestamp_col") ) {
			vo.setTimestampCol((Date)dataValues[8]);
		}					
		
		return vo;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.mapper = mapperFactory.getMapper();
	}

}
