package com.rabbiter.market.member.controller;

import com.rabbiter.market.common.sercurity.annotation.HasPermission;
import com.rabbiter.market.common.web.response.JsonResult;
import com.rabbiter.market.member.domain.Member;
import com.rabbiter.market.member.qo.QueryMember;
import com.rabbiter.market.member.service.IMemberService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/member/member")
public class MemberController {
    @Autowired
    private IMemberService memberService;

    /*查询信息*/
    @HasPermission("member:member:list")
    @PostMapping("/queryPageByQo")
    public JsonResult queryPageByQo(@RequestBody QueryMember qo) {
        Page<Member> page = memberService.queryPageByQo(qo);
        return JsonResult.success(page);
    }

    @HasPermission("member:member:delMember")
    @PostMapping("/delMember")
    public JsonResult delMember(Long id) {
        memberService.delMember(id);
        return JsonResult.success();
    }

    @HasPermission("member:member:save")
    @PostMapping("/save")
    public JsonResult save(@RequestBody Member member) {
        memberService.saveMember(member);
        return JsonResult.success();
    }


    @GetMapping("/queryMemberById")
    public JsonResult queryMemberById(Long id) {
        Member member = memberService.queryMemberById(id);
        return JsonResult.success(member);
    }

    @HasPermission("member:member:update")
    @PostMapping("/update")
    public JsonResult updateMember(@RequestBody Member member) {
        memberService.updateMember(member);
        return JsonResult.success();
    }

    @GetMapping("/queryMemberByPhone")
    public JsonResult queryMemberByPhone(String phone) {
        Member member = memberService.queryMemberByPhone(phone);
        return JsonResult.success(member);
    }
}
