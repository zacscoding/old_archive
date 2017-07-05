//package com.dogsound.config;
//
//import static org.junit.Assert.assertTrue;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//@ActiveProfiles(profiles = {"oracle","mysql"})
//public class EnvironmentTest {	
//	@Autowired 
//	Environment env;
//	
//	Set<String> profileSet;
//	
//	@Before 
//	public void setUp() {
//		profileSet = new HashSet<>();
//		profileSet.add("oracle");
//		profileSet.add("mysql");
//	}
//	
//	@Test
//	public void getProfilesTest() {
//		String[] profiles = env.getActiveProfiles();
//		assertTrue( profiles.length == profileSet.size() );
//		
//		for( String profile : profiles ) {
//			assertTrue( profileSet.contains( profile) );
//		}
//	}
//
//}
