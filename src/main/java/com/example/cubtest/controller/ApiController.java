package com.example.cubtest.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.cubtest.constant.AjaxResultEnum;
import com.example.cubtest.entity.ajax.BpiAjax;
import com.example.cubtest.entity.ajax.CurrencyAjax;
import com.example.cubtest.entity.ajax.DataChangeAjax;
import com.example.cubtest.entity.ajax.QueryDataAjax;
import com.example.cubtest.entity.api.ApiVO;
import com.example.cubtest.entity.db.CurrencyVO;
import com.example.cubtest.service.ApiService;
import com.google.gson.Gson;

@RestController
public class ApiController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ApiService apiService;

	@Value("${coindeskApiUrl}")
	private String coindeskApiUrl;

	/**
	 * 原有API資料
	 * @return
	 */
	@GetMapping("/coindeskApi")
	public String coindeskApi() {

		String apiStr = null;

		try {

			RestTemplate restTemplate = new RestTemplate();
			apiStr = restTemplate.getForObject(coindeskApiUrl, String.class);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return apiStr;
	}

	/**
	 * 轉換API資料
	 * @return
	 */
	@GetMapping("/coindeskDataChangeApi")
	public DataChangeAjax coindeskDataChangeApi() {

		DataChangeAjax ajaxVO = new DataChangeAjax();
		ajaxVO.genResult(AjaxResultEnum.SYSTEM_ERROR);

		String apiStr = this.coindeskApi();

		logger.debug("apiStr = " + apiStr);

		if (StringUtils.hasLength(apiStr)) {

			try {

				Gson gson = new Gson();

				ApiVO apiVO = gson.fromJson(apiStr, ApiVO.class);

				logger.debug("apiVO = " + apiVO);

				if (apiVO != null) {

					SimpleDateFormat apiDf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
					apiDf.setTimeZone(TimeZone.getTimeZone("UTC"));

					SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

					String updateTime = df.format(apiDf.parse(apiVO.getTime().getUpdatedISO()));

					ajaxVO.setUpdateTime(updateTime);

					BpiAjax bpiAjax = new BpiAjax();
					bpiAjax.setEUR(this.genCurrenctAjax(apiVO.getBpi().getEUR().getCode(), apiVO.getBpi().getEUR().getRate()));
					bpiAjax.setGBP(this.genCurrenctAjax(apiVO.getBpi().getGBP().getCode(), apiVO.getBpi().getGBP().getRate()));
					bpiAjax.setUSD(this.genCurrenctAjax(apiVO.getBpi().getUSD().getCode(), apiVO.getBpi().getUSD().getRate()));

					ajaxVO.setBpi(bpiAjax);

					ajaxVO.genResult(AjaxResultEnum.SUCCESS);

					logger.debug("ajaxVO = " + ajaxVO);
				}

			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		return ajaxVO;
	}
	
	/**
	 * 取得資料庫幣別對應表
	 * @param code
	 * @return
	 */
	@GetMapping(value= {"/data/{code}", "/data", "/data/"})
	public QueryDataAjax queryData(@PathVariable(value="code", required = false) String code) {

		QueryDataAjax ajaxVO = new QueryDataAjax();
		ajaxVO.genResult(AjaxResultEnum.SUCCESS);

		try {
			
			if (StringUtils.hasLength(code)) {
				
				List<CurrencyVO> list = new ArrayList<>();
				
				CurrencyVO vo = apiService.queryByCode(code);
				
				if (vo != null) {
					list.add(vo);
				}
				
				ajaxVO.setCurrencyList(list);
			} else {
				ajaxVO.setCurrencyList(apiService.queryAll());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxVO.genResult(AjaxResultEnum.SYSTEM_ERROR);
		}

		return ajaxVO;
	}

	/**
	 * 新增幣別對應表資料
	 * @param code
	 * @param name
	 * @return
	 */
	@PostMapping("/data/{code}/{name}")
	public QueryDataAjax insertData(@PathVariable String code, @PathVariable String name) {

		QueryDataAjax ajaxVO = new QueryDataAjax();
		ajaxVO.genResult(AjaxResultEnum.PARAM_FAIL);

		try {

			if (StringUtils.hasLength(code) && StringUtils.hasLength(name)) {
				
				ajaxVO.setCurrencyList(apiService.insert(code, name));

				ajaxVO.genResult(AjaxResultEnum.SUCCESS);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxVO.genResult(AjaxResultEnum.SYSTEM_ERROR);
		}

		return ajaxVO;
	}

	/**
	 * 修改幣別對應表資料
	 * @param code
	 * @param name
	 * @return
	 */
	@PutMapping("/data/{code}/{name}")
	public QueryDataAjax updateData(@PathVariable String code, @PathVariable String name) {
		QueryDataAjax ajaxVO = new QueryDataAjax();
		ajaxVO.genResult(AjaxResultEnum.PARAM_FAIL);

		try {

			if (StringUtils.hasLength(code) && StringUtils.hasLength(name)) {
				
				ajaxVO.setCurrencyList(apiService.update(code, name));

				ajaxVO.genResult(AjaxResultEnum.SUCCESS);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxVO.genResult(AjaxResultEnum.SYSTEM_ERROR);
		}

		return ajaxVO;
	}

	/**
	 * 刪除幣別對應表資料
	 * @param code
	 * @return
	 */
	@DeleteMapping("/data/{code}")
	public QueryDataAjax deleteData(@PathVariable String code) {
		
		QueryDataAjax ajaxVO = new QueryDataAjax();
		ajaxVO.genResult(AjaxResultEnum.PARAM_FAIL);

		try {

			if (StringUtils.hasLength(code)) {

				ajaxVO.setCurrencyList(apiService.delete(code));

				ajaxVO.genResult(AjaxResultEnum.SUCCESS);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxVO.genResult(AjaxResultEnum.SYSTEM_ERROR);
		}

		return ajaxVO;
	}

	private CurrencyAjax genCurrenctAjax(String code, String rate) {
		CurrencyAjax currency = new CurrencyAjax();
		currency.setCode(code);
		currency.setRate(rate);
		currency.setName(apiService.queryByCode(code) == null ? null : apiService.queryByCode(code).getName());
		return currency;
	}
}
