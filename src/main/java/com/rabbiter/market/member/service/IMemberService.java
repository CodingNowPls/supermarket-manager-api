package com.rabbiter.market.member.service;

import com.rabbiter.market.member.domain.Member;
import com.rabbiter.market.member.qo.QueryMember;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IMemberService extends IService<Member> {
    Page<Member> queryPageByQo(QueryMember qo);

    void delMember(Long id);

    void saveMember(Member member);

    Member queryMemberById(Long id);

    void updateMember(Member member);

    Member queryMemberByPhone(String phone);
}
