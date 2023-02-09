package com.example.cubtest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
class CubtestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * 測試新增資料庫資料
	 * @throws Exception
	 */
	@Test
	@Order(1)
	public void testInsertData() throws Exception {
		log.info("==== 測試新增資料庫資料 ====");
		mockMvc.perform(MockMvcRequestBuilders.post("/data/USD/美元")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
		mockMvc.perform(MockMvcRequestBuilders.post("/data/GBP/英鎊")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
		mockMvc.perform(MockMvcRequestBuilders.post("/data/EUR/歐元")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
		mockMvc.perform(MockMvcRequestBuilders.post("/data/TEST/新增測試用")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}
	
	/**
	 * 測試取得資料庫全部資料
	 * @throws Exception
	 */
	@Test
	@Order(2)
	public void testQueryAllData() throws Exception {
		log.info("==== 測試取得資料庫全部資料 ====");
		mockMvc.perform(MockMvcRequestBuilders.get("/data/")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}
	
	/**
	 * 測試取得資料庫資料By Code
	 * @throws Exception
	 */
	@Test
	@Order(3)
	public void testQueryDataByCode() throws Exception {
		log.info("==== 測試取得資料庫資料By Code ====");
		mockMvc.perform(MockMvcRequestBuilders.get("/data/USD")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
		mockMvc.perform(MockMvcRequestBuilders.get("/data/GBP")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
		mockMvc.perform(MockMvcRequestBuilders.get("/data/EUR")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
		mockMvc.perform(MockMvcRequestBuilders.get("/data/TEST")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}
	
	/**
	 * 測試更新資料庫資料
	 * @throws Exception
	 */
	@Test
	@Order(4)
	public void testUpdateData() throws Exception {
		log.info("==== 測試更新資料庫資料 ====");
		mockMvc.perform(MockMvcRequestBuilders.put("/data/TEST/修改測試用")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}
	
	/**
	 * 測試刪除資料庫資料
	 * @throws Exception
	 */
	@Test
	@Order(5)
	public void testDeleteData() throws Exception {
		log.info("==== 測試刪除資料庫資料 ====");
		mockMvc.perform(MockMvcRequestBuilders.delete("/data/TEST")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}

	/**
	 * 測試取得原 coindesk api
	 * @throws Exception
	 */
	@Test
	@Order(6)
	public void testCallCoindeskApi() throws Exception {
		log.info("==== 測試取得原 coindesk api ====");
		mockMvc.perform(MockMvcRequestBuilders.get("/coindeskApi")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}

	/**
	 * 測試取得資料轉換api
	 * @throws Exception
	 */
	@Test
	@Order(7)
	public void testChangeDataApi() throws Exception {
		log.info("==== 測試取得資料轉換api ====");
		mockMvc.perform(MockMvcRequestBuilders.get("/coindeskDataChangeApi")).andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	}

}
