package com.ssafy.api.controller;

import com.ssafy.api.response.ExchangeRateRes;
import com.ssafy.api.service.ExchangeRateService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    @PostMapping("/curr")
    @ApiOperation(value = "통화코드에 따른 환율 정보 제공", notes = "example value:  \n" +
            "{\n" +
            "\"start_date\": \"2000-10-04\",\n" +
            "\"end_date\": \"2001-10-04\",\n" +
            "\"code\": \"USD\"\n" +
            "}")
    public ResponseEntity<List<ExchangeRateRes>> getExchangeRate(@RequestBody Map<String, Object> map) throws ParseException {
        String sDate = (String) map.get("start_date");
        String eDate = (String) map.get("end_date"); // 포함 안 하는 날짜
        String code = (String) map.get("code");

        // 날짜를 date 타입으로 변환
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(sDate);
        Date endDate = formatter.parse(eDate);
        // end_date도 포함하게 endDate 하루 넘기기
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.add(Calendar.DATE,1);
        endDate = cal.getTime();

        List<ExchangeRateRes> dtoList = exchangeRateService.getExchangeRate(startDate, endDate, code);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }




}
