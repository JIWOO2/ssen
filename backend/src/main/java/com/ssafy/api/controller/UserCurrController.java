package com.ssafy.api.controller;

import com.ssafy.api.request.HoldingCurrencyReq;
import com.ssafy.api.request.InterestedCurrencyReq;
import com.ssafy.api.response.HoldingCurrencyRes;
import com.ssafy.api.response.InterestedCurrencyRes;
import com.ssafy.api.service.HoldingCurrService;
import com.ssafy.api.service.InterestedCurrService;
import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 사용자 부가 기능(보유 통화 , 관심 통화)
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserCurrController {
    private final UserService userService;
    private final HoldingCurrService holdingCurrService;
    private final InterestedCurrService interestedCurrService;

    @GetMapping("/holdcurr")
    @ApiOperation(value = "보유 통화 조회")
    public ResponseEntity<List<HoldingCurrencyRes>> getHoldingCurr(@RequestBody String userId) {
        List<HoldingCurrencyRes> dtoList = null;

        User user = userService.getUserByUserId(userId);
        dtoList = holdingCurrService.getHoldingCurrByUser(user);

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping("/holdcurr")
    @ApiOperation(value = "보유 통화 등록")
    public ResponseEntity<String> addHoldingCurr(@RequestBody HoldingCurrencyReq holdingCurrencyReq) {
        String message = holdingCurrService.addHoldingCurr(holdingCurrencyReq);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PatchMapping("/holdcurr")
    @ApiOperation(value = "보유 통화 수정")
    public ResponseEntity<HoldingCurrencyRes> updateHoldingCurr(@RequestBody HoldingCurrencyReq holdingCurrencyReq) {
        HoldingCurrencyRes dto = holdingCurrService.updateHoldingCurr(holdingCurrencyReq);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/holdcurr")
    @ApiOperation(value = "보유 통화 삭제")
    public ResponseEntity<String> deleteHoldingCurr(@RequestBody HoldingCurrencyReq holdingCurrencyReq) {
        String message = holdingCurrService.deleteHoldingCurr(holdingCurrencyReq);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/intrcurr")
    @ApiOperation(value = "관심 통화 조회")
    public ResponseEntity<List<InterestedCurrencyRes>> getInterestedCurr(@RequestBody String userId) {
        List<InterestedCurrencyRes> dtoList = null;

        User user = userService.getUserByUserId(userId);
        dtoList = interestedCurrService.getInterestedCurrByUser(user);

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @PostMapping("/intrcurr")
    @ApiOperation(value = "관심 통화 등록")
    public ResponseEntity<String> addInterestedCurr(@RequestBody InterestedCurrencyReq interestedCurrencyReq) {
        String message = "FAIL";
        int targetCnt = interestedCurrService.checkTargetCnt(interestedCurrencyReq);
        if (targetCnt == -1) {// 통화 추가
            message = interestedCurrService.addInterestedCurr(interestedCurrencyReq);
        } else {// 타겟 추가

        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
