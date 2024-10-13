package com.rabbiter.market.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbiter.market.common.exception.BusinessException;
import com.rabbiter.market.member.domain.Member;
import com.rabbiter.market.member.mapper.MemberMapper;
import com.rabbiter.market.member.qo.QueryMember;
import com.rabbiter.market.member.service.IMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {
    @Override
    public Page<Member> queryPageByQo(QueryMember qo) {
        Page<Member> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery(Member.class)
                .like(StringUtils.hasText(qo.getPhone()), Member::getPhone, qo.getPhone())
                .like(StringUtils.hasText(qo.getName()), Member::getName, qo.getName())
                .eq(StringUtils.hasText(qo.getState()), Member::getState, qo.getState());
        super.page(page, wrapper);
        return page;
    }

    @Override
    public void delMember(Long id) {
        LambdaUpdateWrapper<Member> wrapper = Wrappers.lambdaUpdate(Member.class)
                .set(Member::getState, Member.STATE_BAN)
                .set(Member::getIntegral, 0L)
                .eq(Member::getId, id);

        super.update(wrapper);
    }

    @Override
    public void saveMember(Member member) {
        LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery(Member.class)
                .eq(Member::getPhone, member.getPhone())
                .eq(Member::getState, Member.STATE_NORMAL);
        Member one = super.getOne(wrapper);
        if (one != null) {
            throw new BusinessException("该用户已注册过");
        }
        member.setPassword(Member.DEFAULT_PWD);
        member.setState(Member.STATE_NORMAL);
        member.setIntegral(0L);
        super.save(member);
    }

    @Override
    public Member queryMemberById(Long id) {
        return super.getById(id);
    }

    @Override
    public void updateMember(Member member) {
        if (Member.STATE_NORMAL.equals(member.getState())) {
            LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery(Member.class)
                    .eq(Member::getPhone, member.getPhone())
                    .ne(Member::getId, member.getId())
                    .eq(Member::getState, Member.STATE_NORMAL);

            Member one = super.getOne(wrapper);
            if (one != null) {
                throw new BusinessException("已被录入");
            }
        }
        super.updateById(member);
    }

    @Override
    public Member queryMemberByPhone(String phone) {
        LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery(Member.class)
                .eq(Member::getPhone, phone)
                .eq(Member::getState, Member.STATE_NORMAL);
        Member one = super.getOne(wrapper);
        if (one == null) {
            throw new BusinessException("该会员不存在");
        }
        return one;
    }
}
